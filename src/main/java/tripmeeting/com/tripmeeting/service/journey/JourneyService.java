package tripmeeting.com.tripmeeting.service.journey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.journey.dto.*;
import tripmeeting.com.tripmeeting.domain.entity.*;
import tripmeeting.com.tripmeeting.domain.service.S3Service;
import tripmeeting.com.tripmeeting.domain.type.JourneyStatus;
import tripmeeting.com.tripmeeting.exception.exception.NotFoundException;
import tripmeeting.com.tripmeeting.exception.exception.UnAuthorizationException;
import tripmeeting.com.tripmeeting.repository.area_code.AreaCodeRepository;
import tripmeeting.com.tripmeeting.repository.chattng_room.ChattingRoomRepository;
import tripmeeting.com.tripmeeting.repository.chattng_room.UserChattingRoomRepository;
import tripmeeting.com.tripmeeting.repository.journey.JourneyRepository;
import tripmeeting.com.tripmeeting.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class JourneyService {
    private final JourneyRepository journeyRepository;
    private final AreaCodeRepository areaCodeRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final UserChattingRoomRepository userChattingRoomRepository;
    public JourneyService(JourneyRepository journeyRepository,
                          AreaCodeRepository areaCodeRepository,
                          ChattingRoomRepository chattingRoomRepository,
                          UserRepository userRepository,
                          UserChattingRoomRepository userChattingRoomRepository,
                          S3Service s3Service
                            ){
        this.journeyRepository = journeyRepository;
        this.areaCodeRepository = areaCodeRepository;
        this.chattingRoomRepository = chattingRoomRepository;
        this.userRepository = userRepository;
        this.userChattingRoomRepository = userChattingRoomRepository;
        this.s3Service = s3Service;
    }

    public JourneyDto findUnique(String journeyId) throws NotFoundException {
        Journey journey = journeyRepository.findJourneyById(journeyId);
        if(journey == null) throw new NotFoundException("journey not found");
        for (UserImage userImage : journey.getUser().getUserImages()) {
            if(userImage.getIsDeleted() == 1)
                continue;

            String url = s3Service.getObjectUrl(userImage.getUrl());
            userImage.signImageUrl(url);
        }


        return JourneyDto.mapFromRelation(journey);
    }

    public Set<JourneyLineDto> findMany(String areaCode, String search) throws NotFoundException {
        AreaCode areaCode1 = areaCodeRepository.findAreaCodeByAreaCode(areaCode);
        if(areaCode1 == null) throw new NotFoundException("area code not found");
        List<Journey> journeys;
        if(search != null){
            journeys = journeyRepository.findJourneysByAreaCodeAndTitleContainingAndStatus(areaCode1, search, JourneyStatus.okay);
        } else {
            journeys = journeyRepository.findJourneysByAreaCodeAndStatus(areaCode1, JourneyStatus.okay);
        }

        return JourneyLineDto.mapFromRelation(journeys);
    }

    public void create(CreateJourneyDto dto) throws NotFoundException {
        User user = userRepository.findUserById(dto.getUserId());
        AreaCode areaCode = areaCodeRepository.findAreaCodeByAreaCode(dto.getAreaCode());

        if(user == null) throw new NotFoundException("user not found");
        if(areaCode == null) throw new NotFoundException("area code not found");

        ChattingRoom chattingRoom = chattingRoomRepository.save(ChattingRoom.mapFromDto());
        Journey journey = Journey.mapFromDto(dto, areaCode, chattingRoom, user, null);

        journeyRepository.save(journey);
        userChattingRoomRepository.save(UserChattingRoom.mapFromRelation(user, chattingRoom));
    }

    public void join(String journeyId, JoinJourneyDto dto) throws UnAuthorizationException {
        User user = userRepository.findUserById(dto.getUserId());
        Journey journey = journeyRepository.findJourneyById(journeyId);
        if(journey.getPassword() != dto.getPassword())
            throw new UnAuthorizationException("no auth to join");

        ChattingRoom chattingRoom = journey.getChattingRoom();

        userChattingRoomRepository.save(UserChattingRoom.mapFromRelation(user, chattingRoom));
    }

    public void quit(String journeyId, QuitJourneyDto dto) throws NotFoundException {
        Journey journey = journeyRepository.findJourneyById(journeyId);
        User user = userRepository.findUserById(dto.getUserId());

        if(journey == null) throw new NotFoundException("journey not found");
        if(user == null) throw new NotFoundException("user not found");

        UserChattingRoom userChattingRoom = userChattingRoomRepository.findUserChattingRoomByChattingRoomAndUser(journey.getChattingRoom(), user);
        userChattingRoom.delete();
        userChattingRoomRepository.save(userChattingRoom);
    }

    public void fire(String journeyId, String memberId, QuitJourneyDto dto) throws UnAuthorizationException, NotFoundException {
        Journey journey = journeyRepository.findJourneyById(journeyId);
        User user = userRepository.findUserById(memberId);

        if(journey == null) throw new NotFoundException("journey not found");
        if(user == null) throw new NotFoundException("user not found");

        if(!journey.getUser().getId().equals(dto.getUserId())) throw new UnAuthorizationException("no auth to fire");

        UserChattingRoom userChattingRoom = userChattingRoomRepository.findUserChattingRoomByChattingRoomAndUser(journey.getChattingRoom(), user);
        userChattingRoom.delete();
        userChattingRoomRepository.save(userChattingRoom);
    }

    public void patch(String journeyId, PatchJourneyDto dto) throws UnAuthorizationException, NotFoundException {
        Journey journey = journeyRepository.findJourneyById(journeyId);
        if(journey == null) throw new NotFoundException("journey not found");

        if(!dto.getUserId().equals(journey.getUser().getId())) {
            log.info(dto.getUserId());
            log.info(journey.getUser().getId());
            throw new UnAuthorizationException("No Auth to patch");
        }

        journey.patch(dto);
        journeyRepository.save(journey);
    }

    public void delete(String journeyId) throws NotFoundException {
        Journey journey = journeyRepository.findJourneyById(journeyId);
        if(journey == null) throw new NotFoundException("journey not found");

        ArrayList<Plan> plans = (ArrayList<Plan>) journey.getPlans();

        for(Plan plan : plans){
            plan.delete();
        }
        journey.delete();
        journeyRepository.save(journey);
    }
}

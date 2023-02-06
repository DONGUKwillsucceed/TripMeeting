package tripmeeting.com.tripmeeting.service.journey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.journey.dto.CreateJourneyDto;
import tripmeeting.com.tripmeeting.controller.journey.dto.JourneyDto;
import tripmeeting.com.tripmeeting.controller.journey.dto.JourneyLineDto;
import tripmeeting.com.tripmeeting.domain.entity.*;
import tripmeeting.com.tripmeeting.repository.area_code.AreaCodeRepository;
import tripmeeting.com.tripmeeting.repository.chattng_room.ChattingRoomRepository;
import tripmeeting.com.tripmeeting.repository.chattng_room.UserChattingRoomRepository;
import tripmeeting.com.tripmeeting.repository.journey.JourneyRepository;
import tripmeeting.com.tripmeeting.repository.user.UserRepository;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class JourneyService {
    private final JourneyRepository journeyRepository;
    private final AreaCodeRepository areaCodeRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserRepository userRepository;
    private final UserChattingRoomRepository userChattingRoomRepository;
    public JourneyService(JourneyRepository journeyRepository,
                          AreaCodeRepository areaCodeRepository,
                          ChattingRoomRepository chattingRoomRepository,
                          UserRepository userRepository,
                          UserChattingRoomRepository userChattingRoomRepository
                            ){
        this.journeyRepository = journeyRepository;
        this.areaCodeRepository = areaCodeRepository;
        this.chattingRoomRepository = chattingRoomRepository;
        this.userRepository = userRepository;
        this.userChattingRoomRepository = userChattingRoomRepository;
    }

    public JourneyDto findUnique(String journeyId){
        Journey journey = journeyRepository.findJourneyById(journeyId);
        return JourneyDto.mapFromRelation(journey);
    }

    public Set<JourneyLineDto> findMany(String areaCode, String search){
        AreaCode areaCode1 = areaCodeRepository.findAreaCodeByAreaCode(areaCode);
        if(search != null){
            List<Journey> journeys = journeyRepository.findJourneysByAreaCodeAndTitleContaining(areaCode1, search);
            return JourneyLineDto.mapFromRelation(journeys);
        }
        List<Journey> journeys = journeyRepository.findJourneysByAreaCode(areaCode1);
        return JourneyLineDto.mapFromRelation(journeys);
    }

    public void create(CreateJourneyDto dto){
        ChattingRoom chattingRoom = chattingRoomRepository.save(ChattingRoom.mapFromDto());

        User user = userRepository.findUserById(dto.getUserId());
        AreaCode areaCode = areaCodeRepository.findAreaCodeByAreaCode(dto.getAreaCode());
        Journey journey = Journey.mapFromDto(dto, areaCode, chattingRoom, user, null);

        journeyRepository.save(journey);
        userChattingRoomRepository.save(UserChattingRoom.mapFromRelation(user, chattingRoom));
    }

    public void delete(String journeyId){
        Journey journey = journeyRepository.findJourneyById(journeyId);
        journey.delete();
        journeyRepository.save(journey);
    }
}

package tripmeeting.com.tripmeeting.service.journey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.journey.dto.CreateJourneyDto;
import tripmeeting.com.tripmeeting.controller.journey.dto.JourneyLineDto;
import tripmeeting.com.tripmeeting.domain.entity.AreaCode;
import tripmeeting.com.tripmeeting.domain.entity.ChattingRoom;
import tripmeeting.com.tripmeeting.domain.entity.Journey;
import tripmeeting.com.tripmeeting.domain.entity.User;
import tripmeeting.com.tripmeeting.repository.area_code.AreaCodeRepository;
import tripmeeting.com.tripmeeting.repository.chattng_room.ChattingRoomRepository;
import tripmeeting.com.tripmeeting.repository.journey.JourneyRepository;
import tripmeeting.com.tripmeeting.repository.user.UserRepository;

import java.util.Set;

@Service
@Slf4j
public class JourneyService {
    private final JourneyRepository journeyRepository;
    private final AreaCodeRepository areaCodeRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserRepository userRepository;
    public JourneyService(JourneyRepository journeyRepository,
                          AreaCodeRepository areaCodeRepository,
                          ChattingRoomRepository chattingRoomRepository
                            , UserRepository userRepository){
        this.journeyRepository = journeyRepository;
        this.areaCodeRepository = areaCodeRepository;
        this.chattingRoomRepository = chattingRoomRepository;
        this.userRepository = userRepository;
    }

    public Set<JourneyLineDto> findMany(String areaCode, String search){
        if(search != null){
            journeyRepository.findJourneysByAreaCodeAndTitleContaining(areaCode, search);

        }

    }

    public void create(CreateJourneyDto dto){
        ChattingRoom chattingRoom = chattingRoomRepository.save(ChattingRoom.mapFromDto());

        User user = userRepository.findUserById(dto.getUserId());
        AreaCode areaCode = areaCodeRepository.findAreaCodeByAreaCode(dto.getAreaCode());
        Journey journey = Journey.mapFromDto(dto, areaCode, chattingRoom, user, null);
        journeyRepository.save(journey);
    }

    public void delete(String journeyId){
        Journey journey = journeyRepository.findJourneyById(journeyId);
        journey.delete();
        journeyRepository.save(journey);
    }
}

package tripmeeting.com.tripmeeting.service.chatting_room;

import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.chatting_room.dto.ChattingDto;
import tripmeeting.com.tripmeeting.controller.chatting_room.dto.ChattingRoomDto;
import tripmeeting.com.tripmeeting.controller.chatting_room.dto.CreateChattingRoomDto;
import tripmeeting.com.tripmeeting.domain.entity.Chatting;
import tripmeeting.com.tripmeeting.domain.entity.ChattingRoom;
import tripmeeting.com.tripmeeting.domain.entity.User;
import tripmeeting.com.tripmeeting.domain.entity.UserChattingRoom;
import tripmeeting.com.tripmeeting.exception.exception.NotFoundException;
import tripmeeting.com.tripmeeting.repository.chattng_room.ChattingRepository;
import tripmeeting.com.tripmeeting.repository.chattng_room.ChattingRoomRepository;
import tripmeeting.com.tripmeeting.repository.chattng_room.UserChattingRoomRepository;
import tripmeeting.com.tripmeeting.repository.user.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class ChattingRoomService {
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserChattingRoomRepository userChattingRoomRepository;
    private final ChattingRepository chattingRepository;

    private final UserRepository userRepository;
    public ChattingRoomService(ChattingRoomRepository chattingRoomRepository,
                               UserChattingRoomRepository userChattingRoomRepository,
                               ChattingRepository chattingRepository,
                               UserRepository userRepository){
        this.chattingRoomRepository = chattingRoomRepository;
        this.userChattingRoomRepository = userChattingRoomRepository;
        this.chattingRepository = chattingRepository;
        this.userRepository = userRepository;
    }

    public Set<ChattingRoomDto> findMany(String userId) throws NotFoundException {
        User user = userRepository.findUserById(userId);

        if(user == null) throw new NotFoundException("user not found");

        List<UserChattingRoom> userChattingRooms =
                userChattingRoomRepository.findUserChattingRoomsByUserAndIsDeleted(user, 0);
        List<ChattingRoom> chattingRooms =
                chattingRoomRepository.findChattingRoomsByUserChattingRoomCollectionIn(userChattingRooms);

        return ChattingRoomDto.mapFromRelation(chattingRooms);
    }

    public Set<ChattingDto> findManyForChatting(String userId, String roomId) throws NotFoundException {
        User user = userRepository.findUserById(userId);
        ChattingRoom chattingRoom = chattingRoomRepository.findChattingRoomById(roomId);

        if(user == null) throw new NotFoundException("user not found");
        if(chattingRoom == null) throw new NotFoundException("chatting room not found");

        return ChattingDto.mapFromRelation((List<Chatting>) chattingRoom.chattingCollection);
    }

    public void create(String userId, CreateChattingRoomDto dto) throws NotFoundException {
        User user1 = userRepository.findUserById(userId);
        User user2 = userRepository.findUserById(dto.getUserId());

        if(user1 == null || user2 == null) throw new NotFoundException("user not found");

        ChattingRoom chattingRoom = chattingRoomRepository.save(ChattingRoom.mapFromDto());

        UserChattingRoom userChattingRoom1 = UserChattingRoom.mapFromRelation(user1, chattingRoom);
        UserChattingRoom userChattingRoom2 = UserChattingRoom.mapFromRelation(user2, chattingRoom);

        userChattingRoomRepository.save(userChattingRoom1);
        userChattingRoomRepository.save(userChattingRoom2);
    }

    public void delete(String userId, String roomId) throws NotFoundException {
        User user = userRepository.findUserById(userId);
        ChattingRoom chattingRoom = chattingRoomRepository.findChattingRoomById(roomId);
        UserChattingRoom userChattingRoom =
                userChattingRoomRepository.findUserChattingRoomByChattingRoomAndUser(chattingRoom, user);

        if(user == null) throw new NotFoundException("user not found");
        if(chattingRoom == null) throw new NotFoundException("chatting room not found");
        if(userChattingRoom == null) throw new NotFoundException("that user is not in the chatting room");

        userChattingRoom.delete();
        userChattingRoomRepository.save(userChattingRoom);
    }

    public void deleteForChatting(String userId, String roomId, String chattingId) throws NotFoundException {
        User user = userRepository.findUserById(userId);
        ChattingRoom chattingRoom = chattingRoomRepository.findChattingRoomById(roomId);

        if(user == null) throw new NotFoundException("user not found");
        if(chattingRoom == null) throw new NotFoundException("chatting room not found");

        Chatting chatting = chattingRepository.findChattingByUserAndChattingRoomAndId(user, chattingRoom, chattingId);

        if(chatting == null) throw new NotFoundException("chatting not found");

        chatting.delete();
        chattingRepository.save(chatting);
    }


}

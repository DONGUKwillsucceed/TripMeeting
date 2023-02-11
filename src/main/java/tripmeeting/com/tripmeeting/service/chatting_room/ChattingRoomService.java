package tripmeeting.com.tripmeeting.service.chatting_room;

import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.chatting_room.dto.*;
import tripmeeting.com.tripmeeting.controller.journey.dto.MemberDto;
import tripmeeting.com.tripmeeting.domain.entity.*;
import tripmeeting.com.tripmeeting.domain.service.S3Service;
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
    private final S3Service s3Service;
    private final UserRepository userRepository;
    public ChattingRoomService(ChattingRoomRepository chattingRoomRepository,
                               UserChattingRoomRepository userChattingRoomRepository,
                               ChattingRepository chattingRepository,
                               UserRepository userRepository,
                               S3Service s3Service){
        this.chattingRoomRepository = chattingRoomRepository;
        this.userChattingRoomRepository = userChattingRoomRepository;
        this.chattingRepository = chattingRepository;
        this.userRepository = userRepository;
        this.s3Service = s3Service;
    }

    public Set<ChattingRoomDto> findMany(String userId) throws NotFoundException {
        User user = userRepository.findUserById(userId);

        if(user == null) throw new NotFoundException("user not found");

        List<UserChattingRoom> userChattingRooms =
                userChattingRoomRepository.findUserChattingRoomsByUserAndIsDeleted(user, 0);

        for(UserChattingRoom userChattingRoom : userChattingRooms){
            for(UserImage userImage : userChattingRoom.getUser().getUserImages()){
                if(userImage.getIsDeleted() == 1) continue;

                String url = s3Service.getObjectUrl(userImage.getUrl());
                userImage.signImageUrl(url);
            }
        }

        List<ChattingRoom> chattingRooms =
                chattingRoomRepository.findChattingRoomsByUserChattingRoomCollectionIn(userChattingRooms);

        return ChattingRoomDto.mapFromRelation(chattingRooms);
    }

    public Set<ChattingDto> findManyForChatting(String userId, String roomId) throws NotFoundException {
        User user = userRepository.findUserById(userId);
        ChattingRoom chattingRoom = chattingRoomRepository.findChattingRoomById(roomId);

        if(user == null) throw new NotFoundException("user not found");
        if(chattingRoom == null) throw new NotFoundException("chatting room not found");

        for(Chatting chatting : chattingRoom.chattingCollection){
            if(chatting.getIsDeleted() == 1) continue;

            for(UserImage userImage : chatting.getUser().getUserImages()) {
                if(userImage.getIsDeleted() == 1) continue;

                String url = s3Service.getObjectUrl(userImage.getUrl());
                userImage.signImageUrl(url);
            }
        }

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

    public MemberDto addUser(AddUserDto dto){
        ChattingRoom chattingRoom = chattingRoomRepository.findChattingRoomById(dto.getRoomId());
        User user = userRepository.findUserById(dto.getUserId());
        UserChattingRoom userChattingRoom =
                userChattingRoomRepository.findUserChattingRoomByChattingRoomAndUser(chattingRoom, user);

        userChattingRoom.delete();
        userChattingRoomRepository.save(userChattingRoom);

        return MemberDto.mapFromRelationForUser(user);
    }

    public ChattingDto createForChatting(SendMessageDto dto) throws NotFoundException {
        User user = userRepository.findUserById(dto.getUserId());
        ChattingRoom chattingRoom = chattingRoomRepository.findChattingRoomById(dto.getRoomId());

        if(user == null) throw new NotFoundException("user not found");
        if(chattingRoom == null) throw new NotFoundException("chatting room not found");

        Chatting chatting = chattingRepository.save(Chatting.mapFromDto(dto, user, chattingRoom));

        return ChattingDto.mapFromRelationForOne(chatting);
    }

    public MemberDto deleteUser(ExitChattingRoomDto dto) throws NotFoundException {
        User user = userRepository.findUserById(dto.getUserId());
        ChattingRoom chattingRoom = chattingRoomRepository.findChattingRoomById(dto.getRoomId());
        UserChattingRoom userChattingRoom =
                userChattingRoomRepository.findUserChattingRoomByChattingRoomAndUser(chattingRoom, user);

        if(user == null) throw new NotFoundException("user not found");
        if(chattingRoom == null) throw new NotFoundException("chatting room not found");
        if(userChattingRoom == null) throw new NotFoundException("that user is not in the chatting room");

        userChattingRoom.delete();
        userChattingRoomRepository.save(userChattingRoom);
        return MemberDto.mapFromRelationForUser(user);
    }

    public Chatting deleteForChatting(DeleteChattingDto dto) throws NotFoundException {
        User user = userRepository.findUserById(dto.getUserId());
        ChattingRoom chattingRoom = chattingRoomRepository.findChattingRoomById(dto.getRoomId());

        if(user == null) throw new NotFoundException("user not found");
        if(chattingRoom == null) throw new NotFoundException("chatting room not found");

        Chatting chatting = chattingRepository.findChattingByUserAndChattingRoomAndId(user, chattingRoom, dto.getChattingId());

        if(chatting == null) throw new NotFoundException("chatting not found");

        chatting.delete();
        return chattingRepository.save(chatting);
    }


}

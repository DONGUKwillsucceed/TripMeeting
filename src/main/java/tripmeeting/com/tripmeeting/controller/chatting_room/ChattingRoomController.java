package tripmeeting.com.tripmeeting.controller.chatting_room;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tripmeeting.com.tripmeeting.controller.chatting_room.dto.ChattingDto;
import tripmeeting.com.tripmeeting.controller.chatting_room.dto.ChattingRoomDto;
import tripmeeting.com.tripmeeting.controller.chatting_room.dto.CreateChattingRoomDto;
import tripmeeting.com.tripmeeting.exception.exception.NotFoundException;
import tripmeeting.com.tripmeeting.service.chatting_room.ChattingRoomService;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("user/{userId}/chatting-room")
public class ChattingRoomController {
    private final ChattingRoomService chattingRoomService;

    public ChattingRoomController(ChattingRoomService chattingRoomService){
        this.chattingRoomService = chattingRoomService;
    }
    @GetMapping()
    public Set<ChattingRoomDto> findMany(@PathVariable("userId") String userId) throws NotFoundException {
        return chattingRoomService.findMany(userId);
    }

    @GetMapping("{roomId}/chatting")
    public Set<ChattingDto> findManyForChatting(@PathVariable("userId") String userId,
                                                @PathVariable("roomId") String roomId) throws NotFoundException {
        return chattingRoomService.findManyForChatting(userId, roomId);
    }


    @PostMapping("create")
    public void create(@PathVariable("userId") String userId, @RequestBody CreateChattingRoomDto dto) throws NotFoundException {
        chattingRoomService.create(userId, dto);
    }


    @DeleteMapping("{roomId}")
    public void delete(@PathVariable("userId") String userId, @PathVariable("roomId") String roomId) throws NotFoundException {
        chattingRoomService.delete(userId, roomId);
    }
    @DeleteMapping("{roomId}/chatting/{chattingId}")
    public void deleteForChatting(@PathVariable("userId") String userId,
                                  @PathVariable("roomId") String roomId,
                                  @PathVariable("chattingId") String chattingId) throws NotFoundException {
        chattingRoomService.deleteForChatting(userId, roomId, chattingId);
    }
}

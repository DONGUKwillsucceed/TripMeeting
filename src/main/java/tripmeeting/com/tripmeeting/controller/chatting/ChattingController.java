package tripmeeting.com.tripmeeting.controller.chatting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tripmeeting.com.tripmeeting.controller.chatting.dto.ChattingRoomDto;
import tripmeeting.com.tripmeeting.controller.chatting.dto.CreateChattingRoomDto;
import tripmeeting.com.tripmeeting.exception.exception.NotFoundException;
import tripmeeting.com.tripmeeting.service.chatting.ChattingRoomService;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("user/{userId}/chatting-room")
public class ChattingController {
    private final ChattingRoomService chattingRoomService;

    public ChattingController(ChattingRoomService chattingRoomService){
        this.chattingRoomService = chattingRoomService;
    }
    @GetMapping()
    public Set<ChattingRoomDto> findMany(@PathVariable("userId") String userId) throws NotFoundException {
        return chattingRoomService.findMany(userId);
    }

    @PostMapping("create")
    public void create(@PathVariable("userId") String userId, @RequestBody CreateChattingRoomDto dto) throws NotFoundException {
        chattingRoomService.create(userId, dto);
    }


    @DeleteMapping("{roomId}")
    public void delete(@PathVariable("userId") String userId, @PathVariable("roomId") String roomId) throws NotFoundException {
        chattingRoomService.delete(userId, roomId);
    }
}

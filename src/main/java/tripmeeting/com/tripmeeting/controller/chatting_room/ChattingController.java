package tripmeeting.com.tripmeeting.controller.chatting_room;

import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import tripmeeting.com.tripmeeting.controller.chatting_room.dto.*;
import tripmeeting.com.tripmeeting.controller.journey.dto.MemberDto;
import tripmeeting.com.tripmeeting.domain.entity.Chatting;
import tripmeeting.com.tripmeeting.exception.exception.NotFoundException;
import tripmeeting.com.tripmeeting.service.chatting_room.ChattingRoomService;

@RestController
public class ChattingController{
    private final ChattingRoomService chattingRoomService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    public ChattingController(ChattingRoomService chattingRoomService,
                              SimpMessagingTemplate simpMessagingTemplate){
        this.chattingRoomService = chattingRoomService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
    @MessageMapping("/add-chatting")
    public void addChatting(@Valid SendMessageDto dto) throws NotFoundException {
        ChattingDto data = chattingRoomService.createForChatting(dto);
        simpMessagingTemplate.convertAndSend("/chatting/room/" + dto.getRoomId() + "add-chatting", data);
    }

    @MessageMapping("/delete-chatting")
    public void deleteChatting(@Valid DeleteChattingDto dto) throws NotFoundException {
        Chatting data = chattingRoomService.deleteForChatting(dto);
        simpMessagingTemplate.convertAndSend("/chatting/room/" + dto.getRoomId() + "delete-chatting", data);
    }

    @MessageMapping("add-user")
    public void addUser(@Valid AddUserDto dto){
        MemberDto data = chattingRoomService.addUser(dto);
        simpMessagingTemplate.convertAndSend("/chatting/room/" + dto.getRoomId() + "add-user", data);
    }

    @MessageMapping("exit-user")
    public void exitUser(@Valid ExitChattingRoomDto dto) throws NotFoundException {
        MemberDto data = chattingRoomService.deleteUser(dto);
        simpMessagingTemplate.convertAndSend("/chatting/room/" + dto.getRoomId() + "exit-user", data);
    }
}

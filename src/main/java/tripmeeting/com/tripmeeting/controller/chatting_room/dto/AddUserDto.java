package tripmeeting.com.tripmeeting.controller.chatting_room.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddUserDto {

    @NotEmpty(message = "user id necessary")
    private String userId;

    @NotEmpty(message = "chatting id necessary")
    private String roomId;

    @Builder
    public AddUserDto(String userId, String roomId){
        this.userId = userId;
        this.roomId = roomId;
    }
}

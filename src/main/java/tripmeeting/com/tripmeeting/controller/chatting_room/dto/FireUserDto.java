package tripmeeting.com.tripmeeting.controller.chatting_room.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FireUserDto {
    @NotEmpty
    String ownerId;

    @NotEmpty
    String userId;
}

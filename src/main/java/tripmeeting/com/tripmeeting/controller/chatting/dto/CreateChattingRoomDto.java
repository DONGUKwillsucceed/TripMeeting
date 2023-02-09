package tripmeeting.com.tripmeeting.controller.chatting.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateChattingRoomDto {
    private String userId;

    @Builder
    public CreateChattingRoomDto(String userId){
        this.userId = userId;
    }
}

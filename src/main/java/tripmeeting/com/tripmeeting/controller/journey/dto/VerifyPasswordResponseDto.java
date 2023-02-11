package tripmeeting.com.tripmeeting.controller.journey.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerifyPasswordResponseDto {
    private boolean result;

    public VerifyPasswordResponseDto(boolean result){
        this.result = result;
    }

    public void update(boolean result){
        this.result = result;
    }
}

package tripmeeting.com.tripmeeting.controller.journey.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuitJourneyDto {
    @NotEmpty(message = "user id necessary")
    String userId;
}

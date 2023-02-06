package tripmeeting.com.tripmeeting.controller.journey.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PatchJourneyDto {
    String userId; // 방장만 권한이 있음

    String name;

    @NotEmpty(message = "description is necessary")
    String description;
}

package tripmeeting.com.tripmeeting.controller.plan.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePlanDto {
    @NotNull(message = "day necessary")
    int day;

    @NotNull(message = "turn necessary")
    int turn;

    @NotEmpty(message = "name necessary")
    String name;

    @NotNull(message = "latitude necessary")
    double latitude;

    @NotNull(message = "longitude necessary")
    double longitude;
}

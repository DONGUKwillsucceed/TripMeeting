package tripmeeting.com.tripmeeting.controller.journey.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanDto {
    String id;
    String name;
    int day;
    int turn;
    int latitude;
    int longitude;
    String userId;
}

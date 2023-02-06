package tripmeeting.com.tripmeeting.controller.journey.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JourneyDto {
    String id;
    String name;
    String imageUrl;

    String startDate;

    String userId;

    String endDate;
    int max;

    Set<MemberDto> members;

    Set<PlanDto> plans;
}

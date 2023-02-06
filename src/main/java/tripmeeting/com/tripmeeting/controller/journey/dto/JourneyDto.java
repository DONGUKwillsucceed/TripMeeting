package tripmeeting.com.tripmeeting.controller.journey.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.entity.Journey;
import tripmeeting.com.tripmeeting.domain.entity.Plan;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JourneyDto {
    String id;
    String name;
    String imageUrl;

    Date startDate;

    String userId;

    Date endDate;
    int max;

    Set<MemberDto> members;

    Set<PlanDto> plans;

    @Builder
    public JourneyDto(String id, String name, String imageUrl, Date startDate,
                      String userId, Date endDate, int max,Set<MemberDto> members, Set<PlanDto> plans){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.userId = userId;
        this.endDate = endDate;
        this.max = max;
        this.members = members;
        this.plans = plans;
    }

    public static JourneyDto mapFromRelation(Journey journey){
        return JourneyDto.builder()
                .id(journey.getId())
                .name(journey.getTitle())
                .max(journey.getCrewCapacity())
                .startDate(journey.getStartDate())
                .endDate(journey.getEndDate())
                .imageUrl(
                        journey.getImage() != null ? journey.getImage().getUrl() : null
                )
                .userId(journey.getUser().getId())
                .members(MemberDto.mapFromRelation(journey.getChattingRoom().getUsers()))
                .plans(PlanDto.mapFromRelation((List<Plan>) journey.getPlans()))
                .build();
    }
}

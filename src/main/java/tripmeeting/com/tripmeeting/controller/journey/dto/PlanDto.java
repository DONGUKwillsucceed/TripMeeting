package tripmeeting.com.tripmeeting.controller.journey.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.entity.Plan;
import tripmeeting.com.tripmeeting.domain.type.JourneyStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanDto {
    String id;
    String name;
    int day;
    int turn;
    double latitude;
    double longitude;
    String userId;

    @Builder
    public PlanDto(String id, String name, int day, int turn, double latitude, double longitude, String userId){
        this.id = id;
        this.name = name;
        this.day = day;
        this.turn = turn;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userId = userId;
    }

    public static Set<PlanDto> mapFromRelation(List<Plan> plans){
        Set<PlanDto> dtos = new HashSet<>();
        for(Plan plan : plans){
            if(plan.getStatus() != JourneyStatus.okay)
                continue;

            PlanDto dto = PlanDto.builder()
                    .id(plan.getId())
                    .name(plan.getName())
                    .day(plan.getDay())
                    .turn(plan.getTurn())
                    .latitude(plan.getLatitude())
                    .longitude(plan.getLongitude())
                    .userId(null)
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
}

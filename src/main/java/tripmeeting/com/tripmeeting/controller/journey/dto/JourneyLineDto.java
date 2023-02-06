package tripmeeting.com.tripmeeting.controller.journey.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.entity.Journey;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JourneyLineDto {
    String id;
    String name;
    String imageUrl;
    String startDate;
    String endDate;
    int max;
    Set<MemberDto> members;

    @Builder
    public JourneyLineDto(String id,
                          String name,
                          String imageUrl,
                          String startDate,
                          String endDate,
                          int max,
                          Set<MemberDto> members){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.max = max;
        this.members = members;
    }

    public static JourneyLineDto mapFromRelation(Journey journey, String profileImageUrl){
        return JourneyLineDto.builder()
        .id(journey.getId())
        .name(journey.getTitle())
        .imageUrl(journey.getImage().getUrl())
        .max(journey.getCrewCapacity())
        .build();

    }
}

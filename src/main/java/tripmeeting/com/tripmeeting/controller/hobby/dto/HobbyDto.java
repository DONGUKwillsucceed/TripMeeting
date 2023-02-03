package tripmeeting.com.tripmeeting.controller.hobby.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.entity.Hobby;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HobbyDto {
    String id;
    String name;

    @Builder
    public HobbyDto(String id, String name){
        this.id = id;
        this.name = name;
    }

    public static Set<HobbyDto> mapFromRelation(List<Hobby> hobbies){
        Set<HobbyDto> dtos = new HashSet<>();
        for(Hobby hobby: hobbies){
            HobbyDto dto = HobbyDto.builder()
                    .id(hobby.getId())
                    .name(hobby.getName())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
}

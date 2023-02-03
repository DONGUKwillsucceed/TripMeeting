package tripmeeting.com.tripmeeting.controller.job.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.entity.Job;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobDto {
    String id;
    String name;

    @Builder
    public JobDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Set<JobDto> mapFromRelation(List<Job> jobs){
        Set<JobDto> dtos = new HashSet<>();
        for(Job job : jobs){
            JobDto dto = JobDto.builder()
                    .id(job.getId())
                    .name(job.getName())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
}

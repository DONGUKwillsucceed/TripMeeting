package tripmeeting.com.tripmeeting.service.job;

import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.job.dto.JobDto;
import tripmeeting.com.tripmeeting.repository.job.JobRepository;

import java.util.Set;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }
    public Set<JobDto> findAllBySearch(String search){
        if(search != null){
            return JobDto.mapFromRelation(jobRepository.findJobsByNameContaining(search));
        }
        return JobDto.mapFromRelation(jobRepository.findAll());
    }
}

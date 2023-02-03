package tripmeeting.com.tripmeeting.controller.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripmeeting.com.tripmeeting.controller.job.dto.JobDto;
import tripmeeting.com.tripmeeting.service.job.JobService;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("job")
public class JobController {
    JobService jobService;
    public JobController(JobService jobService){
        this.jobService = jobService;
    }
    @GetMapping()
    public Set<JobDto> findAllBySearch(@RequestParam(name = "search", required = false) String search){
        return jobService.findAllBySearch(search);
    }
}

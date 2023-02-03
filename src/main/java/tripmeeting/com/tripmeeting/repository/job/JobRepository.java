package tripmeeting.com.tripmeeting.repository.job;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.Job;

import java.util.ArrayList;

public interface JobRepository extends JpaRepository<Job, String> {
    Job findJobById(String id);

    ArrayList<Job> findJobsByNameContaining(String name);

}

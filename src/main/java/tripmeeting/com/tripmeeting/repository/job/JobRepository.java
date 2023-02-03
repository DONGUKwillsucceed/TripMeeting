package tripmeeting.com.tripmeeting.repository.job;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.Job;

public interface JobRepository extends JpaRepository<Job, String> {
    Job findJobById(String id);
}

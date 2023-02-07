package tripmeeting.com.tripmeeting.repository.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, String> {
    Plan findPlanById(String id);
}

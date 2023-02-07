package tripmeeting.com.tripmeeting.service.plan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.plan.dto.CreatePlanDto;
import tripmeeting.com.tripmeeting.domain.entity.Journey;
import tripmeeting.com.tripmeeting.domain.entity.Plan;
import tripmeeting.com.tripmeeting.exception.exception.NotFoundException;
import tripmeeting.com.tripmeeting.repository.journey.JourneyRepository;
import tripmeeting.com.tripmeeting.repository.plan.PlanRepository;

@Service
@Slf4j
public class PlanService {
    private final PlanRepository planRepository;

    private final JourneyRepository journeyRepository;

    public PlanService(PlanRepository planRepository, JourneyRepository journeyRepository){
        this.planRepository = planRepository;
        this.journeyRepository = journeyRepository;
    }

    public void create(String journeyId, CreatePlanDto dto) throws NotFoundException {
        Journey journey = journeyRepository.findJourneyById(journeyId);
        if(journey == null) throw new NotFoundException("journey not found");
        
        planRepository.save(Plan.mapFromRelation(dto, journey));
    }

    public void delete(String planId) throws NotFoundException {
        Plan plan = planRepository.findPlanById(planId);
        plan.delete();
        planRepository.save(plan);
    }
}

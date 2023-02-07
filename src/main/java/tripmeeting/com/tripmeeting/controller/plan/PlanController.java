package tripmeeting.com.tripmeeting.controller.plan;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tripmeeting.com.tripmeeting.controller.plan.dto.CreatePlanDto;
import tripmeeting.com.tripmeeting.exception.exception.NotFoundException;
import tripmeeting.com.tripmeeting.service.plan.PlanService;

@RestController
@Slf4j
@RequestMapping("journey/{journeyId}/plan")
public class PlanController {
    private final PlanService planService;
    public PlanController(PlanService planService){
        this.planService = planService;
    }


    @PostMapping("create")
    public void create(@PathVariable("journeyId") String journeyId, @Valid @RequestBody CreatePlanDto dto) throws NotFoundException {
        planService.create(journeyId, dto);
    }

    @DeleteMapping("{planId}")
    public void delete(@PathVariable("planId") String planId) throws NotFoundException {
        planService.delete(planId);
    }
}

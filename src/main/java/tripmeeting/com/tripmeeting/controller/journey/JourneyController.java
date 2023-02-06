package tripmeeting.com.tripmeeting.controller.journey;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tripmeeting.com.tripmeeting.controller.journey.dto.CreateJourneyDto;
import tripmeeting.com.tripmeeting.controller.journey.dto.JourneyDto;
import tripmeeting.com.tripmeeting.controller.journey.dto.JourneyLineDto;
import tripmeeting.com.tripmeeting.service.journey.JourneyService;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("journey")
public class JourneyController {
    private final JourneyService journeyService;
    public JourneyController(JourneyService journeyService){
        this.journeyService = journeyService;
    }

    @GetMapping("{journeyId}")
    public JourneyDto findUnique(@PathVariable("journeyId") String journeyId){
        return journeyService.findUnique(journeyId);
    }

    @GetMapping()
    public Set<JourneyLineDto> findMany(
                                   @RequestParam(name = "area_code", required = false) String area_code,
                                   @RequestParam(name = "search", required = false) String search) {
        return journeyService.findMany(area_code, search);
    }

    @PostMapping("create")
    public void create(@Valid @RequestBody CreateJourneyDto dto){
        journeyService.create(dto);
    }

    @DeleteMapping("{journeyId}")
    public void delete(@PathVariable("journeyId") String journyId){
        journeyService.delete(journyId);
    }

}

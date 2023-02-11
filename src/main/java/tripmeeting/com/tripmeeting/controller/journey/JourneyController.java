package tripmeeting.com.tripmeeting.controller.journey;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tripmeeting.com.tripmeeting.controller.journey.dto.*;
import tripmeeting.com.tripmeeting.exception.exception.NotFoundException;
import tripmeeting.com.tripmeeting.exception.exception.UnAuthorizationException;
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
    public JourneyDto findUnique(@PathVariable("journeyId") String journeyId) throws NotFoundException {
        return journeyService.findUnique(journeyId);
    }

    @GetMapping()
    public Set<JourneyLineDto> findMany(
                                   @RequestParam(name = "area_code", required = false) String area_code,
                                   @RequestParam(name = "search", required = false) String search) throws NotFoundException {
        return journeyService.findMany(area_code, search);
    }

    @PostMapping("create")
    public void create(@Valid @RequestBody CreateJourneyDto dto) throws NotFoundException {
        journeyService.create(dto);
    }

    @PostMapping("{journeyId}/verify-password")
    public VerifyPasswordResponseDto join(@PathVariable("journeyId") String journeyId, @Valid @RequestBody VerifyPasswordRequestDto dto) {
        return journeyService.verify(journeyId, dto);
    }

    @PostMapping("{journeyId}/quit")
    public void quit(@PathVariable("journeyId") String journeyId, @Valid @RequestBody QuitJourneyDto dto) throws NotFoundException {
        journeyService.quit(journeyId, dto);
    }

    @PostMapping("{journeyId}/member/{memberId}/fire")
    public void fire(@PathVariable("journeyId") String journeyId, @PathVariable("memberId") String memberId, @Valid @RequestBody QuitJourneyDto dto) throws UnAuthorizationException, NotFoundException {
        journeyService.fire(journeyId, memberId, dto);
    }

    @PatchMapping("{journeyId}")
    public void patch(@PathVariable("journeyId") String journeyId, @Valid @RequestBody PatchJourneyDto dto) throws UnAuthorizationException, NotFoundException {
        journeyService.patch(journeyId, dto);
    }

    @DeleteMapping("{journeyId}")
    public void delete(@PathVariable("journeyId") String journeyId) throws NotFoundException {
        journeyService.delete(journeyId);
    }

}

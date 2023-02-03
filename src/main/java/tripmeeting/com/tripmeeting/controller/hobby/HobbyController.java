package tripmeeting.com.tripmeeting.controller.hobby;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripmeeting.com.tripmeeting.controller.hobby.dto.HobbyDto;
import tripmeeting.com.tripmeeting.service.hobby.HobbyService;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("hobby")
public class HobbyController {

    private final HobbyService hobbyService;
    public HobbyController(HobbyService hobbyService){
        this.hobbyService = hobbyService;
    }

    @GetMapping()
    public Set<HobbyDto> findAllBySearch(@RequestParam(name = "search", required = false) String search){
        return hobbyService.findAllBySearch(search);
    }
}

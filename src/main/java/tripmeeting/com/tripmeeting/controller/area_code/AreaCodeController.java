package tripmeeting.com.tripmeeting.controller.area_code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripmeeting.com.tripmeeting.controller.area_code.dto.AreaCodeDto;
import tripmeeting.com.tripmeeting.service.area_code.AreaCodeService;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("area_code")
public class AreaCodeController {
    private final AreaCodeService areaCodeService;
    public AreaCodeController(AreaCodeService areaCodeService){
        this.areaCodeService = areaCodeService;
    }

    @GetMapping("area_one")
    public Set<AreaCodeDto> findAllAreaOne(){
        return areaCodeService.findAllAreaOne();
    }

    @GetMapping("area_two")
    public Set<AreaCodeDto> findAllAreaTwoByAreaOne(@RequestParam(name = "area_one") String areaOne){
        return areaCodeService.findAllAreaTwo(areaOne);
    }

}

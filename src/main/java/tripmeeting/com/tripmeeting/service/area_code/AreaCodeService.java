package tripmeeting.com.tripmeeting.service.area_code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.area_code.dto.AreaCodeDto;
import tripmeeting.com.tripmeeting.domain.entity.AreaCode;
import tripmeeting.com.tripmeeting.repository.area_code.AreaCodeRepository;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AreaCodeService {
    private final AreaCodeRepository areaCodeRepository;
    public AreaCodeService(AreaCodeRepository areaCodeRepository){
        this.areaCodeRepository = areaCodeRepository;
    }

    public Set<AreaCodeDto> findAllAreaOne(){
        List<AreaCode> areaCodes = areaCodeRepository.findAllByAreaTwo(null);
        return AreaCodeDto.mapFromRelationForAreaOne(areaCodes);
    }

    public Set<AreaCodeDto> findAllAreaTwo(String areaOne){
        List<AreaCode> areaCodes = areaCodeRepository.findAllByAreaOne(areaOne);
        return AreaCodeDto.mapFromRelationForAreaTwo(areaCodes);
    }
}

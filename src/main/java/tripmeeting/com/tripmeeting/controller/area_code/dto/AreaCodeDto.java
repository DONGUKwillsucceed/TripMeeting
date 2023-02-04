package tripmeeting.com.tripmeeting.controller.area_code.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripmeeting.com.tripmeeting.domain.entity.AreaCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AreaCodeDto {
    private String areaCode;
    private String name;
    @Builder
    public AreaCodeDto(String areaCode, String name){
        this.areaCode = areaCode;
        this.name = name;
    }

    public static Set<AreaCodeDto> mapFromRelationForAreaOne(List<AreaCode> areaCodes){
        Set<AreaCodeDto> dtos = new HashSet<>();
        for(AreaCode areaCode : areaCodes){
            AreaCodeDto dto = AreaCodeDto.builder()
                    .areaCode(areaCode.getAreaCode())
                    .name(areaCode.getAreaOne())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
    public static Set<AreaCodeDto> mapFromRelationForAreaTwo(List<AreaCode> areaCodes){
        Set<AreaCodeDto> dtos = new HashSet<>();
        for(AreaCode areaCode : areaCodes){
            AreaCodeDto dto = AreaCodeDto.builder()
                    .areaCode(areaCode.getAreaCode())
                    .name(areaCode.getAreaTwo())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
}

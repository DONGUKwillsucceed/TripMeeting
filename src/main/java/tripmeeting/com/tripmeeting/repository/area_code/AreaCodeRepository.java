package tripmeeting.com.tripmeeting.repository.area_code;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.AreaCode;

public interface AreaCodeRepository extends JpaRepository<AreaCode, String> {
    AreaCode findAreaCodeByAreaCode(String areaCode);
}

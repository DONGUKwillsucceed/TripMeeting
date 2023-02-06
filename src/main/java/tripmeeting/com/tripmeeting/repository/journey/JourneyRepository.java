package tripmeeting.com.tripmeeting.repository.journey;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.AreaCode;
import tripmeeting.com.tripmeeting.domain.entity.Journey;

import java.util.ArrayList;

public interface JourneyRepository extends JpaRepository<Journey, String> {
    Journey findJourneyById(String id);

    ArrayList<Journey> findJourneysByAreaCode(AreaCode areaCode);

    ArrayList<Journey> findJourneysByAreaCodeAndTitleContaining(AreaCode areaCode, String search);
}

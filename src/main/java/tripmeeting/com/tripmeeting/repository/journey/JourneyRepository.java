package tripmeeting.com.tripmeeting.repository.journey;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.Journey;

import java.util.ArrayList;

public interface JourneyRepository extends JpaRepository<Journey, String> {
    Journey findJourneyById(String id);

    ArrayList<Journey> findJourneysByAreaCode(String areaCode);

    ArrayList<Journey> findJourneysByAreaCodeAndTitleContaining(String areaCode, String search);
}

package tripmeeting.com.tripmeeting.repository.journey;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.AreaCode;
import tripmeeting.com.tripmeeting.domain.entity.Journey;
import tripmeeting.com.tripmeeting.domain.type.JourneyStatus;

import java.util.ArrayList;

public interface JourneyRepository extends JpaRepository<Journey, String> {
    Journey findJourneyById(String id);

    ArrayList<Journey> findJourneysByAreaCodeAndStatus(AreaCode areaCode, JourneyStatus status);


    ArrayList<Journey> findJourneysByAreaCodeAndTitleContainingAndStatus(AreaCode areaCode, String search, JourneyStatus status);
}

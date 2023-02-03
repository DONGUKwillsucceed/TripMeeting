package tripmeeting.com.tripmeeting.repository.hobby;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.Hobby;

import java.util.ArrayList;


public interface HobbyRepository extends JpaRepository<Hobby, String> {
    ArrayList<Hobby> findAllByNameContaining(String search);
}

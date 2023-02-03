package tripmeeting.com.tripmeeting.repository.hobby;

import org.springframework.data.jpa.repository.JpaRepository;
import tripmeeting.com.tripmeeting.domain.entity.Hobby;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, String> {
    @Override
    List<Hobby> findAllById(Iterable<String> strings);
}

package tripmeeting.com.tripmeeting.service.hobby;

import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.hobby.dto.HobbyDto;
import tripmeeting.com.tripmeeting.repository.hobby.HobbyRepository;

import java.util.Set;

@Service
public class HobbyService {
    private final HobbyRepository hobbyRepository;

    public HobbyService(HobbyRepository hobbyRepository){
        this.hobbyRepository = hobbyRepository;
    }

    public Set<HobbyDto> findAllBySearch(String search){
        if(search != null)
            return HobbyDto.mapFromRelation(hobbyRepository.findAllByNameContaining(search));
        return HobbyDto.mapFromRelation(hobbyRepository.findAll());
    }
}

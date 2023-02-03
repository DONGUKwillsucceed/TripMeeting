package tripmeeting.com.tripmeeting.service.user;

import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.user.dto.CreateUserDto;
import tripmeeting.com.tripmeeting.controller.user.dto.PatchUserDto;
import tripmeeting.com.tripmeeting.controller.user.dto.UserDto;
import tripmeeting.com.tripmeeting.domain.entity.AreaCode;
import tripmeeting.com.tripmeeting.domain.entity.Hobby;
import tripmeeting.com.tripmeeting.domain.entity.Job;
import tripmeeting.com.tripmeeting.domain.entity.User;
import tripmeeting.com.tripmeeting.repository.area_code.AreaCodeRepository;
import tripmeeting.com.tripmeeting.repository.hobby.HobbyRepository;
import tripmeeting.com.tripmeeting.repository.job.JobRepository;
import tripmeeting.com.tripmeeting.repository.user.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final HobbyRepository hobbyRepository;
    private final AreaCodeRepository areaCodeRepository;

    public UserService(UserRepository userRepository,
                       JobRepository jobRepository,
                       HobbyRepository hobbyRepository,
                       AreaCodeRepository areaCodeRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.hobbyRepository = hobbyRepository;
        this.areaCodeRepository = areaCodeRepository;
    }

    public UserDto findUnique(String userId){
        return UserDto.mapFromRelation(userRepository.findUserById(userId));
    }

    public void create(CreateUserDto dto){
        AreaCode areaCode = areaCodeRepository.findAreaCodeByAreaCode(dto.getAreaCode());
        Job job = jobRepository.findJobById(dto.getJobId());
        Set<Hobby> hobbies = new HashSet<>(hobbyRepository.findAllById(dto.getHobbyIds()));
        userRepository.save(User.mapFromDto(dto, areaCode,job, hobbies));
    }

    public void patch(String userId, PatchUserDto dto){
        Set<Hobby> hobbies = null;
        User user = userRepository.findUserById(userId);
        Job job = jobRepository.findJobById(dto.getJobId());
        if(dto.getHobbyIds() != null)
            hobbies = new HashSet<>(hobbyRepository.findAllById(dto.getHobbyIds()));
        user.patch(dto, job, hobbies);
        userRepository.save(user);
    }

    public void delete(String userId){
        userRepository.deleteById(userId);
    }
}

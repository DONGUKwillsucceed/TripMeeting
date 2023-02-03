package tripmeeting.com.tripmeeting.service.user;

import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.user.dto.CreateUserDto;
import tripmeeting.com.tripmeeting.controller.user.dto.UserDto;
import tripmeeting.com.tripmeeting.domain.entity.Hobby;
import tripmeeting.com.tripmeeting.domain.entity.Job;
import tripmeeting.com.tripmeeting.domain.entity.User;
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

    public UserService(UserRepository userRepository, JobRepository jobRepository, HobbyRepository hobbyRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.hobbyRepository = hobbyRepository;
    }

    public UserDto findUnique(String userId){
        return UserDto.mapFromRelation(userRepository.findUserById(userId));
    }

    public void create(CreateUserDto dto){
        Job job = jobRepository.findJobById(dto.getJobId());
        Set<Hobby> hobbies = new HashSet<>(hobbyRepository.findAllById(dto.getHobbyIds()));
        userRepository.save(User.mapFromDto(dto, job, hobbies));
    }
}

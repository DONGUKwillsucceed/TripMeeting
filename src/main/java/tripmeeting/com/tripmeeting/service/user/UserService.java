package tripmeeting.com.tripmeeting.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.user.dto.CreateProfileImageDto;
import tripmeeting.com.tripmeeting.controller.user.dto.CreateUserDto;
import tripmeeting.com.tripmeeting.controller.user.dto.PatchUserDto;
import tripmeeting.com.tripmeeting.controller.user.dto.UserDto;
import tripmeeting.com.tripmeeting.domain.entity.*;
import tripmeeting.com.tripmeeting.domain.service.S3Service;
import tripmeeting.com.tripmeeting.repository.area_code.AreaCodeRepository;
import tripmeeting.com.tripmeeting.repository.hobby.HobbyRepository;
import tripmeeting.com.tripmeeting.repository.image.ImageRepository;
import tripmeeting.com.tripmeeting.repository.job.JobRepository;
import tripmeeting.com.tripmeeting.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service @Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final HobbyRepository hobbyRepository;
    private final AreaCodeRepository areaCodeRepository;
    private final S3Service s3Service;

    private final ImageRepository imageRepository;

    public UserService(UserRepository userRepository,
                       JobRepository jobRepository,
                       HobbyRepository hobbyRepository,
                       AreaCodeRepository areaCodeRepository,
                       ImageRepository imageRepository,
                       S3Service s3Service) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.hobbyRepository = hobbyRepository;
        this.areaCodeRepository = areaCodeRepository;
        this.imageRepository = imageRepository;
        this.s3Service = s3Service;
    }

    public UserDto findUnique(String userId){
        User user = userRepository.findUserById(userId);

        List<UserImage> profileImages = new ArrayList<>();
        for (UserImage userImage : user.userImages) {
            if(userImage.getIsDeleted() == 1)
                continue;

            String url = s3Service.getObjectUrl(userImage.getUrl());
            UserImage profileImage = UserImage.mapFromDto(userImage.getId(), url);
            profileImages.add(profileImage);
        }

        return UserDto.mapFromRelation(user, profileImages);
    }

    public void createProfileImage(String userId, CreateProfileImageDto dto){
        UserImage image = imageRepository.findUserImageById(dto.getId());
        User user = userRepository.findUserById(userId);
        image.updateUser(user);
        imageRepository.save(image);
    }

    public void create(CreateUserDto dto){
        AreaCode areaCode = areaCodeRepository.findAreaCodeByAreaCode(dto.getAreaCode());
        List<UserImage> images = imageRepository.findAllById(dto.getProfileImageIds());
        Job job = jobRepository.findJobById(dto.getJobId());
        Set<Hobby> hobbies = new HashSet<>(hobbyRepository.findAllById(dto.getHobbyIds()));

        User user = userRepository.save(User.mapFromDto(dto, areaCode,job, hobbies, null));
        for(UserImage image : images){
            image.updateUser(user);
            imageRepository.save(image);
        }
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
        User user = userRepository.findUserById(userId);
        user.delete();
        userRepository.save(user);
    }

    public void deleteProfileImage(String imageId) {
        UserImage image = imageRepository.findUserImageById(imageId);
        image.delete();
        imageRepository.save(image);
    }
}

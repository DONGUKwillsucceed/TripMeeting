package tripmeeting.com.tripmeeting.service.image;

import org.springframework.stereotype.Service;
import tripmeeting.com.tripmeeting.controller.image.dto.UploadUrlDto;
import tripmeeting.com.tripmeeting.domain.entity.UserImage;
import tripmeeting.com.tripmeeting.domain.service.S3Service;
import tripmeeting.com.tripmeeting.repository.image.ImageRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ImageService {
    private final S3Service s3Service;
    private final ImageRepository userImageRepository;
    public ImageService(ImageRepository userImageRepository, S3Service s3Service){
        this.s3Service = s3Service;
        this.userImageRepository = userImageRepository;
    }

    public Set<UploadUrlDto> getUploadUrlForProfile(int cnt){
        Set<UploadUrlDto> dtos = new HashSet<>();
        for(int n = 0; n < cnt; n++){
            String id = UUID.randomUUID().toString();
            String s3Path = "profile/" + id;
            String url = s3Service.getUploadUrl(s3Path);
            UploadUrlDto dto = UploadUrlDto.mapFromRelation(id, url);
            dtos.add(dto);
            userImageRepository.save(UserImage.mapFromDto(id,s3Path));
        }
        return dtos;
    }

    public Set<UploadUrlDto> getUploadUrlForChatting(int cnt){
        Set<UploadUrlDto> dtos = new HashSet<>();
        for(int n = 0; n < cnt; n++){
            String id = UUID.randomUUID().toString();
            String s3Path = "chatting/" + id;
            String url = s3Service.getUploadUrl(s3Path);
            UploadUrlDto dto = UploadUrlDto.mapFromRelation(id, url);
            dtos.add(dto);
            userImageRepository.save(UserImage.mapFromDto(id,s3Path));
        }
        return dtos;
    }
}

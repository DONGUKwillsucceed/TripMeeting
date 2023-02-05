package tripmeeting.com.tripmeeting.controller.image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripmeeting.com.tripmeeting.controller.image.dto.UploadUrlDto;
import tripmeeting.com.tripmeeting.service.image.ImageService;

import java.util.Set;

@RestController
@Slf4j @RequestMapping("image")
public class ImageController {
    private final ImageService imageService;
    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @GetMapping("profile/upload-url")
    Set<UploadUrlDto> getUploadUrlForProfile(@RequestParam(name = "cnt", required = false) @DefaultValue("1") int cnt){
        return imageService.getUploadUrlForProfile(cnt);
    }

    @GetMapping("chatting/upload-url")
    Set<UploadUrlDto> getUploadUrlForChatting(@RequestParam(name = "cnt", required = false) @DefaultValue("1") int cnt){
        return imageService.getUploadUrlForChatting(cnt);
    }
}

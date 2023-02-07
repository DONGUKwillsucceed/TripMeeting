package tripmeeting.com.tripmeeting.controller.user;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tripmeeting.com.tripmeeting.controller.user.dto.CreateProfileImageDto;
import tripmeeting.com.tripmeeting.controller.user.dto.CreateUserDto;
import tripmeeting.com.tripmeeting.controller.user.dto.PatchUserDto;
import tripmeeting.com.tripmeeting.controller.user.dto.UserDto;
import tripmeeting.com.tripmeeting.exception.exception.NotFoundException;
import tripmeeting.com.tripmeeting.service.user.UserService;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {
    UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public UserDto findUnique(@PathVariable("userId") String userId) throws NotFoundException {
        return userService.findUnique(userId);
    }

    @PatchMapping("{userId}/modify")
    public void patch(@PathVariable("userId") String userId, @Valid @RequestBody PatchUserDto dto) throws NotFoundException {
        userService.patch(userId, dto);
    }

    @PostMapping("{userId}/image/profile/create")
    public void createProfileImage(@PathVariable("userId") String userId, @Valid @RequestBody CreateProfileImageDto dto) throws NotFoundException {
        userService.createProfileImage(userId, dto);
    }

    @PostMapping("create")
    public void create(@Valid @RequestBody CreateUserDto dto) throws NotFoundException {
        userService.create(dto);
    }

    @DeleteMapping("{userId}")
    public void delete(@PathVariable("userId") String userId) throws NotFoundException {
        userService.delete(userId);
    }

    @DeleteMapping("{userId}/image/profile/{imageId}")
    public void deleteProfileImage(@PathVariable("imageId") String imageId) throws NotFoundException {
        userService.deleteProfileImage(imageId);
    }
}

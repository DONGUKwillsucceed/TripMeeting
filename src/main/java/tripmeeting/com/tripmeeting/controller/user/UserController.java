package tripmeeting.com.tripmeeting.controller.user;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tripmeeting.com.tripmeeting.controller.user.dto.CreateUserDto;
import tripmeeting.com.tripmeeting.controller.user.dto.UserDto;
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
    public UserDto findUnique(@PathVariable("userId") String userId){
        return userService.findUnique(userId);
    }

    @PostMapping("create")
    public void create(@Valid @RequestBody CreateUserDto dto){
        userService.create(dto);
    }
}

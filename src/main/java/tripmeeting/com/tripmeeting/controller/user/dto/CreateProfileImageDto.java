package tripmeeting.com.tripmeeting.controller.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
public class CreateProfileImageDto {
    @NotEmpty
    String id;
}

package tripmeeting.com.tripmeeting.controller.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CreateUserDto {
    @NotEmpty(message = "name is necessary")
    @Size(max = 255)
    String name;

    @NotNull(message = "age is necessary")
    int age;

    @NotEmpty(message = "area code is necessary")
    String areaCode;

    @NotEmpty(message = "job id is necessary")
    String jobId;

    @NotEmpty(message = "description is necessary")
    @Size(max = 300)
    String description;

    String kakaoId;
    String naverId;

    @NotEmpty(message = "hobby ids is necessary")
    ArrayList<String> hobbyIds;
}

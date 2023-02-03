package tripmeeting.com.tripmeeting.controller.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class PatchUserDto {
    @Size(max = 255)
    String name;

    int age;

    String areaCode;

    String jobId;

    @Size(max = 300)
    String description;

    ArrayList<String> hobbyIds;
}

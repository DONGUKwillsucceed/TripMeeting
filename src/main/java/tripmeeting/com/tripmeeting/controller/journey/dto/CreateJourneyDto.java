package tripmeeting.com.tripmeeting.controller.journey.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateJourneyDto {

    @NotEmpty(message = "name is necessary")
    String name;
    @NotEmpty(message = "description is necessary")
    String description;

    @NotNull(message = "max is necessary")
    int max;
    @NotEmpty(message = "area code is necessary")
    String areaCode;
    @Length(max = 5, min = 1, message = "Badly formed password")
    String password;

    @NotNull(message = "start date is necessary")
    Date startDate;
    @NotNull(message = "end date is necessary")
    Date endDate;

    @NotEmpty(message = "user Id is necessary")
    String userId;
}

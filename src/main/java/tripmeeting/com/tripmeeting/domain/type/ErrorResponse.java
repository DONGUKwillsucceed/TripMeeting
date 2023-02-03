package tripmeeting.com.tripmeeting.domain.type;

import lombok.*;

import java.util.Date;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponse{
    @NonNull
    private String message;
    private Date timestamp = new Date();
    private StackTraceElement[] stack;

}
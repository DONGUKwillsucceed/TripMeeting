package tripmeeting.com.tripmeeting.exception.exception;

public class UnAuthorizationError extends Exception{
    public UnAuthorizationError(String errorMessage){
        super(errorMessage);
    }
}

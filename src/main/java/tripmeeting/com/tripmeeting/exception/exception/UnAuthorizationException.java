package tripmeeting.com.tripmeeting.exception.exception;

public class UnAuthorizationException extends Exception{
    public UnAuthorizationException(String errorMessage){
        super(errorMessage);
    }
}

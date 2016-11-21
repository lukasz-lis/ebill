package pl.eightbit.exceptions;


public class MemberAlreadyExists extends RuntimeException {

    public MemberAlreadyExists(String message) {
        super(message);
    }

    public MemberAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}

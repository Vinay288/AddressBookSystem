package AddressBookSystem;

public class IOServiceException extends RuntimeException {
    enum ExceptionType {
        ENTERED_WRONG
    }

    ExceptionType type;

    public IOServiceException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}

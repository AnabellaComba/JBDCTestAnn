package utilities;

public class ServiceException extends Exception {
    // For serialization
    private static final long serialVersionUID = 1L;

    // Error code for client handling
    private final String errorCode;

    // Constructors
    public ServiceException(String message) {
        super(message);
        this.errorCode = "GENERIC_ERROR";
    }

    public ServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "GENERIC_ERROR";
    }

    public ServiceException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    // Getters
    public String getErrorCode() {
        return errorCode;
    }
}
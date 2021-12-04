package be.atc.salesmanagercrm.exceptions;

import lombok.Getter;

public class AccessDeniedException extends RuntimeException {

    @Getter
    private ErrorCodes errorCodes;

    /**
     * Access Denied Exception
     *
     * @param message String
     */
    public AccessDeniedException(String message) {
        super(message);
    }

    /**
     * Access Denied Exception
     *
     * @param message String
     * @param cause   Throwable
     */
    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Access Denied Exception
     *
     * @param message    String
     * @param cause      Throwable
     * @param errorCodes ErrorCodes
     */
    public AccessDeniedException(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    /**
     * Access Denied Exception
     *
     * @param message    String
     * @param errorCodes ErrorCodes
     */
    public AccessDeniedException(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }
}

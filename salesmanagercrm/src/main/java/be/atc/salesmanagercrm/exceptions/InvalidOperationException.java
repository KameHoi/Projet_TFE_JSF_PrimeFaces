package be.atc.salesmanagercrm.exceptions;

import lombok.Getter;

public class InvalidOperationException extends RuntimeException {

    @Getter
    private ErrorCodes errorCodes;

    /**
     * Invalid Operation Exception
     *
     * @param message String
     */
    public InvalidOperationException(String message) {
        super(message);
    }

    /**
     * Invalid Operation Exception
     *
     * @param message String
     * @param cause   Throwable
     */
    public InvalidOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Invalid Operation Exception
     *
     * @param message    String
     * @param cause      Throwable
     * @param errorCodes ErrorCodes
     */
    public InvalidOperationException(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    /**
     * Invalid Operation Exception
     *
     * @param message    String
     * @param errorCodes ErrorCodes
     */
    public InvalidOperationException(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }
}
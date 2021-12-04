package be.atc.salesmanagercrm.exceptions;

import lombok.Getter;

import java.util.List;

public class InvalidEntityException extends RuntimeException {

    @Getter
    private ErrorCodes errorCodes;
    @Getter
    private List<String> errors;

    /**
     * Invalid Entity Exception
     *
     * @param message String
     */
    public InvalidEntityException(String message) {
        super(message);
    }

    /**
     * Invalid Entity Exception
     *
     * @param message String
     * @param cause   Throwable
     */
    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Invalid Entity Exception
     *
     * @param message    String
     * @param cause      Throwable
     * @param errorCodes ErrorCodes
     */
    public InvalidEntityException(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    /**
     * Invalid Entity Exception
     *
     * @param message    String
     * @param errorCodes ErrorCodes
     */
    public InvalidEntityException(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    /**
     * Invalid Entity Exception
     *
     * @param message    String
     * @param errorCodes ErrorCodes
     * @param errors     List<String>
     */
    public InvalidEntityException(String message, ErrorCodes errorCodes, List<String> errors) {
        super(message);
        this.errorCodes = errorCodes;
        this.errors = errors;
    }
}

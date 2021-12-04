package be.atc.salesmanagercrm.exceptions;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException {

    @Getter
    private ErrorCodes errorCodes;

    /**
     * Entity Not found exception
     *
     * @param message String
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Entity Not found exception
     *
     * @param message String
     * @param cause   Throwable
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Entity Not found exception
     *
     * @param message    String
     * @param cause      Throwable
     * @param errorCodes ErrorCodes
     */
    public EntityNotFoundException(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    /**
     * Entity Not found exception
     *
     * @param message    String
     * @param errorCodes ErrorCodes
     */
    public EntityNotFoundException(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }
}

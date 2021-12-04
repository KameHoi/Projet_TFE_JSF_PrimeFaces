package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.UsersEntity;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidOperationException;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Larche Marie-Élise
 */
@Slf4j
@FacesValidator("userEmailValidator")
public class UsersEmailFrontValidator implements Validator {

    @Getter
    @Setter
    private static Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * Validator for email
     *
     * @param context   FacesContext
     * @param component UIComponent
     * @param value     Object
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setEmail((String) value);


        try {
            validateLength(usersEntity);
        } catch (InvalidOperationException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            throw new ValidatorException(new FacesMessage(getMessageEmailError()));
        }

        try {
            checkEmailRegexe(usersEntity);
        } catch (InvalidOperationException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            throw new ValidatorException(new FacesMessage(getMessageEmailError()));
        }

    }

    /**
     * Return message for username already in DB
     *
     * @return jsf utils message
     */
    private String getMessageEmailError() {
        return JsfUtils.returnMessage(locale, "users.errorEmailRegex");
    }

    /**
     * Check if user exist in DB
     *
     * @param entity : UsersEntity
     */
    public void checkEmailRegexe(UsersEntity entity) {

        if (entity != null) {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{2,4}$");
            Matcher matcher = pattern.matcher(entity.getEmail());
            boolean bool = matcher.matches();

            if (!bool) {
                log.warn("wrong regex email", entity.getEmail());
                throw new InvalidOperationException(
                        "Votre adresse mail n'est pas valide " + entity.getEmail(), ErrorCodes.USER_BAD_EMAIL_REGEX
                );
            }
        }
    }

    /**
     * Validate lenth function
     *
     * @param entity UsersEntity
     */
    public void validateLength(UsersEntity entity) {
        if (entity.getEmail() != null && entity.getEmail().length() < 2) {
            log.warn("longueur trop courte", entity.getEmail());
            throw new InvalidOperationException(
                    "longueur trop courte " + entity.getEmail(), ErrorCodes.USER_BAD_EMAIL_REGEX
            );
        }
    }
}

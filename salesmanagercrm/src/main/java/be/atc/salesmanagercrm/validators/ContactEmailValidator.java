package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.ContactsEntity;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidOperationException;
import be.atc.salesmanagercrm.utils.JsfUtils;
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
 * @author Zabbara Maximilien
 */
@Slf4j
@FacesValidator("contactEmailValidator")
public class ContactEmailValidator implements Validator {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        if (value != null) {
            ContactsEntity contactsEntity = new ContactsEntity();
            contactsEntity.setEmail((String) value);

            try {
                checkEmail(contactsEntity);
            } catch (InvalidOperationException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageEmailError(), null));
            }
        }
    }

    /**
     * Return message for email syntax error
     *
     * @return jsf utils message
     */
    private String getMessageEmailError() {
        return JsfUtils.returnMessage(locale, "users.errorEmailRegex");
    }

    /**
     * Check email syntax
     *
     * @param contactsEntity ContactsEntity
     */
    public void checkEmail(ContactsEntity contactsEntity) {

        if (contactsEntity != null) {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{2,4}$");
            Matcher matcher = pattern.matcher(contactsEntity.getEmail());
            boolean bool = matcher.matches();

            if (!bool) {
                log.warn("wrong regex email " + contactsEntity.getEmail());
                throw new InvalidOperationException(
                        "Votre adresse mail n'est pas valide " + contactsEntity.getEmail(), ErrorCodes.USER_BAD_EMAIL_REGEX
                );
            }
        }
    }
}


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
 * @author Maximilien Zabbara
 */
@Slf4j
@FacesValidator("contactPhoneNumberValidator")
public class ContactPhoneNumberValidator implements Validator {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        if (value != null) {
            ContactsEntity contactsEntity = new ContactsEntity();
            contactsEntity.setPhoneNumber((String) value);

            try {
                checkPhoneNumber(contactsEntity);
            } catch (InvalidOperationException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessagePhoneNumberError(), null));
            }
        }
    }

    /**
     * Return message for wrong phone number
     *
     * @return jsf utils message
     */
    private String getMessagePhoneNumberError() {
        return JsfUtils.returnMessage(locale, "contacts.phoneNumberNotValid");
    }


    /**
     * Check if phone number is correct
     *
     * @param contactsEntity ContactsEntity
     */
    public void checkPhoneNumber(ContactsEntity contactsEntity) {

        log.info("Start of the checkPhoneNumber function");

        if (contactsEntity != null) {
            String regex = "^[\\+]?[(]?[0-9 ]{3}[)]?[0-9]{7,9}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(contactsEntity.getPhoneNumber());

            log.info(contactsEntity.getPhoneNumber());

            boolean matcherResult = matcher.matches();

            if (!matcherResult) {
                log.warn("Phone number : " + contactsEntity.getPhoneNumber() + " is not valid");
                throw new InvalidOperationException(
                        "L'url n'est pas valide " + contactsEntity.getPhoneNumber(), ErrorCodes.CONTACT_PHONE_NOT_VALID
                );
            }
        }
    }
}

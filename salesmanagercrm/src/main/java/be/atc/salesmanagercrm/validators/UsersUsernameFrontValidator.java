package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.beans.CheckEntities;
import be.atc.salesmanagercrm.entities.UsersEntity;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Locale;

/**
 * @author Larche Marie-Élise
 */
@Slf4j
@FacesValidator("userUsernameValidator")
public class UsersUsernameFrontValidator implements Validator {
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        CheckEntities checkEntities = new CheckEntities();

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername((String) value);

        try {
            checkEntities.checkUserByUsername(usersEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            throw new ValidatorException(new FacesMessage(getMessageErrorUsernameAlreadyExist()));
        }
    }

    /**
     * Return message for username already in DB
     *
     * @return jsf utils message
     */
    private String getMessageErrorUsernameAlreadyExist() {
        return JsfUtils.returnMessage(locale, "users.usernameExist");
    }

}



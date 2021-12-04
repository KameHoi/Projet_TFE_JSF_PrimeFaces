package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.beans.CheckEntities;
import be.atc.salesmanagercrm.entities.RolesEntity;
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

@Slf4j
@FacesValidator("DeleteRolesSafeValidator")
public class DeleteRolesSafeFrontValidator implements Validator {
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        CheckEntities checkEntities = new CheckEntities();
        log.info((String) value);

        RolesEntity rolesEntity = new RolesEntity();
        //trouver l'id a partir du label si c'est un label qu'on recoit
        // rolesEntity.setPassword((String) value);

        rolesEntity.setLabel((String) value);
        log.info(String.valueOf(rolesEntity));


            /* try {
                checkEntities.checkUserByUsername(usersEntity);
            } catch (InvalidEntityException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(getMessageErrorUsernameAlreadyExist()));
            }*/

        try {
            checkEntities.checkForSafeDeleteRole(rolesEntity);
        } catch (InvalidOperationException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            throw new ValidatorException(new FacesMessage(getMessageError()));
        }
    }


    /**
     * Return message for username already in DB
     *
     * @return jsf utils message
     */
    private String getMessageError() {
        return JsfUtils.returnMessage(locale, "roles.userActif");
    }


}

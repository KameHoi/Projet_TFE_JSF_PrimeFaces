package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.beans.CheckEntities;
import be.atc.salesmanagercrm.beans.ExtendBean;
import be.atc.salesmanagercrm.entities.RolesEntity;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
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


/**
 * @author Larche Marie-Élise
 */
@Slf4j
@FacesValidator("roleLabelValidator")
public class RoleLabelFrontValidator extends ExtendBean implements Validator {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        CheckEntities checkEntities = new CheckEntities();

        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setLabel((String) value);

        try {
            validateLength(rolesEntity);
        } catch (InvalidOperationException exception) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "role.warning1", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            throw new ValidatorException(new FacesMessage(getMessageTooShort()));
        }

        try {
            checkEntities.checkRoleByLabel(rolesEntity);
        } catch (InvalidEntityException exception) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "roles.labelExist", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            throw new ValidatorException(new FacesMessage(getMessageErrorLabelAlreadyExist()));
        }
    }

    /**
     * Return message for username already in DB
     *
     * @return jsf utils message
     */
    private String getMessageErrorLabelAlreadyExist() {
        return JsfUtils.returnMessage(locale, "roles.labelExist");
    }

    private String getMessageTooShort() {
        return JsfUtils.returnMessage(locale, "role.warning1");
    }

    public void validateLength(RolesEntity entity) {
        log.info(entity.getLabel().substring(0, 1));

        if (entity.getLabel() != null && entity.getLabel().charAt(0) == ' ') {
            log.warn("au moins 1 caractére", entity.getLabel());
            throw new InvalidOperationException(
                    "au moins 1 caractére " + entity.getLabel(), ErrorCodes.ROLES_NOT_VALID
            );
        }
    }

}

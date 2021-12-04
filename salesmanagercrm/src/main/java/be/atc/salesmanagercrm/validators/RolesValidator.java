package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.RolesEntity;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * @author Larché Marie-Élise
 */
@Slf4j
public class RolesValidator {

    public static List<String> validate(RolesEntity entity) {
        FacesMessage msg;
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        List<String> errors = new ArrayList<>();


        log.info(String.valueOf(entity));
        if (entity == null) {
            errors.add("La reception des données à échouée");
            errors.add("Veuillez recommencer votre inscription");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "role.empty"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return errors;
        }
        if (entity.getLabel() == null || entity.getLabel().isEmpty() || entity.getLabel() == " ") {
            errors.add("Le nom de rôle est vide");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "role.empty"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return errors;
    }


}

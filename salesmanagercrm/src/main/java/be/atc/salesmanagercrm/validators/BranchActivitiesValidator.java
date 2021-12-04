package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.BranchActivitiesEntity;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Maximilien Zabbara
 */
@Slf4j
public class BranchActivitiesValidator {

    public static List<String> validate(BranchActivitiesEntity branchActivitiesEntity) {

        List<String> errors = new ArrayList<>();

        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        FacesMessage msg;

        if (branchActivitiesEntity == null) {
            errors.add("Le secteur d'activité est obligatoire !");
            msg = new FacesMessage(JsfUtils.returnMessage(locale, "branchActivities.labelError"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return errors;
        }
        if (branchActivitiesEntity.getLabel() == null || branchActivitiesEntity.getLabel().isEmpty()) {
            errors.add("Le secteur d'activité est obligatoire !");
            msg = new FacesMessage(JsfUtils.returnMessage(locale, "branchActivities.labelError"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return errors;

    }

}

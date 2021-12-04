package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.JobTitlesEntity;
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
public class JobTitlesValidator {

    public static List<String> validate(JobTitlesEntity jobTitlesEntity) {

        List<String> errors = new ArrayList<>();

        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        FacesMessage msg;


        if (jobTitlesEntity == null) {
            errors.add("L'intitulé du poste est obligatoire !");
            msg = new FacesMessage(JsfUtils.returnMessage(locale, "jobTitles.labelError"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return errors;
        }
        if (jobTitlesEntity.getLabel() == null || jobTitlesEntity.getLabel().isEmpty()) {
            errors.add("L'intitulé du poste est obligatoire !");
            msg = new FacesMessage(JsfUtils.returnMessage(locale, "jobTitles.labelError"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return errors;
    }
}

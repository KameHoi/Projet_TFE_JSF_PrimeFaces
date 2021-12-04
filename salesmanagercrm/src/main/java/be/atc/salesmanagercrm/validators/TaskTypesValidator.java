package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.TaskTypesEntity;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.Getter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Younes Arifi
 */
public class TaskTypesValidator {
    @Getter
    private static final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * Validate TaskTypesEntity Backend and return list of Errors
     *
     * @param entity TaskTypesEntity
     * @return List<String>
     */
    public static List<String> validate(TaskTypesEntity entity) {
        List<String> errors = new ArrayList<>();
        String errorMessage = null;

        if (entity == null) {
            errors.add("Veuillez renseigner un label dans votre type tache");

            errorMessage = JsfUtils.returnMessage(getLocale(), "taskTypes.validator.title") + "\n" + JsfUtils.returnMessage(getLocale(), "taskTypes.validator.title");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return errors;
        }
        if (entity.getLabel() == null || entity.getLabel().isEmpty()) {
            errors.add("Veuillez renseigner un label dans votre type tache !");

            errorMessage = JsfUtils.returnMessage(getLocale(), "taskTypes.validator.title") + "\n";
        }
        if (entity.getLabel().length() > 255) {
            errors.add("Le nombre de caractere du label doit etre inferieur ou egale a 255");

            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "taskTypes.validator.titleLength") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "taskTypes.validator.titleLength") + "\n";
        }
        if (errorMessage != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return errors;
    }
}

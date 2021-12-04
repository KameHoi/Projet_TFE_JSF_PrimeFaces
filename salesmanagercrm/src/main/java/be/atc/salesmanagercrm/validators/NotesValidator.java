package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.NotesEntity;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Younes Arifi
 */
@Slf4j
public class NotesValidator {

    @Getter
    @Setter
    private static Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * Validate NotesEntity Backend and return list of Errors
     *
     * @param entity NotesEntity
     * @return List<String>
     */
    public static List<String> validate(NotesEntity entity) {

        List<String> errors = new ArrayList<>();
        String errorMessage = null;

        if (entity == null) {
            errors.add("L utilisateur n existe pas !");
            errors.add("Veuillez renseigner un message dans votre note");

            errorMessage = JsfUtils.returnMessage(getLocale(), "userNotExist") + "\n" + JsfUtils.returnMessage(getLocale(), "notes.validator.message");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return errors;
        }
        if (entity.getUsersByIdUsers() == null) {
            errors.add("L utilisateur n existe pas !");

            errorMessage = JsfUtils.returnMessage(getLocale(), "userNotExist") + "\n";
        }
        if (entity.getMessage() == null || entity.getMessage().isEmpty()) {
            errors.add("Veuillez renseigner un message dans votre note");

            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "notes.validator.message") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "notes.validator.message") + "\n";
        }

        if (entity.getMessage().length() > 255) {
            errors.add("Le nombre de caractere du message doit etre inferieur ou egale a 255");

            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "notes.validator.messageLength") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "notes.validator.messageLength") + "\n";
        }

        if (errorMessage != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return errors;
    }
}

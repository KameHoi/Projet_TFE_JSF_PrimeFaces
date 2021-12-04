package be.atc.salesmanagercrm.validators;


import be.atc.salesmanagercrm.entities.ConversationsEntity;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.Getter;
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
public class ConversationsValidator {
    @Getter
    private static final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public static List<String> validate(ConversationsEntity entity) {
        FacesMessage msg;
        String errorMessage = null;

        List<String> errors = new ArrayList<>();

        log.info(String.valueOf(entity));
        if (entity == null) {
            errors.add("La reception des données à échouée");
            errors.add("Veuillez recommencer votre message");
            errorMessage = JsfUtils.returnMessage(getLocale(), "message.empty") + "\n" + JsfUtils.returnMessage(getLocale(), "message.empty");

            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return errors;
        }
        if (entity.getMessage() == null || entity.getMessage().isEmpty()) {
            errors.add("Le message est vide");
            errorMessage = JsfUtils.returnMessage(getLocale(), "message.empty");
        }
        if (entity.getMessage().length() > 255) {
            errors.add("Le nombre de caractere de la description doit etre inferieur ou egale a 255");

            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "validator.conversationLength") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "validator.conversationLength") + "\n";
        }
        if (errorMessage != null) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return errors;
    }

    public static List<String> validateEntity(ConversationsEntity entity) {
        List<String> errors = new ArrayList<>();

        log.info(String.valueOf(entity));
        if (entity == null) {
            errors.add("La reception des données à échouée");
            errors.add("Veuillez recommencer votre inscription");
            return errors;
        }

        return errors;
    }

}

package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.TransactionsEntity;
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
public class TransactionsValidator {
    @Getter
    private static final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * Validate TransactionsEntity Backend and return list of Errors
     *
     * @param entity TransactionsEntity
     * @return List<String>
     */
    public static List<String> validate(TransactionsEntity entity) {
        List<String> errors = new ArrayList<>();
        String errorMessage = null;

        if (entity == null) {
            errors.add("L utilisateur n existe pas !");
            errors.add("Veuillez renseigner un un titre dans votre transaction");
            errors.add("Veuillez renseigner un nombre positif dans votre transaction");

            errorMessage = JsfUtils.returnMessage(getLocale(), "userNotExist") + "\n" + JsfUtils.returnMessage(getLocale(), "transactions.validator.title") + "\n" + JsfUtils.returnMessage(getLocale(), "transactions.validator.amount");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return errors;
        }
        if (entity.getUsersByIdUsers() == null) {
            errors.add("L utilisateur n existe pas !");

            errorMessage = JsfUtils.returnMessage(getLocale(), "userNotExist") + "\n";
        }
        if (entity.getTitle() == null || entity.getTitle().isEmpty()) {
            errors.add("Veuillez renseigner un titre dans votre transaction");

            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "transactions.validator.title") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "transactions.validator.title") + "\n";
        }
        if (entity.getTitle().length() > 255) {
            errors.add("Le nombre de caractere du titre doit etre inferieur ou egale a 255");

            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "validator.titleLength") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "validator.titleLength") + "\n";
        }
        if (entity.getAmount() != null) {
            if (entity.getAmount() < 0) {
                errors.add("Veuillez renseigner un nombre positif dans votre transaction");

                errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "transactions.validator.amount") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "transactions.validator.amount") + "\n";
            }
        }

        if (errorMessage != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return errors;
    }
}

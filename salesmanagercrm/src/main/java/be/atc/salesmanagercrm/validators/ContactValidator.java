package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.ContactsEntity;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.extern.slf4j.Slf4j;
import nl.garvelink.iban.Modulo97;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Maximilien Zabbara
 */
@Slf4j
public class ContactValidator {

    public static List<String> validate(ContactsEntity contactsEntity) {

        List<String> errors = new ArrayList<>();

        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        FacesMessage msg;

        if (contactsEntity == null) {
            errors.add("Le prénom est obligatoire");
            errors.add("Le nom est obligatoire");

            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contacts.firstnameNotValid"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return errors;
        }

        if (!(contactsEntity.getEmail().equals(""))) {
            if (!(validateEmail(contactsEntity))) {
                errors.add("L'adresse email doit être valide");
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contacts.emailNotValid"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

        if (contactsEntity.getFirstname() == null || contactsEntity.getFirstname().isEmpty()) {
            errors.add("Le prénom est obligatoire");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contacts.firstnameNotValid"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        if (contactsEntity.getLastname() == null || contactsEntity.getLastname().isEmpty()) {
            errors.add("Le nom est obligatoire");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contacts.lastnameNotValid"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        if (contactsEntity.getContactTypesByIdContactTypes() == null) {
            errors.add("Veuillez choisir un type de contact");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contacts.ContactTypeNotValid"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        if (!(contactsEntity.getPhoneNumber().equals(""))) {
            if (!(validatePhoneNumber(contactsEntity))) {
                errors.add("Le prénom est obligatoire");
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contacts.phoneNumberNotValid"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

        if (contactsEntity.getBankAccount() != null && !(contactsEntity.getBankAccount().isEmpty())) {
            if (!(validateBankAccount(contactsEntity))) {
                if (!(checkBankAccountValidity(contactsEntity))) {
                    errors.add("Le numéro de compte doit être valide !");
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "company.BankAccountNotValid"), null);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
        }

        return errors;
    }

    /**
     * Check if email is correct
     *
     * @param contactsEntity contactsEntity
     * @return boolean
     */
    public static boolean validateEmail(ContactsEntity contactsEntity) {
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(contactsEntity.getEmail());

        return matcher.find();
    }

    /**
     * Check if phoneNumber is correct
     *
     * @param contactsEntity contactsEntity
     * @return boolean
     */
    public static boolean validatePhoneNumber(ContactsEntity contactsEntity) {

        String regex = "^[\\+]?[(]?[0-9 ]{3}[)]?[0-9]{7,9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contactsEntity.getPhoneNumber());

        return matcher.find();
    }

    /**
     * Check if bank account format if correct
     *
     * @param contactsEntity contactsEntity
     * @return boolean
     */
    public static boolean validateBankAccount(ContactsEntity contactsEntity) {

        String regex = "BE-[a-zA-Z0-9]{2}\\s?([0-9]{4}\\s?){3}\\s?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contactsEntity.getBankAccount());

        log.info("Résultat : " + matcher.find());

        return matcher.find();
    }

    /**
     * Check if bank account is valid
     *
     * @param contactsEntity ContactsEntity
     * @return boolean
     */
    public static boolean checkBankAccountValidity(ContactsEntity contactsEntity) {
        log.info("Start of checkBankAccountValidity function");

        String companyIban = contactsEntity.getBankAccount();

        String companyIbanReplaced = companyIban.replace("-", "");

        log.info("Iban replaced : " + companyIbanReplaced);

        return Modulo97.verifyCheckDigits(companyIbanReplaced);
    }

}

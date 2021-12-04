package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.dao.UsersDao;
import be.atc.salesmanagercrm.dao.impl.UsersDaoImpl;
import be.atc.salesmanagercrm.entities.UsersEntity;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Larché Marie-Élise
 */
@Slf4j
public class UsersValidator {

    @Getter
    private static final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Getter
    @Setter
    private static UsersDao dao = new UsersDaoImpl();

    public static List<String> validate(UsersEntity entity) {
        List<String> errors = new ArrayList<>();
        String errorMessage = null;

        log.info(String.valueOf(entity));

        if (entity == null) {
            errors.add("La reception des données à échouée");
            errors.add("Veuillez recommencer votre inscription");
            errorMessage = JsfUtils.returnMessage(getLocale(), "userNotExist");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return errors;
        }
        if (entity.getRolesByIdRoles() == null) {
            errors.add("Le rôle est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "roleEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "roleEmpty") + "\n";
        }
        if (entity.getLastname() == null || entity.getLastname().isEmpty()) {
            errors.add("Le nom de famille est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "lastnameEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "lastnameEmpty") + "\n";
        }
        if (entity.getFirstname() == null || entity.getFirstname().isEmpty()) {
            errors.add("Le prénom est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "firstnameEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "firstnameEmpty") + "\n";
        }
        if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
            errors.add("L'email ne peut pas être vide'");
            errorMessage = JsfUtils.returnMessage(getLocale(), "mailEmpty") + "\n";
        } else if (!validateEmail(entity)) {
            errors.add("Votre email n'est pas valide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "mailRegex") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "mailRegex") + "\n";
        }

        if (errorMessage != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return errors;
    }

    public static List<String> validateUpdateByAdmin(UsersEntity entity) {
        List<String> errors = new ArrayList<>();

        log.info(String.valueOf(entity));
        String errorMessage = null;

        if (entity == null) {
            errors.add("La reception des données à échouée");
            errors.add("Veuillez recommencer votre inscription");
            errorMessage = JsfUtils.returnMessage(getLocale(), "userNotExist") + "\n";
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return errors;
        }
        if (entity.getRolesByIdRoles() == null) {
            errors.add("Le rôle est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "roleEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "roleEmpty") + "\n";
        }
        if (entity.getLastname() == null || entity.getLastname().isEmpty()) {
            errors.add("Le nom de famille est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "lastnameEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "lastnameEmpty") + "\n";
        }
        if (entity.getFirstname() == null || entity.getFirstname().isEmpty()) {
            errors.add("Le prénom est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "firstnameEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "firstnameEmpty") + "\n";
        }
        if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
            errors.add("L'email ne peut pas être vide'");
            errorMessage = JsfUtils.returnMessage(getLocale(), "mailEmpty") + "\n";
        } else if (!validateEmail(entity)) {
            errors.add("Votre email n'est pas valide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "mailRegex") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "mailRegex") + "\n";
        }

        if (errorMessage != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return errors;
    }

    public static List<String> validateUpdateByAdminNoChange(UsersEntity entity) {
        List<String> errors = new ArrayList<>();

        log.info(String.valueOf(entity));
        String errorMessage = null;

        if (entity == null) {
            errors.add("La reception des données à échouée");
            errors.add("Veuillez recommencer votre inscription");

            errorMessage = JsfUtils.returnMessage(getLocale(), "userNotExist") + "\n";
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return errors;
        }
        if (entity.getRolesByIdRoles() == null) {
            errors.add("Le rôle est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "roleEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "roleEmpty") + "\n";
        }
        if (entity.getLastname() == null || entity.getLastname().isEmpty()) {
            errors.add("Le nom de famille est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "lastnameEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "lastnameEmpty") + "\n";
        }
        if (entity.getFirstname() == null || entity.getFirstname().isEmpty()) {
            errors.add("Le prénom est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "firstnameEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "firstnameEmpty") + "\n";
        }
        if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
            errors.add("L'email ne peut pas être vide'");
            errorMessage = JsfUtils.returnMessage(getLocale(), "mailEmpty") + "\n";
        } else if (!validateEmail(entity)) {
            errors.add("Votre email n'est pas valide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "mailRegex") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "mailRegex") + "\n";
        }

        if (errorMessage != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }


        return errors;
    }

    public static List<String> validateUpdateByUser(UsersEntity entity, String pass2, String pass1) {
        List<String> errors = new ArrayList<>();
        String errorMessage = null;

        log.info(String.valueOf(entity));

        if (entity == null) {
            errors.add("La reception des données à échouée");
            errors.add("Veuillez recommencer votre inscription");
            errorMessage = JsfUtils.returnMessage(getLocale(), "userNotExist") + "\n";
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return errors;
        }

        if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
            errors.add("L'email ne peut pas être vide'");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "passwordEmty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "passwordEmty") + "\n";
        } else if (!validateEmail(entity)) {
            errors.add("Votre email n'est pas valide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "mailRegex") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "mailRegex") + "\n";
        }
        log.info(pass1);
        if ((pass1 != null && !pass1.equals("")) || (pass2 != null && !pass2.equals(""))) {
            if (!validatePassword(pass1) || !validatePassword(pass2)) {
                errors.add("Votre mot de passe doit faire 8 caractéres, posséder une majuscule et un chiffre ou un caractére spécial");
                errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "passwordBadRegex") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "passwordBadRegex") + "\n";
            } else if (!vaidateMatchPassword(pass1, pass2)) {
                errors.add("Vos mots de passe doivent être identique");
                errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "passwordBadRegex") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "passwordBadRegex") + "\n";
            }
        }

        if (errorMessage != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }


        return errors;
    }


    public static boolean validatePassword(String password) {
        String regex = "^^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?])[A-Za-z\\d@$!%*?/.^&*_=+>)]{8,}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }


    public static boolean validateEmail(UsersEntity entity) {
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(entity.getEmail());

        return matcher.find();
    }

    public static boolean vaidateMatchPassword(String pass, String pass2) {
        return pass.equals(pass2);
    }

    public static List<String> connection(UsersEntity entity, String passwordCo) {
        List<String> errors = new ArrayList<>();
        String errorMessage = null;

        log.info(String.valueOf(entity));

        if (entity == null) {
            errors.add("La reception des données à échouée");
            errors.add("Veuillez recommencer votre inscription");
            errorMessage = JsfUtils.returnMessage(getLocale(), "userNotExist") + "\n";
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return errors;
        }
        if (entity.getUsername() == null || entity.getUsername().isEmpty()) {
            errors.add("Le pseudo est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "usernameEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "usernameEmpty") + "\n";
        }
        if (passwordCo == null || passwordCo.isEmpty()) {
            errors.add("Le mot de passe est vide");
            errorMessage = errorMessage == null ? JsfUtils.returnMessage(getLocale(), "passwordEmpty") + "\n" : errorMessage + JsfUtils.returnMessage(getLocale(), "passwordEmpty") + "\n";
        }

        if (errorMessage != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return errors;
    }


}

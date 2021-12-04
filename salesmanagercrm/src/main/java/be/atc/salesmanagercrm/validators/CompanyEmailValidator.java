package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.CompaniesEntity;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Maximilien Zabbara
 */
@Slf4j
@FacesValidator("companyEmailValidator")
public class CompanyEmailValidator implements Validator {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        if (value != null) {
            CompaniesEntity companiesEntity = new CompaniesEntity();
            companiesEntity.setEmail((String) value);

            try {
                checkEmail(companiesEntity);
            } catch (InvalidOperationException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageEmailError(), null));
            }
        }
    }

    /**
     * Return message for email syntax error
     *
     * @return jsf utils message
     */
    private String getMessageEmailError() {
        return JsfUtils.returnMessage(locale, "users.errorEmailRegex");
    }

    /**
     * Check email syntax
     *
     * @param companiesEntity CompaniesEntity
     */
    public void checkEmail(CompaniesEntity companiesEntity) {

        if (companiesEntity != null) {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{2,4}$");
            Matcher matcher = pattern.matcher(companiesEntity.getEmail());
            boolean bool = matcher.matches();

            if (!bool) {
                log.warn("wrong regex email " + companiesEntity.getEmail());
                throw new InvalidOperationException(
                        "Votre adresse mail n'est pas valide " + companiesEntity.getEmail(), ErrorCodes.USER_BAD_EMAIL_REGEX
                );
            }
        }
    }
}

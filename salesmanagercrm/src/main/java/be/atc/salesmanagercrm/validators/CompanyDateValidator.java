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
 * @author Zabbara Maximilien
 */
@Slf4j
@FacesValidator("companyDateValidator")
public class CompanyDateValidator implements Validator {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        if (value != null) {
            CompaniesEntity companiesEntity = new CompaniesEntity();
            companiesEntity.setCreationDate((Integer) value);

            try {
                checkDate(companiesEntity);
            } catch (InvalidOperationException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageCreationDateError(), null));
            }
        }
    }

    /**
     * Return message for email syntax error
     *
     * @return jsf utils message
     */
    private String getMessageCreationDateError() {
        return JsfUtils.returnMessage(locale, "company.creationDateNotValid");
    }


    /**
     * Check if creation date is correct
     *
     * @param companiesEntity CompaniesEntity
     */
    public void checkDate(CompaniesEntity companiesEntity) {

        if (companiesEntity != null) {

            Pattern pattern = Pattern.compile("^[0-9]{4}$");

            Matcher matcher = pattern.matcher(String.valueOf(companiesEntity.getCreationDate()));

            boolean bool = matcher.matches();

            if (!bool) {
                log.warn("Creation date " + companiesEntity.getCreationDate() + " is not correct");
                throw new InvalidOperationException(
                        "L'année de création entrée n'est pas bonne " + companiesEntity.getCreationDate(), ErrorCodes.COMPANY_CREATIONDATE_NOT_VALID
                );
            }
        }
    }
}

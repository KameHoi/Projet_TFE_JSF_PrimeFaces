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
@FacesValidator("linkedinPageValidator")
public class LinkedinPageValidator implements Validator {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        if (value != null) {

            CompaniesEntity companiesEntity = new CompaniesEntity();
            companiesEntity.setLinkedInPage((String) value);

            try {
                checkLinkedinPage(companiesEntity);
            } catch (InvalidOperationException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageDomainError(), null));
            }
        }

    }

    /**
     * Return message for wrong linkedin Page
     *
     * @return jsf utils message
     */
    private String getMessageDomainError() {
        return JsfUtils.returnMessage(locale, "company.LinkedinPageNotValid");
    }

    /**
     * Check if LinkedIn page correct
     *
     * @param companiesEntity CompaniesEntity
     */
    public void checkLinkedinPage(CompaniesEntity companiesEntity) {

        if (companiesEntity != null) {
            Pattern pattern = Pattern.compile("^https?://((www|\\w\\w)\\.)?linkedin.com\\/.*$");
            Matcher matcher = pattern.matcher(companiesEntity.getLinkedInPage());
            boolean matcherResult = matcher.matches();

            if (!matcherResult) {
                log.warn("Url " + companiesEntity.getLinkedInPage() + " is not valid");
                throw new InvalidOperationException(
                        "La page LinkedIn n'est pas valide " + companiesEntity.getLinkedInPage(), ErrorCodes.COMPANY_LINKEDIN_NOT_VALID
                );
            }
        }
    }

}

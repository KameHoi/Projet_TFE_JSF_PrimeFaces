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
@FacesValidator("companyDomainValidator")
public class CompanyDomainValidator implements Validator {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        if (value != null) {
            CompaniesEntity companiesEntity = new CompaniesEntity();
            companiesEntity.setDomainName((String) value);

            try {
                checkUrlDomain(companiesEntity);
            } catch (InvalidOperationException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageDomainError(), null));
            }
        }
    }

    /**
     * Return message for wrong domain url
     *
     * @return jsf utils message
     */
    private String getMessageDomainError() {
        return JsfUtils.returnMessage(locale, "company.domainNotValid");
    }

    /**
     * Check if domain url is correct
     *
     * @param companiesEntity CompaniesEntity
     */
    public void checkUrlDomain(CompaniesEntity companiesEntity) {

        if (companiesEntity != null) {
            Pattern pattern = Pattern.compile("([https://|http://])*([www.])*\\w+\\.\\w+\\D+");
            Matcher matcher = pattern.matcher(companiesEntity.getDomainName());
            boolean matcherResult = matcher.matches();

            if (!matcherResult) {
                log.warn("Url " + companiesEntity.getDomainName() + " is not valid");
                throw new InvalidOperationException(
                        "L'url n'est pas valide " + companiesEntity.getDomainName(), ErrorCodes.COMPANY_DOMAIN_NOT_VALID
                );
            }
        }
    }
}

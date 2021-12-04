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
@FacesValidator("companyVATValidator")
public class CompanyVATValidator implements Validator {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        if (value != null) {

            CompaniesEntity companiesEntity = new CompaniesEntity();
            companiesEntity.setVatNumber((String) value);

            try {
                checkVATFormat(companiesEntity);
            } catch (InvalidOperationException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageVATFormatError(), null));
            }

            try {
                checkVATValidity(companiesEntity);
            } catch (InvalidOperationException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageVATNOTVALIDError(), null));
            }
        }
    }

    /**
     * Check format of VAT
     *
     * @param companiesEntity CompaniesEntity
     */
    public void checkVATFormat(CompaniesEntity companiesEntity) {

        log.info("Start of the checkVATFormat function");

        if (companiesEntity != null) {
            String regex = "^[A-Za-z]{2,4}(?=.{2,12}$)[-_\\s0-9]*(?:[a-zA-Z][-_\\s0-9]*){0,2}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(companiesEntity.getVatNumber());

            boolean matcherResult = matcher.matches();

            if (!matcherResult) {
                log.warn("VAT number : " + companiesEntity.getVatNumber() + " is not correct");
                throw new InvalidOperationException(
                        "Le numéro de TVA n'est pas valide " + companiesEntity.getVatNumber(), ErrorCodes.COMPANY_VAT_NOT_VALID
                );
            }
        }

    }

    /**
     * Check VAT is valid
     *
     * @param companiesEntity CompaniesEntity
     */
    public void checkVATValidity(CompaniesEntity companiesEntity) {

        log.info("Start of CheckVATVAlidity function");

        String response = companiesEntity.getVatNumber();

        String newResponse = response.substring(3);

        log.info("Numéro sans le BE : " + newResponse);

        double newResponseDouble = Double.parseDouble(newResponse);

        double modulo = 97.00;

        double verif = Math.floor(newResponseDouble / 100);

        log.info("Résultat vérif : " + verif);

        Double digit = newResponseDouble % 100;

        log.info("Résultat digit : " + digit);

        Double check = modulo - (verif % modulo);

        log.info("Résultat check : " + check);

        if (!(check.equals(digit))) {
            log.warn("VAT number : " + companiesEntity.getVatNumber() + " is not valid");
            throw new InvalidOperationException(
                    "Le numéro de TVA n'est pas valide " + companiesEntity.getVatNumber(), ErrorCodes.COMPANY_VAT_NOT_VALID
            );
        }
    }

    /**
     * Return message for VAT validity error
     *
     * @return jsf utils message
     */
    private String getMessageVATNOTVALIDError() {
        return JsfUtils.returnMessage(locale, "company.VATNotValid");
    }

    /**
     * Return message for VAT format error
     *
     * @return jsf utils message
     */
    private String getMessageVATFormatError() {
        return JsfUtils.returnMessage(locale, "company.VATFormatNotValid");
    }

}

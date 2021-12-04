package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.CompaniesEntity;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidOperationException;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.extern.slf4j.Slf4j;
import nl.garvelink.iban.Modulo97;

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
@FacesValidator("companyBankAccountValidator")
public class CompanyBankAccountValidator implements Validator {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        if (value != null) {
            CompaniesEntity companiesEntity = new CompaniesEntity();
            companiesEntity.setBankAccount((String) value);

            try {
                checkBankAccountFormat(companiesEntity);
            } catch (InvalidOperationException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageBankAccountError(), null));
            }

            try {
                checkBankAccountValidity(companiesEntity);
            } catch (InvalidOperationException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageBankAccountError(), null));
            }
        }
    }

    /**
     * Return message for bank account error
     *
     * @return jsf utils message
     */
    private String getMessageBankAccountError() {
        return JsfUtils.returnMessage(locale, "company.BankAccountNotValid");
    }


    /**
     * Check format of bank account is correct
     *
     * @param companiesEntity CompaniesEntity
     */
    public void checkBankAccountFormat(CompaniesEntity companiesEntity) {

        log.info("Start of the checkBankAccountFormat function");
        log.info("Value = " + companiesEntity.getBankAccount());

        if (companiesEntity != null) {
            String regex = "BE-[a-zA-Z0-9]{2}\\s?([0-9]{4}\\s?){3}\\s?";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(companiesEntity.getBankAccount());

            boolean matcherResult = matcher.matches();

            if (!matcherResult) {
                log.warn("Bank account number : " + companiesEntity.getBankAccount() + " is not valid");
                throw new InvalidOperationException(
                        "Le numéro de compte bancaire n'est pas valide " + companiesEntity.getBankAccount(), ErrorCodes.COMPANY_BANKACCOUNT_NOT_VALID
                );
            }
        }
    }

    /**
     * Check if bank account is valid
     *
     * @param companiesEntity CompaniesEntity
     */
    public void checkBankAccountValidity(CompaniesEntity companiesEntity) {

        log.info("Start of checkBankAccountValidity function");

        String companyIban = companiesEntity.getBankAccount();

        String companyIbanReplaced = companyIban.replace("-", "");

        log.info("Iban replaced : " + companyIbanReplaced);

        boolean validIban = Modulo97.verifyCheckDigits(companyIbanReplaced);

        if (!(validIban)) {
            log.warn("Iban number : " + companiesEntity.getVatNumber() + " is not valid");
            throw new InvalidOperationException(
                    "Le numéro de banque n'est pas valide " + companiesEntity.getVatNumber(), ErrorCodes.COMPANY_BANKACCOUNT_NOT_VALID
            );
        }
    }
}

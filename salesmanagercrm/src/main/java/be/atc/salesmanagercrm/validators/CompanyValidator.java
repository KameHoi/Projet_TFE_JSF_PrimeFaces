package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.entities.CompaniesEntity;
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
public class CompanyValidator {

    public static List<String> validate(CompaniesEntity companiesEntity) {

        List<String> errors = new ArrayList<>();

        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        FacesMessage msg;


        if (companiesEntity == null) {

            errors.add("Le nom de l'entreprise est obligatoire !");
            errors.add("Veuillez choisir un type d'entreprise");

            msg = new FacesMessage(JsfUtils.returnMessage(locale, "company.labelNotValid"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return errors;
        }

        if (!(companiesEntity.getDomainName().equals(""))) {
            if (!validateUrlDomain(companiesEntity)) {
                errors.add("Le nom de domaine doit être valide !");
                msg = new FacesMessage(JsfUtils.returnMessage(locale, "company.domainNotValid"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

        if (companiesEntity.getLabel() == null || companiesEntity.getLabel().isEmpty()) {
            errors.add("Le nom de l'entreprise est obligatoire !");
            msg = new FacesMessage(JsfUtils.returnMessage(locale, "company.labelNotValid"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        if (companiesEntity.getCompanyTypesByIdCompanyTypes() == null) {
            errors.add("Veuillez choisir un type d'entreprise");
            msg = new FacesMessage(JsfUtils.returnMessage(locale, "company.companiesTypeNotValid"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        if (companiesEntity.getEmployeesNumber() != 0) {
            if (numbersIsPositive(companiesEntity)) {
                errors.add("Le nombre entré doit être supérieur à 0");
                msg = new FacesMessage(JsfUtils.returnMessage(locale, "company.employeesNumberNotValid"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

        if (companiesEntity.getRevenue() != null) {
            if (revenueIsPositive(companiesEntity)) {
                errors.add("Le nombre entré doit être supérieur à 0");
                msg = new FacesMessage(JsfUtils.returnMessage(locale, "company.employeesNumberNotValid"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

        if (!(companiesEntity.getLinkedInPage().equals(""))) {
            if (!(validateLinkedinPage(companiesEntity))) {
                errors.add("La page LinkedIn doit être valide !");
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "company.LinkedinPageNotValid"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

        if (companiesEntity.getBankAccount() != null && !(companiesEntity.getBankAccount().isEmpty())) {
            if (!(validateBankAccount(companiesEntity))) {
                if (!(checkBankAccountValidity(companiesEntity))) {
                    errors.add("Le numéro de compte doit être valide !");
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "company.BankAccountNotValid"), null);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
        }

        if (companiesEntity.getVatNumber() != null && !(companiesEntity.getVatNumber().isEmpty())) {
            if (!(validateVATNumber(companiesEntity))) {
                if (!(checkVATValidity(companiesEntity))) {
                    errors.add("Le numéro de tva doit être valide !");
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "company.VATFormatNotValid"), null);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
        }

        if (companiesEntity.getCreationDate() != 0) {
            if (!(validateCreationDate(companiesEntity))) {
                errors.add("L'année de création doit être valide !");
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "company.creationDateNotValid"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        if (companiesEntity.getClosingDate() != 0) {
            if (!(validateClosingDate(companiesEntity))) {
                errors.add("L'année de création doit être valide !");
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "company.creationDateNotValid"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

        return errors;
    }

    public static boolean validateUrlDomain(CompaniesEntity companiesEntity) {
        Pattern pattern = Pattern.compile("([https://|http://])*([www.])*\\w+\\.\\w+\\D+");
        Matcher matcher = pattern.matcher(companiesEntity.getDomainName());

        return matcher.find();
    }

    public static boolean validateLinkedinPage(CompaniesEntity companiesEntity) {

        String regex = "^https?://((www|\\w\\w)\\.)?linkedin.com\\/.*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(companiesEntity.getLinkedInPage());

        return matcher.find();
    }

    public static boolean numbersIsPositive(CompaniesEntity companiesEntity) {

        return companiesEntity.getEmployeesNumber() <= 0;
    }

    public static boolean revenueIsPositive(CompaniesEntity companiesEntity) {

        return companiesEntity.getRevenue() <= 0;
    }

    public static boolean validateBankAccount(CompaniesEntity companiesEntity) {

        String regex = "BE-[a-zA-Z0-9]{2}\\s?([0-9]{4}\\s?){3}\\s?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(companiesEntity.getBankAccount());

        log.info("Résultat : " + matcher.find());

        return matcher.find();
    }

    public static boolean validateVATNumber(CompaniesEntity companiesEntity) {

        String regex = "^[A-Za-z]{2,4}(?=.{2,12}$)[-_\\s0-9]*(?:[a-zA-Z][-_\\s0-9]*){0,2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(companiesEntity.getVatNumber());

        return matcher.find();
    }

    public static boolean checkVATValidity(CompaniesEntity companiesEntity) {
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

        return check.equals(digit);
    }

    public static boolean validateCreationDate(CompaniesEntity companiesEntity) {

        String regex = "^[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(companiesEntity.getCreationDate()));

        return matcher.find();
    }

    public static boolean validateClosingDate(CompaniesEntity companiesEntity) {

        String regex = "^[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(companiesEntity.getClosingDate()));

        return matcher.find();
    }

    public static boolean checkBankAccountValidity(CompaniesEntity companiesEntity) {
        log.info("Start of checkBankAccountValidity function");

        String companyIban = companiesEntity.getBankAccount();

        String companyIbanReplaced = companyIban.replace("-", "");

        log.info("Iban replaced : " + companyIbanReplaced);

        return Modulo97.verifyCheckDigits(companyIbanReplaced);
    }

}

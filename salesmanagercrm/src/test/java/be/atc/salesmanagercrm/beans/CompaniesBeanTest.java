package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.entities.BranchActivitiesEntity;
import be.atc.salesmanagercrm.entities.CompaniesEntity;
import be.atc.salesmanagercrm.entities.CompanyTypesEntity;
import be.atc.salesmanagercrm.entities.UsersEntity;
import lombok.extern.slf4j.Slf4j;
import nl.garvelink.iban.Modulo97;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CompaniesBeanTest {

    private CompaniesBean companiesBean;

    @BeforeEach
    public void init() {
        log.info("Appel avant chaque test");
        companiesBean = new CompaniesBean();
    }

    @AfterEach
    public void after() {
        log.info("Appel après chaque test");
        companiesBean = null;
    }

    @Test
    void save() {

        UsersEntity usersEntity = new UsersEntity();
        CompaniesEntity companiesEntity = new CompaniesEntity();
        BranchActivitiesEntity branchActivitiesEntity = new BranchActivitiesEntity();
        CompanyTypesEntity companyTypesEntity = new CompanyTypesEntity();

        usersEntity.setId(1);
        companyTypesEntity.setId(1);
        branchActivitiesEntity.setId(1);

        companiesEntity.setBranchActivitiesByIdBranchActivities(branchActivitiesEntity);
        companiesEntity.setUsersByIdUsers(usersEntity);
        companiesEntity.setCompanyTypesByIdCompanyTypes(companyTypesEntity);
        companiesEntity.setLabel("Coca-cola");
        companiesEntity.setDomainName("www.coca.be");
        companiesEntity.setEmployeesNumber(2000);
        companiesEntity.setRevenue(1000000.0);
        companiesEntity.setLinkedInPage("https://www.linkedin.com/company/the-coca-cola-company/");

        companiesBean.save(companiesEntity);
    }

    @Test
    void findCompaniesEntityByIdUser() {
        // Mettre un id correct
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        List<CompaniesEntity> companiesEntities = companiesBean.findCompaniesEntityByIdUser(usersEntity);

        boolean test = !companiesEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(true);

    }

    @Test
    void findByIdCompanyAndByIdUser() {

        int id = 1;
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        CompaniesEntity companiesEntity = companiesBean.findByIdCompanyAndByIdUser(id, usersEntity);

        boolean test = !(companiesEntity == null);

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(true);

    }

    @Test
    void checkVATValidity() {

        String response = "BE 0696668549";

        String newResponse = response.substring(3);

        double newResponseDouble = Double.parseDouble(newResponse);

        double modulo = 97.00;

        double verif = Math.floor(newResponseDouble / 100);

        log.info("Résultat vérif : " + verif);

        Double digit = newResponseDouble % 100;

        log.info("Résultat digit : " + digit);

        Double check = modulo - (verif % modulo);

        log.info("Résultat check : " + check);

        assertThat(check.equals(digit)).isEqualTo(true);

    }


    @Test
    void checkVATValidityShoudlReturnFalse() {

        String response = "BE 06966685";

        String newResponse = response.substring(3);

        double newResponseDouble = Double.parseDouble(newResponse);

        double modulo = 97.00;

        double verif = Math.floor(newResponseDouble / 100);

        log.info("Résultat vérif : " + verif);

        Double digit = newResponseDouble % 100;

        log.info("Résultat digit : " + digit);

        Double check = modulo - (verif % modulo);

        log.info("Résultat check : " + check);

        assertThat(check.equals(digit)).isEqualTo(false);

    }

    @Test
    void checkIbanShouldReturnFalse() {
        String ibanNbr = "BE-0402377378";

        String replaceIban = ibanNbr.replace("-", "");

        log.info(replaceIban);

        boolean valid = Modulo97.verifyCheckDigits(replaceIban);

        log.info("Réponse de valid = " + valid);
        assertThat(valid).isEqualTo(false);
    }

    @Test
    void checkIbanShouldReturnTrue() {
        String ibanNbr = "BE-61000171003017";

        String replaceIban = ibanNbr.replace("-", "");

        log.info(replaceIban);

        boolean valid = Modulo97.verifyCheckDigits(replaceIban);

        log.info("Réponse de valid = " + valid);
        assertThat(valid).isEqualTo(true);
    }

    @Test
    void countActiveCompanies() {

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        long i = companiesBean.countActiveCompanies(usersEntity);

        log.info("Valeur du résultat = " + i);

    }
}
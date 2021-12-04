package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.entities.CompanyTypesEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CompanyTypesBeanTest {

    private CompanyTypesBean companyTypesBean;

    @BeforeEach
    public void init() {
        log.info("Appel avant chaque test");
        companyTypesBean = new CompanyTypesBean();
    }

    @AfterEach
    public void after() {
        log.info("Appel après chaque test");
        companyTypesBean = null;
    }


    @Test
    void findAll() {
        List<CompanyTypesEntity> companyTypesEntityList = companyTypesBean.findAll();

        boolean test = !companyTypesEntityList.isEmpty();

        log.info("Le test vaut : " + test + ". La liste contient : " + (long) companyTypesEntityList.size() + " types d'entreprise");

        assertThat(test).isEqualTo(true);
    }
}
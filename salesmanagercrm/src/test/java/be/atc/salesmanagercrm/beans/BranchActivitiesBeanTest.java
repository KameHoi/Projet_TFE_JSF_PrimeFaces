package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.entities.BranchActivitiesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class BranchActivitiesBeanTest {

    private BranchActivitiesBean branchActivitiesBean;

    @BeforeEach
    public void init() {
        log.info("Appel avant chaque test");
        branchActivitiesBean = new BranchActivitiesBean();
    }

    @AfterEach
    public void after() {
        log.info("Appel après chaque test");
        branchActivitiesBean = null;
    }

    @Test
    void addBranchActivities() {

        BranchActivitiesEntity branchActivitiesEntity = new BranchActivitiesEntity();

        branchActivitiesEntity.setLabel("Marketing");

        branchActivitiesBean.addBranchActivities(branchActivitiesEntity);
    }

    @Test
    void addBranchActivitiesWithLabelAlreadyExist() {

        BranchActivitiesEntity branchActivitiesEntity = new BranchActivitiesEntity();

        branchActivitiesEntity.setLabel("Marketing");

        branchActivitiesBean.addBranchActivities(branchActivitiesEntity);
    }

    @Test
    void findAll() {
        List<BranchActivitiesEntity> branchActivitiesEntityList = branchActivitiesBean.findAll();

        boolean test = !branchActivitiesEntityList.isEmpty();

        log.info("Le test vaut : " + test + ". La liste contient : " + (long) branchActivitiesEntityList.size() + " secteurs d'activité");

        assertThat(test).isEqualTo(true);

    }

    @Test
    void findBranchActvivitiesList() {
        List<BranchActivitiesEntity> branchActivitiesEntities = branchActivitiesBean.findBranchActvivitiesList();

        boolean test = !branchActivitiesEntities.isEmpty();


        log.info("Le test vaut : " + test + ". La liste contient : " + (long) branchActivitiesEntities.size() + " secteurs d'activité");

        assertThat(test).isEqualTo(true);
    }

    @Test
    void findBranchActivitiesEntityByIdShouldReturnTrue() {

        //ID valide
        int id = 1;

        BranchActivitiesEntity branchActivitiesEntity = null;

        try {
            branchActivitiesEntity = branchActivitiesBean.findById(id);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = branchActivitiesEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);

    }

    @Test
    void findBranchActivitiesEntityByIdShouldReturnFalse() {

        //ID valide
        int id = 99;

        BranchActivitiesEntity branchActivitiesEntity = null;

        try {
            branchActivitiesEntity = branchActivitiesBean.findById(id);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = branchActivitiesEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);

    }


    @Test
    void update() {

        BranchActivitiesEntity branchActivitiesEntity = new BranchActivitiesEntity();

        branchActivitiesEntity.setId(1);

        branchActivitiesEntity.setLabel("Informatique modifié");

        branchActivitiesBean.updateBranchActivitiesLabel(branchActivitiesEntity);
    }
}
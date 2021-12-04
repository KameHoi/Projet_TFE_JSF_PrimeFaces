package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.entities.*;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
class CheckEntitiesTest {

    @Test
    void checkContact() {

        ContactsEntity entity = new ContactsEntity();

        entity.setId(2);

        CheckEntities checkEntities = new CheckEntities();

        log.info("Test de départ");
        try {
            checkEntities.checkContact(entity);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            return;
        }

        log.info("test de fin");

    }

    @Test
    void checkCompany() {

        CompaniesEntity entity = new CompaniesEntity();

        entity.setId(2);

        CheckEntities checkEntities = new CheckEntities();
        log.info("Test de départ");

        try {
            checkEntities.checkCompany(entity);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            return;
        }

        log.info("test de fin");
    }

    @Test
    void checkUser() {

        UsersEntity entity = new UsersEntity();

        entity.setId(3);

        CheckEntities checkEntities = new CheckEntities();
        log.info("Test de départ");

        try {
            checkEntities.checkUser(entity);
        } catch (InvalidEntityException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            return;
        }

        log.info("test de fin");
    }

    @Test
    void checkTaskType() {
        TaskTypesEntity entity = new TaskTypesEntity();

        entity.setId(20);

        CheckEntities checkEntities = new CheckEntities();
        log.info("Test de départ");

        try {
            checkEntities.checkTaskType(entity);
        } catch (EntityNotFoundException exception) {
            log.info("test on est dans le catch");
            return;
        }

        log.info("test de fin");
    }

    @Test
    void checkJobTitlesLabel() {
        JobTitlesEntity jobTitlesEntity = new JobTitlesEntity();

        jobTitlesEntity.setLabel("test");

        CheckEntities checkEntities = new CheckEntities();
        log.info("Début du test");

        try {
            checkEntities.checkJobTitlesLabel(jobTitlesEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            return;
        }

        log.info("test de fin");
    }

    @Test
    void checkBranchActivitiesLabel() {
        BranchActivitiesEntity branchActivitiesEntity = new BranchActivitiesEntity();

        branchActivitiesEntity.setLabel("marketing");

        CheckEntities checkEntities = new CheckEntities();
        log.info("Début du test");

        try {
            checkEntities.checkBranchActivitiesLabel(branchActivitiesEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            return;
        }

        log.info("Fin du test");
    }
}
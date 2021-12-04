package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.entities.*;
import be.atc.salesmanagercrm.enums.EnumPriority;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class TasksBeanTest {

    private TasksBean tasksBean;

    @BeforeEach
    public void init() {
        log.info("Appel avant chaque test");
        tasksBean = new TasksBean();
    }

    @AfterEach
    public void after() {
        log.info("Appel après chaque test");
        tasksBean = null;
    }

    @Test
    void save() {

        UsersEntity usersEntity = new UsersEntity();
        CompaniesEntity companiesEntity = new CompaniesEntity();
        ContactsEntity contactsEntity = new ContactsEntity();
        TaskTypesEntity taskTypesEntity = new TaskTypesEntity();

        usersEntity.setId(1);
        companiesEntity.setId(1);
        contactsEntity.setId(1);
        taskTypesEntity.setId(1);

        TasksEntity entity = new TasksEntity();

        entity.setUsersByIdUsers(usersEntity);
        entity.setPriority(EnumPriority.ELEVEE);
        entity.setEndDate(LocalDateTime.now().plusMonths(1));
        entity.setTitle("Task Test");

     //   entity.setCompaniesByIdCompanies(companiesEntity);
        entity.setContactsByIdContacts(contactsEntity);
        entity.setTaskTypesByIdTaskTypes(taskTypesEntity);
        tasksBean.save(entity);
    }

    @Test
    void findByIdShouldReturnFalse() {

        // Mettre un id incorrect
        int id = 1200;
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);
        TasksEntity tasksEntity = null;

        try {
            tasksEntity = tasksBean.findById(id, usersEntity);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = tasksEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);

    }


    @Test
    void findByIdShouldReturnTrue() {

        // Mettre un id correct
        int id = 13;
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);
        TasksEntity tasksEntity = null;

        try {
            tasksEntity = tasksBean.findById(id, usersEntity);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = tasksEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);

    }

    @Test
    void findTasksEntityByContactsByIdContactsShouldReturnTrue() {

        // Mettre un id correct
        ContactsEntity contactsEntity = new ContactsEntity();
        contactsEntity.setId(1);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        List<TasksEntity> tasksEntities = tasksBean.findTasksEntityByContactsByIdContacts(contactsEntity, usersEntity);


        boolean test = !tasksEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(true);
    }

    @Test
    void findTasksEntityByContactsByIdContactsShouldReturnFalse() {

        // Mettre un id incorrect
        ContactsEntity contactsEntity = new ContactsEntity();
        contactsEntity.setId(1884);

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(11);

        List<TasksEntity> tasksEntities = tasksBean.findTasksEntityByContactsByIdContacts(contactsEntity, usersEntity);


        boolean test = !tasksEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(false);
    }

    @Test
    void findTasksEntityByCompaniesByIdCompaniesShouldReturnTrue() {

        // Mettre un id correct
        CompaniesEntity companiesEntity = new CompaniesEntity();
        companiesEntity.setId(1);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        List<TasksEntity> tasksEntities = tasksBean.findTasksEntityByCompaniesByIdCompanies(companiesEntity, usersEntity);


        boolean test = !tasksEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(true);
    }

    @Test
    void findTasksEntityByCompaniesByIdCompaniesShouldReturnFalse() {

        // Mettre un id incorrect
        CompaniesEntity companiesEntity = new CompaniesEntity();
        companiesEntity.setId(195);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        List<TasksEntity> tasksEntities = tasksBean.findTasksEntityByCompaniesByIdCompanies(companiesEntity, usersEntity);


        boolean test = !tasksEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(false);
    }

    @Test
    void findAll() {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);
        List<TasksEntity> tasksEntities = tasksBean.findAll(usersEntity);


        boolean test = !tasksEntities.isEmpty();

        log.info("Le test vaut : " + test + ". La liste contient : " + (long) tasksEntities.size() + " taches");

        assertThat(test).isEqualTo(true);
    }


    @Test
    void delete() {
        int id = 12;
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        tasksBean.delete(id, usersEntity);

    }

    @Test
    void update() {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        TasksEntity tasksEntity = new TasksEntity();
        tasksEntity.setId(11);
        tasksEntity.setTitle("Titre modifié");
        tasksEntity.setDescription("Description Modifié");

        tasksEntity.setUsersByIdUsers(usersEntity);

        tasksBean.update(tasksEntity);
    }

    @Test
    void findTasksFinished() {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);


        List<TasksEntity> tasksEntities = tasksBean.findTasksFinished(usersEntity);


        boolean test = !tasksEntities.isEmpty();

        log.info("Le test vaut : " + test + ". La liste contient : " + (long) tasksEntities.size() + " taches");

        assertThat(test).isEqualTo(true);
    }
}
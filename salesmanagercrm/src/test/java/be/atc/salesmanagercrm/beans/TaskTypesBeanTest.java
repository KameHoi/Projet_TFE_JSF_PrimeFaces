package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.entities.TaskTypesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class TaskTypesBeanTest {

    private TaskTypesBean taskTypesBean;

    @BeforeEach
    public void init() {
        log.info("Appel avant chaque test");
        taskTypesBean = new TaskTypesBean();
    }

    @AfterEach
    public void after() {
        log.info("Appel après chaque test");
        taskTypesBean = null;
    }

    @Test
    void save() {

        TaskTypesEntity entity = new TaskTypesEntity();

//        entity.setLabel("");

        taskTypesBean.save(entity);
    }

    @Test
    void findByIdShouldReturnFalse() {

        // Mettre un id incorrect
        int id = 12;

        TaskTypesEntity taskTypesEntity = null;


        try {
            taskTypesEntity = taskTypesBean.findById(id);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = taskTypesEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }


    @Test
    void findByIdShouldReturnTrue() {

        // Mettre un id correct
        int id = 1;
        TaskTypesEntity taskTypesEntity = null;

        try {
            taskTypesEntity = taskTypesBean.findById(id);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = taskTypesEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);

    }

    @Test
    void findAll() {
        List<TaskTypesEntity> taskTypesEntities = taskTypesBean.findAll();


        boolean test = !taskTypesEntities.isEmpty();

        log.info("Le test vaut : " + test + ". La liste contient : " + (long) taskTypesEntities.size() + " taches");

        assertThat(test).isEqualTo(true);
    }

    @Test
    void update() {

        TaskTypesEntity taskTypesEntity = new TaskTypesEntity();
        taskTypesEntity.setId(5);

        taskTypesEntity.setLabel("Test Modifié");
        taskTypesBean.update(taskTypesEntity);
    }

    @Test
    void findByLabelShouldReturnTrue() {

        // Mettre un label correct
        String label = "Email";
        TaskTypesEntity taskTypesEntity = null;

        try {
            taskTypesEntity = taskTypesBean.findByLabel(label);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = taskTypesEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);

    }

    @Test
    void findByLabelShouldReturnFalse() {

        // Mettre un label correct
        String label = "test";
        TaskTypesEntity taskTypesEntity = null;

        try {
            taskTypesEntity = taskTypesBean.findByLabel(label);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = taskTypesEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);

    }
}
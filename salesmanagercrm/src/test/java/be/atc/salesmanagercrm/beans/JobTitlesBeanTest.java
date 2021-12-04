package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.entities.JobTitlesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JobTitlesBeanTest {

    private JobTitlesBean jobTitlesBean;

    @BeforeEach
    public void init() {
        log.info("Appel avant chaque test");
        jobTitlesBean = new JobTitlesBean();
    }

    @AfterEach
    public void after() {
        log.info("Appel après chaque test");
        jobTitlesBean = null;
    }

    @Test
    void findByIdShouldReturnFalse() {

        // Mettre un id incorrect
        int id = 25;

        JobTitlesEntity jobTitlesEntity = null;

        try {
            jobTitlesEntity = jobTitlesBean.findById(id);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = jobTitlesEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);

    }


    @Test
    void findByIdShouldReturnTrue() {

        // Mettre un id correct
        int id = 1;

        JobTitlesEntity jobTitlesEntity = null;

        try {
            jobTitlesEntity = jobTitlesBean.findById(id);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = jobTitlesEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);

    }

    @Test
    void update() {

        JobTitlesEntity jobTitlesEntity = new JobTitlesEntity();
        jobTitlesEntity.setId(1);

        jobTitlesEntity.setLabel("PDG");

        jobTitlesBean.update(jobTitlesEntity);
    }

}
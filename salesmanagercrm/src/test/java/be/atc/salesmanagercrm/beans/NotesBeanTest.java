package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.entities.CompaniesEntity;
import be.atc.salesmanagercrm.entities.ContactsEntity;
import be.atc.salesmanagercrm.entities.NotesEntity;
import be.atc.salesmanagercrm.entities.UsersEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class NotesBeanTest {

    private NotesBean notesBean;

    @BeforeEach
    public void init() {
        log.info("Appel avant chaque test");
        notesBean = new NotesBean();
    }

    @AfterEach
    public void after() {
        log.info("Appel après chaque test");
        notesBean = null;
    }

    @Test
    void save() {

        UsersEntity usersEntity = new UsersEntity();

        usersEntity.setId(1055);

        NotesEntity entity = new NotesEntity();

        entity.setUsersByIdUsers(usersEntity);
        entity.setMessage("test 4");

        notesBean.save(entity);
    }

    @Test
    void findByIdShouldReturnFalse() {
        int id = 1;
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        NotesEntity notesEntity = null;
        try {
            notesEntity = notesBean.findById(id, usersEntity);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = notesEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    void findByIdShouldReturnTrue() {
        int id = 1;
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        NotesEntity notesEntity = null;
        try {
            notesEntity = notesBean.findById(id, usersEntity);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
        }

        boolean test = notesEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }
    @Test
    void findEntityNotesEntityByContactsByIdContactsShouldReturnTrue() {

        // Mettre un id correct
        ContactsEntity contactsEntity = new ContactsEntity();
        contactsEntity.setId(1);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        List<NotesEntity> notesEntities = notesBean.findNotesEntityByContactsByIdContacts(contactsEntity, usersEntity);

        boolean test = !notesEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(true);
    }

    @Test
    void findEntityNotesEntityByContactsByIdContactsShouldReturnFalse() {

        // Mettre un id incorrect
        ContactsEntity contactsEntity = new ContactsEntity();
        contactsEntity.setId(1);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(11);

        List<NotesEntity> notesEntities = notesBean.findNotesEntityByContactsByIdContacts(contactsEntity, usersEntity);

        boolean test = !notesEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(false);
    }


    @Test
    void findEntityNotesEntityByCompaniesByIdCompaniesShouldReturnTrue() {

        // Mettre un id correct
        CompaniesEntity companiesEntity = new CompaniesEntity();
        companiesEntity.setId(1);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        List<NotesEntity> notesEntities = notesBean.findNotesEntityByCompaniesByIdCompanies(companiesEntity, usersEntity);

        boolean test = !notesEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(true);
    }

    @Test
    void findEntityNotesEntityByCompaniesByIdCompaniesShouldReturnFalse() {

        // Mettre un id incorrect
        CompaniesEntity companiesEntity = new CompaniesEntity();
        companiesEntity.setId(195);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        List<NotesEntity> notesEntities = notesBean.findNotesEntityByCompaniesByIdCompanies(companiesEntity, usersEntity);

        boolean test = !notesEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(false);
    }

    @Test
    void findAll() {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);
        List<NotesEntity> notesEntities = notesBean.findAll(usersEntity);


        boolean test = !notesEntities.isEmpty();

        log.info("Le test vaut : " + test + ". La liste contient : " + (long) notesEntities.size() + " notes");

        assertThat(test).isEqualTo(true);
    }

    @Test
    void delete() {
        int id = 12;
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        notesBean.delete(id, usersEntity);

    }

    @Test
    void update() {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(10);

        NotesEntity notesEntity = new NotesEntity();
        notesEntity.setId(1);
        notesEntity.setMessage("Message modifié");
        notesEntity.setUsersByIdUsers(usersEntity);

        notesBean.update(notesEntity);
    }
}
package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.entities.ContactTypesEntity;
import be.atc.salesmanagercrm.entities.ContactsEntity;
import be.atc.salesmanagercrm.entities.JobTitlesEntity;
import be.atc.salesmanagercrm.entities.UsersEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ContactsBeanTest {

    private ContactsBean contactsBean;

    @BeforeEach
    public void init() {
        log.info("Appel avant chaque test");
        contactsBean = new ContactsBean();
    }

    @AfterEach
    public void after() {
        log.info("Appel après chaque test");
        contactsBean = null;
    }

    @Test
    void findContactsEntityByIdUser() {

        // Mettre un id correct
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        List<ContactsEntity> contactsEntities = contactsBean.findContactsEntityByIdUser(usersEntity);

        boolean test = !contactsEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(true);

    }

    @Test
    void save() {

        ContactsEntity contactsEntity = new ContactsEntity();
        JobTitlesEntity jobTitlesEntity = new JobTitlesEntity();
        ContactTypesEntity contactTypesEntity = new ContactTypesEntity();

        jobTitlesEntity.setId(4);
        contactTypesEntity.setId(1);

        contactsEntity.setEmail("test@gmail.com");
        contactsEntity.setFirstname("Max");
        contactsEntity.setLastname("Zab");
        contactsEntity.setJobTitlesByIdJobTitles(jobTitlesEntity);
        contactsEntity.setContactTypesByIdContactTypes(contactTypesEntity);
        contactsEntity.setPhoneNumber("0477777777");

        contactsBean.save(contactsEntity);
    }

    @Test
    void checkPhoneNumberShouldReturnTrue() {
        String phoneNumber = "+32 584584884";
        String regex = "^[\\+]?[(]?[0-9 ]{3}[)]?[0-9]{7,9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        matcher.matches();
        log.info(String.valueOf(matcher.matches()));

        assertThat(matcher.matches());
    }

    @Test
    void checkPhoneNumberShouldReturnFalse() {
        String regex = "^[\\+]?[(]?[0-9 ]{3}[)]?[0-9]{7,9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("+32 4adbve");
        matcher.matches();
        log.info(String.valueOf(matcher.matches()));

        assertThat(!(matcher.matches()));
    }
}
package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notes", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Notes.findNotesEntityByContactsByIdContacts", query = "select n from NotesEntity n where (n.contactsByIdContacts.id = :id and n.usersByIdUsers.id = :idUser) ORDER BY n.creationDate desc "),
        @NamedQuery(name = "Notes.findNotesEntityByCompaniesByIdCompanies", query = "select n from NotesEntity n where (n.companiesByIdCompanies.id = :id and n.usersByIdUsers.id = :idUser) ORDER BY n.creationDate desc "),
        @NamedQuery(name = "Notes.findAll", query = "SELECT n from NotesEntity n where n.usersByIdUsers.id = :idUser ORDER BY n.creationDate desc "),
        @NamedQuery(name = "Notes.findById", query = "SELECT n from NotesEntity n where (n.id = :id and n.usersByIdUsers.id = :idUser)"),


})
public class NotesEntity {
    private int id;
    private LocalDateTime creationDate;
    private String message;
    private UsersEntity usersByIdUsers;
    private ContactsEntity contactsByIdContacts;
    private CompaniesEntity companiesByIdCompanies;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Creation_Date", nullable = false)
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "Message", nullable = false)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotesEntity that = (NotesEntity) o;
        return id == that.id && Objects.equals(creationDate, that.creationDate) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, message);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Users", referencedColumnName = "ID", nullable = false)
    public UsersEntity getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(UsersEntity usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Contacts", referencedColumnName = "ID")
    public ContactsEntity getContactsByIdContacts() {
        return contactsByIdContacts;
    }

    public void setContactsByIdContacts(ContactsEntity contactsByIdContacts) {
        this.contactsByIdContacts = contactsByIdContacts;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Companies", referencedColumnName = "ID")
    public CompaniesEntity getCompaniesByIdCompanies() {
        return companiesByIdCompanies;
    }

    public void setCompaniesByIdCompanies(CompaniesEntity companiesByIdCompanies) {
        this.companiesByIdCompanies = companiesByIdCompanies;
    }
}

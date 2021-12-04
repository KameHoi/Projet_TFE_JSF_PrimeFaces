package be.atc.salesmanagercrm.entities;

import be.atc.salesmanagercrm.enums.EnumPriority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tasks", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Tasks.findTasksEntityByContactsByIdContacts", query = "select t from TasksEntity t where (t.contactsByIdContacts.id = :id and t.usersByIdUsers.id = :idUser) ORDER BY t.creationDate desc "),
        @NamedQuery(name = "Tasks.findTasksEntityByCompaniesByIdCompanies", query = "select t from TasksEntity t where (t.companiesByIdCompanies.id = :id and t.usersByIdUsers.id = :idUser) ORDER BY t.creationDate desc "),
        @NamedQuery(name = "Tasks.findById", query = "SELECT t from TasksEntity t where (t.id = :id and t.usersByIdUsers.id = :idUser)"),
        @NamedQuery(name = "Tasks.findAll", query = "SELECT t from TasksEntity t where t.usersByIdUsers.id = :idUser and t.status = false ORDER BY t.creationDate desc "),
        @NamedQuery(name = "Tasks.findTasksToLate", query = "SELECT t from TasksEntity t where (t.endDate < :endDate and t.usersByIdUsers.id = :idUser and t.status = false) ORDER BY t.creationDate desc "),
        @NamedQuery(name = "Tasks.findTasksToCome", query = "SELECT t from TasksEntity t where (t.endDate > :endDate and t.usersByIdUsers.id = :idUser and t.status = false) ORDER BY t.creationDate desc "),
        @NamedQuery(name = "Tasks.findTasksToday", query = "SELECT t from TasksEntity t where (t.endDate < :today and t.endDate > :now and t.usersByIdUsers.id = :idUser and t.status = false) ORDER BY t.creationDate desc "),
        @NamedQuery(name = "Tasks.findTasksFinished", query = "SELECT t from TasksEntity t where (t.status = true and t.usersByIdUsers.id = :idUser) ORDER BY t.creationDate desc "),
})

public class TasksEntity {
    private int id;
    private String title;
    private EnumPriority priority;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private String description;
    private boolean status;
    private UsersEntity usersByIdUsers;
    private ContactsEntity contactsByIdContacts;
    private CompaniesEntity companiesByIdCompanies;
    private TaskTypesEntity taskTypesByIdTaskTypes;

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
    @Column(name = "Title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Enumerated(EnumType.STRING)
    @Basic
    @Column(name = "Priority")
    public EnumPriority getPriority() {
        return priority;
    }

    public void setPriority(EnumPriority priority) {
        this.priority = priority;
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
    @Column(name = "End_Date")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "Status", nullable = false)
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasksEntity that = (TasksEntity) o;
        return id == that.id && status == that.status && Objects.equals(title, that.title) && Objects.equals(priority, that.priority) && Objects.equals(creationDate, that.creationDate) && Objects.equals(endDate, that.endDate) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, priority, creationDate, endDate, description, status);
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

    @ManyToOne
    @JoinColumn(name = "ID_Task_Types", referencedColumnName = "ID")
    public TaskTypesEntity getTaskTypesByIdTaskTypes() {
        return taskTypesByIdTaskTypes;
    }

    public void setTaskTypesByIdTaskTypes(TaskTypesEntity taskTypesByIdTaskTypes) {
        this.taskTypesByIdTaskTypes = taskTypesByIdTaskTypes;
    }
}

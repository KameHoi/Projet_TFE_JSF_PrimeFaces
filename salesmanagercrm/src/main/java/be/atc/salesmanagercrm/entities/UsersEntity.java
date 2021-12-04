package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Users.findNUserByUsernameAndPassword", query = "select u from UsersEntity u where u.username = :username and u.password = :password"),
        @NamedQuery(name = "Users.findByUsername", query = "select u from UsersEntity u where u.username = :username"),
        @NamedQuery(name = "Users.findAllUsers", query = "select u from UsersEntity u order by u.active desc, u.rolesByIdRoles.label, u.username"),
        @NamedQuery(name = "Users.findPassword", query = "select u.password from UsersEntity u where u.id = :id"),
        @NamedQuery(name = "Users.findUsername", query = "select u.username from UsersEntity u where u.id = :id"),
        @NamedQuery(name = "Users.findActiveUsers", query = "select u from UsersEntity u where u.active = true"),
        @NamedQuery(name = "Users.findDisableUsers", query = "select u from UsersEntity u where u.active = false"),
        @NamedQuery(name = "Users.findUserActiveForConnection", query = "select u from UsersEntity u where u.active = false and u.username = :username"),
})
public class UsersEntity {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private LocalDateTime registerDate;
    private boolean isActive;
    private Collection<CompaniesEntity> companiesById;
    private Collection<ContactsEntity> contactsById;
    private Collection<ConversationsEntity> conversationsById;
    private Collection<NotesEntity> notesById;
    private Collection<TasksEntity> tasksById;
    private Collection<TransactionsEntity> transactionsById;
    private RolesEntity rolesByIdRoles;
    private Collection<VouchersEntity> vouchersById;

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
    @Column(name = "Firstname", nullable = false)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "Lastname", nullable = false)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "Username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Register_Date", nullable = false)
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "IsActive", nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id && isActive == that.isActive && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(registerDate, that.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, username, password, email, registerDate, isActive);
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<CompaniesEntity> getCompaniesById() {
        return companiesById;
    }

    public void setCompaniesById(Collection<CompaniesEntity> companiesById) {
        this.companiesById = companiesById;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<ContactsEntity> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<ContactsEntity> contactsById) {
        this.contactsById = contactsById;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<ConversationsEntity> getConversationsById() {
        return conversationsById;
    }

    public void setConversationsById(Collection<ConversationsEntity> conversationsById) {
        this.conversationsById = conversationsById;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<NotesEntity> getNotesById() {
        return notesById;
    }

    public void setNotesById(Collection<NotesEntity> notesById) {
        this.notesById = notesById;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<TasksEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TasksEntity> tasksById) {
        this.tasksById = tasksById;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<TransactionsEntity> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<TransactionsEntity> transactionsById) {
        this.transactionsById = transactionsById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Roles", referencedColumnName = "ID", nullable = false)
    public RolesEntity getRolesByIdRoles() {
        return rolesByIdRoles;
    }

    public void setRolesByIdRoles(RolesEntity rolesByIdRoles) {
        this.rolesByIdRoles = rolesByIdRoles;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<VouchersEntity> getVouchersById() {
        return vouchersById;
    }

    public void setVouchersById(Collection<VouchersEntity> vouchersById) {
        this.vouchersById = vouchersById;
    }
}

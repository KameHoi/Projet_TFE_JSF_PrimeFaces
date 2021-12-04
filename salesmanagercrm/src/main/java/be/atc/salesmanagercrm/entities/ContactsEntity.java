package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "contacts", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Contacts.findContactsEntityByIdUser", query = "select c from ContactsEntity c where (c.usersByIdUsers.id = :idUser and c.active=true)"),
        @NamedQuery(name = "Contacts.findAllContactsEntityByIdUser", query = "select c from ContactsEntity c where (c.usersByIdUsers.id = :idUser) order by c.registerDate desc"),
        @NamedQuery(name = "Contacts.findByIdContactAndByIdUser", query = "select c from ContactsEntity c where (c.id = :id and c.usersByIdUsers.id = :idUser)"),
        @NamedQuery(name = "Contacts.countActiveContacts", query = "select COUNT(c.lastname) from ContactsEntity c where (c.active = true and c.usersByIdUsers.id = :idUser)"),
        @NamedQuery(name = "Contacts.countAllContacts", query = "select COUNT(c.lastname) from ContactsEntity c where (c.usersByIdUsers.id = :idUser)"),
        @NamedQuery(name = "Contacts.findAll", query = "select c from ContactsEntity c where c.active=true"),
        @NamedQuery(name = "Contacts.findById", query = "select c from ContactsEntity c where c.id = :id"),
})
public class ContactsEntity {
    private int id;
    private String lastname;
    private String firstname;
    private String bankAccount;
    private String email;
    private LocalDateTime registerDate;
    private LocalDateTime modificationDate;
    private String phoneNumber;
    private boolean isActive;
    private Collection<AddressesEntity> addressesById;
    private Collection<CompaniesContactsEntity> companiesContactsById;
    private UsersEntity usersByIdUsers;
    private CivilitiesEntity civilitiesByIdCivilities;
    private JobTitlesEntity jobTitlesByIdJobTitles;
    private ContactTypesEntity contactTypesByIdContactTypes;
    private BranchActivitiesEntity branchActivitiesByIdBranchActivities;
    private Collection<NotesEntity> notesById;
    private Collection<TasksEntity> tasksById;
    private Collection<TransactionsEntity> transactionsById;
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
    @Column(name = "Lastname", nullable = false)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
    @Column(name = "Bank_Account")
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Basic
    @Column(name = "Email")
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
    @Column(name = "Modification_Date")
    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Basic
    @Column(name = "Phone_Number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        ContactsEntity that = (ContactsEntity) o;
        return id == that.id && isActive == that.isActive && Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(bankAccount, that.bankAccount) && Objects.equals(email, that.email) && Objects.equals(registerDate, that.registerDate) && Objects.equals(modificationDate, that.modificationDate) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, bankAccount, email, registerDate, modificationDate, phoneNumber, isActive);
    }

    @OneToMany(mappedBy = "contactsByIdContacts")
    public Collection<AddressesEntity> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<AddressesEntity> addressesById) {
        this.addressesById = addressesById;
    }

    @OneToMany(mappedBy = "contactsByIdContacts")
    public Collection<CompaniesContactsEntity> getCompaniesContactsById() {
        return companiesContactsById;
    }

    public void setCompaniesContactsById(Collection<CompaniesContactsEntity> companiesContactsById) {
        this.companiesContactsById = companiesContactsById;
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
    @JoinColumn(name = "ID_Civilities", referencedColumnName = "ID")
    public CivilitiesEntity getCivilitiesByIdCivilities() {
        return civilitiesByIdCivilities;
    }

    public void setCivilitiesByIdCivilities(CivilitiesEntity civilitiesByIdCivilities) {
        this.civilitiesByIdCivilities = civilitiesByIdCivilities;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Job_Titles", referencedColumnName = "ID")
    public JobTitlesEntity getJobTitlesByIdJobTitles() {
        return jobTitlesByIdJobTitles;
    }

    public void setJobTitlesByIdJobTitles(JobTitlesEntity jobTitlesByIdJobTitles) {
        this.jobTitlesByIdJobTitles = jobTitlesByIdJobTitles;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Contact_Types", referencedColumnName = "ID", nullable = false)
    public ContactTypesEntity getContactTypesByIdContactTypes() {
        return contactTypesByIdContactTypes;
    }

    public void setContactTypesByIdContactTypes(ContactTypesEntity contactTypesByIdContactTypes) {
        this.contactTypesByIdContactTypes = contactTypesByIdContactTypes;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Branch_Activities", referencedColumnName = "ID")
    public BranchActivitiesEntity getBranchActivitiesByIdBranchActivities() {
        return branchActivitiesByIdBranchActivities;
    }

    public void setBranchActivitiesByIdBranchActivities(BranchActivitiesEntity branchActivitiesByIdBranchActivities) {
        this.branchActivitiesByIdBranchActivities = branchActivitiesByIdBranchActivities;
    }

    @OneToMany(mappedBy = "contactsByIdContacts")
    public Collection<NotesEntity> getNotesById() {
        return notesById;
    }

    public void setNotesById(Collection<NotesEntity> notesById) {
        this.notesById = notesById;
    }

    @OneToMany(mappedBy = "contactsByIdContacts")
    public Collection<TasksEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TasksEntity> tasksById) {
        this.tasksById = tasksById;
    }

    @OneToMany(mappedBy = "contactsByIdContacts")
    public Collection<TransactionsEntity> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<TransactionsEntity> transactionsById) {
        this.transactionsById = transactionsById;
    }

    @OneToMany(mappedBy = "contactsByIdContacts")
    public Collection<VouchersEntity> getVouchersById() {
        return vouchersById;
    }

    public void setVouchersById(Collection<VouchersEntity> vouchersById) {
        this.vouchersById = vouchersById;
    }
}

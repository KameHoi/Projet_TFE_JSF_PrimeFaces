package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "companies", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Companies.countActiveCompanies", query = "SELECT count(c.label) FROM CompaniesEntity c WHERE c.active=true and c.usersByIdUsers.id=:idUser"),
        @NamedQuery(name = "Companies.countAllCompanies", query = "SELECT count(c.label) FROM CompaniesEntity c WHERE c.usersByIdUsers.id=:idUser"),
        @NamedQuery(name = "Companies.findCompaniesEntityByIdUser", query = "select c from CompaniesEntity c where (c.usersByIdUsers.id = :idUser) order by c.registerDate desc"),
        @NamedQuery(name = "Companies.findByIdCompanyAndByIdUser", query = "select c from CompaniesEntity c where (c.id = :id and c.usersByIdUsers.id = :idUser)"),
        @NamedQuery(name = "Companies.findById", query = "select c from CompaniesEntity c where c.id=:id"),
})
public class CompaniesEntity {
    private int id;
    private String label;
    private String description;
    private String vatNumber;
    private String bankAccount;
    private String domainName;
    private int employeesNumber;
    private LocalDateTime modificationDate;
    private LocalDateTime registerDate;
    private Double annualSales;
    private Double revenue;
    private String linkedInPage;
    private int creationDate;
    private int closingDate;
    private String phoneNumber;
    private String email;
    private boolean isActive;
    private Collection<AddressesEntity> addressesById;
    private UsersEntity usersByIdUsers;
    private CompanyTypesEntity companyTypesByIdCompanyTypes;
    private BranchActivitiesEntity branchActivitiesByIdBranchActivities;
    private Collection<CompaniesContactsEntity> companiesContactsById;
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
    @Column(name = "Label", nullable = false)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
    @Column(name = "VAT_Number")
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
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
    @Column(name = "Domain_Name")
    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Basic
    @Column(name = "Employees_Number")
    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
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
    @Column(name = "Register_Date", nullable = false)
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "Annual_Sales")
    public Double getAnnualSales() {
        return annualSales;
    }

    public void setAnnualSales(Double annualSales) {
        this.annualSales = annualSales;
    }

    @Basic
    @Column(name = "Revenue")
    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    @Basic
    @Column(name = "LinkedIn_Page")
    public String getLinkedInPage() {
        return linkedInPage;
    }

    public void setLinkedInPage(String linkedInPage) {
        this.linkedInPage = linkedInPage;
    }

    @Basic
    @Column(name = "Creation_Date")
    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "Closing_Date")
    public int getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(int closingDate) {
        this.closingDate = closingDate;
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
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        CompaniesEntity that = (CompaniesEntity) o;
        return id == that.id && isActive == that.isActive && Objects.equals(label, that.label) && Objects.equals(description, that.description) && Objects.equals(vatNumber, that.vatNumber) && Objects.equals(bankAccount, that.bankAccount) && Objects.equals(domainName, that.domainName) && Objects.equals(employeesNumber, that.employeesNumber) && Objects.equals(modificationDate, that.modificationDate) && Objects.equals(registerDate, that.registerDate) && Objects.equals(annualSales, that.annualSales) && Objects.equals(revenue, that.revenue) && Objects.equals(linkedInPage, that.linkedInPage) && Objects.equals(creationDate, that.creationDate) && Objects.equals(closingDate, that.closingDate) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, description, vatNumber, bankAccount, domainName, employeesNumber, modificationDate, registerDate, annualSales, revenue, linkedInPage, creationDate, closingDate, phoneNumber, email, isActive);
    }

    @OneToMany(mappedBy = "companiesByIdCompanies")
    public Collection<AddressesEntity> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<AddressesEntity> addressesById) {
        this.addressesById = addressesById;
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
    @JoinColumn(name = "ID_Company_Types", referencedColumnName = "ID", nullable = false)
    public CompanyTypesEntity getCompanyTypesByIdCompanyTypes() {
        return companyTypesByIdCompanyTypes;
    }

    public void setCompanyTypesByIdCompanyTypes(CompanyTypesEntity companyTypesByIdCompanyTypes) {
        this.companyTypesByIdCompanyTypes = companyTypesByIdCompanyTypes;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Branch_Activities", referencedColumnName = "ID")
    public BranchActivitiesEntity getBranchActivitiesByIdBranchActivities() {
        return branchActivitiesByIdBranchActivities;
    }

    public void setBranchActivitiesByIdBranchActivities(BranchActivitiesEntity branchActivitiesByIdBranchActivities) {
        this.branchActivitiesByIdBranchActivities = branchActivitiesByIdBranchActivities;
    }

    @OneToMany(mappedBy = "companiesByIdCompanies")
    public Collection<CompaniesContactsEntity> getCompaniesContactsById() {
        return companiesContactsById;
    }

    public void setCompaniesContactsById(Collection<CompaniesContactsEntity> companiesContactsById) {
        this.companiesContactsById = companiesContactsById;
    }

    @OneToMany(mappedBy = "companiesByIdCompanies")
    public Collection<NotesEntity> getNotesById() {
        return notesById;
    }

    public void setNotesById(Collection<NotesEntity> notesById) {
        this.notesById = notesById;
    }

    @OneToMany(mappedBy = "companiesByIdCompanies")
    public Collection<TasksEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TasksEntity> tasksById) {
        this.tasksById = tasksById;
    }

    @OneToMany(mappedBy = "companiesByIdCompanies")
    public Collection<TransactionsEntity> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<TransactionsEntity> transactionsById) {
        this.transactionsById = transactionsById;
    }

    @OneToMany(mappedBy = "companiesByIdCompanies")
    public Collection<VouchersEntity> getVouchersById() {
        return vouchersById;
    }

    public void setVouchersById(Collection<VouchersEntity> vouchersById) {
        this.vouchersById = vouchersById;
    }
}

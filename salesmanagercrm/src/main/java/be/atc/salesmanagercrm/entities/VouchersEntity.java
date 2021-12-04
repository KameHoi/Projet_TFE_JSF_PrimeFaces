package be.atc.salesmanagercrm.entities;

import be.atc.salesmanagercrm.enums.EnumPriority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "vouchers", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Vouchers.findVouchersEntityByContactsByIdContacts", query = "select v from VouchersEntity v where (v.contactsByIdContacts.id = :id and v.usersByIdUsers.id = :idUser) ORDER BY v.creationDate desc "),
        @NamedQuery(name = "Vouchers.findVouchersEntityByCompaniesByIdCompanies", query = "select v from VouchersEntity v where (v.companiesByIdCompanies.id = :id and v.usersByIdUsers.id = :idUser) ORDER BY v.creationDate desc "),
        @NamedQuery(name = "Vouchers.findAll", query = "SELECT v from VouchersEntity v where v.usersByIdUsers.id = :idUser ORDER BY v.creationDate desc "),
        @NamedQuery(name = "Vouchers.countActiveVouchers", query = "SELECT COUNT(v.title) from VouchersEntity v where v.usersByIdUsers.id = :idUser"),
        @NamedQuery(name = "Vouchers.findById", query = "SELECT v from VouchersEntity v where (v.id = :id and v.usersByIdUsers.id = :idUser)"),
        @NamedQuery(name = "Vouchers.countVouchersActiveStatus", query = "SELECT count(v.id)  from VouchersEntity v where (v.usersByIdUsers.id = :idUser and v.voucherStatusByIdVoucherStatus.label = :voucherStatus)"),

})
public class VouchersEntity {
    private int id;
    private String title;
    private String description;
    private EnumPriority priority;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private Collection<VoucherHistoriesEntity> voucherHistoriesById;
    private UsersEntity usersByIdUsers;
    private ContactsEntity contactsByIdContacts;
    private CompaniesEntity companiesByIdCompanies;
    private VoucherStatusEntity voucherStatusByIdVoucherStatus;

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

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VouchersEntity that = (VouchersEntity) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(priority, that.priority) && Objects.equals(creationDate, that.creationDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, priority, creationDate, endDate);
    }

    @OneToMany(mappedBy = "vouchersByIdVouchers")
    public Collection<VoucherHistoriesEntity> getVoucherHistoriesById() {
        return voucherHistoriesById;
    }

    public void setVoucherHistoriesById(Collection<VoucherHistoriesEntity> voucherHistoriesById) {
        this.voucherHistoriesById = voucherHistoriesById;
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
    @JoinColumn(name = "ID_Voucher_Status", referencedColumnName = "ID", nullable = false)
    public VoucherStatusEntity getVoucherStatusByIdVoucherStatus() {
        return voucherStatusByIdVoucherStatus;
    }

    public void setVoucherStatusByIdVoucherStatus(VoucherStatusEntity voucherStatusByIdVoucherStatus) {
        this.voucherStatusByIdVoucherStatus = voucherStatusByIdVoucherStatus;
    }
}

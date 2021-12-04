package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "transactions", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Transactions.findTransactionsEntityByContactsByIdContacts", query = "select t from TransactionsEntity t where (t.contactsByIdContacts.id = :id and t.usersByIdUsers.id = :idUser and t.active = true)  ORDER BY t.creationDate desc "),
        @NamedQuery(name = "Transactions.findTransactionsEntityByCompaniesByIdCompanies", query = "select t from TransactionsEntity t where (t.companiesByIdCompanies.id = :id and t.usersByIdUsers.id = :idUser and t.active = true) ORDER BY t.creationDate desc "),
        @NamedQuery(name = "Transactions.findAll", query = "SELECT t from TransactionsEntity t where (t.usersByIdUsers.id = :idUser and t.active = true) ORDER BY t.creationDate desc "),
        @NamedQuery(name = "Transactions.findById", query = "SELECT t from TransactionsEntity t where (t.id = :id and t.usersByIdUsers.id = :idUser and t.active = true)"),
        @NamedQuery(name = "Transactions.countActiveTransactions", query = "SELECT COUNT(t) from TransactionsEntity t where (t.usersByIdUsers.id = :idUser and t.active = true)"),
        @NamedQuery(name = "Transactions.findAllByPhase", query = "SELECT t from TransactionsEntity t where (t.usersByIdUsers.id = :idUser and t.active = true and t.transactionPhasesByIdTransactionPhases.label = :phaseTransaction) ORDER BY t.creationDate desc "),
        @NamedQuery(name = "Transactions.countTransactionsActivePhase", query = "SELECT count(t.id) from TransactionsEntity t where (t.usersByIdUsers.id = :idUser and t.active = true and t.transactionPhasesByIdTransactionPhases.label = :phaseTransaction)"),

})
public class TransactionsEntity {
    private int id;
    private String title;
    private Double amount;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private boolean isActive;
    private Collection<TransactionHistoriesEntity> transactionHistoriesById;
    private UsersEntity usersByIdUsers;
    private ContactsEntity contactsByIdContacts;
    private CompaniesEntity companiesByIdCompanies;
    private TransactionTypesEntity transactionTypesByIdTransactionTypes;
    private TransactionPhasesEntity transactionPhasesByIdTransactionPhases;

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
    @Column(name = "Amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "Creation_Date")
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
        TransactionsEntity that = (TransactionsEntity) o;
        return id == that.id && isActive == that.isActive && Objects.equals(title, that.title) && Objects.equals(amount, that.amount) && Objects.equals(creationDate, that.creationDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, amount, creationDate, endDate, isActive);
    }

    @OneToMany(mappedBy = "transactionsByIdTransactions")
    public Collection<TransactionHistoriesEntity> getTransactionHistoriesById() {
        return transactionHistoriesById;
    }

    public void setTransactionHistoriesById(Collection<TransactionHistoriesEntity> transactionHistoriesById) {
        this.transactionHistoriesById = transactionHistoriesById;
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
    @JoinColumn(name = "ID_Transaction_Types", referencedColumnName = "ID")
    public TransactionTypesEntity getTransactionTypesByIdTransactionTypes() {
        return transactionTypesByIdTransactionTypes;
    }

    public void setTransactionTypesByIdTransactionTypes(TransactionTypesEntity transactionTypesByIdTransactionTypes) {
        this.transactionTypesByIdTransactionTypes = transactionTypesByIdTransactionTypes;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Transaction_Phases", referencedColumnName = "ID")
    public TransactionPhasesEntity getTransactionPhasesByIdTransactionPhases() {
        return transactionPhasesByIdTransactionPhases;
    }

    public void setTransactionPhasesByIdTransactionPhases(TransactionPhasesEntity transactionPhasesByIdTransactionPhases) {
        this.transactionPhasesByIdTransactionPhases = transactionPhasesByIdTransactionPhases;
    }
}

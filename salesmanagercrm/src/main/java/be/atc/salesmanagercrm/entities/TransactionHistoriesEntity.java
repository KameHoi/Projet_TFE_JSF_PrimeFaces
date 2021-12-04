package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transaction_histories", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "TransactionHistories.findAllByIdUserAndByIdTransaction", query = "select tH from TransactionHistoriesEntity tH where (tH.transactionsByIdTransactions.id = :idTransaction and tH.transactionsByIdTransactions.usersByIdUsers.id = :idUser and tH.transactionsByIdTransactions.active = true)  ORDER BY tH.saveDate desc "),
})
public class TransactionHistoriesEntity {
    private int id;
    private LocalDateTime saveDate;
    private TransactionsEntity transactionsByIdTransactions;
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
    @Column(name = "Save_Date", nullable = false)
    public LocalDateTime getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(LocalDateTime saveDate) {
        this.saveDate = saveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionHistoriesEntity that = (TransactionHistoriesEntity) o;
        return id == that.id && Objects.equals(saveDate, that.saveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saveDate);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Transactions", referencedColumnName = "ID", nullable = false)
    public TransactionsEntity getTransactionsByIdTransactions() {
        return transactionsByIdTransactions;
    }

    public void setTransactionsByIdTransactions(TransactionsEntity transactionsByIdTransactions) {
        this.transactionsByIdTransactions = transactionsByIdTransactions;
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

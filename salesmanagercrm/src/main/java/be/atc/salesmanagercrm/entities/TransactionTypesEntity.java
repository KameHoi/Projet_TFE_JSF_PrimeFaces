package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "transaction_types", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "TransactionTypes.findAll", query = "SELECT tT from TransactionTypesEntity tT"),
        @NamedQuery(name = "TransactionTypes.findTransactionTypesEntityByLabel", query = "SELECT tT from TransactionTypesEntity tT where tT.label = :label"),
})
public class TransactionTypesEntity {
    private int id;
    private String label;
    private Collection<TransactionsEntity> transactionsById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionTypesEntity that = (TransactionTypesEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "transactionTypesByIdTransactionTypes")
    public Collection<TransactionsEntity> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<TransactionsEntity> transactionsById) {
        this.transactionsById = transactionsById;
    }
}

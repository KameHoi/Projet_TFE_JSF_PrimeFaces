package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "voucher_status", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "VoucherStatus.findAll", query = "SELECT vS from VoucherStatusEntity vS"),
        @NamedQuery(name = "VoucherStatus.findVoucherStatusEntityByLabel", query = "SELECT vS from VoucherStatusEntity vS where vS.label = :label"),
})
public class VoucherStatusEntity {
    private int id;
    private String label;
    private Collection<VoucherHistoriesEntity> voucherHistoriesById;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoucherStatusEntity that = (VoucherStatusEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "voucherStatusByIdVoucherStatus")
    public Collection<VoucherHistoriesEntity> getVoucherHistoriesById() {
        return voucherHistoriesById;
    }

    public void setVoucherHistoriesById(Collection<VoucherHistoriesEntity> voucherHistoriesById) {
        this.voucherHistoriesById = voucherHistoriesById;
    }

    @OneToMany(mappedBy = "voucherStatusByIdVoucherStatus")
    public Collection<VouchersEntity> getVouchersById() {
        return vouchersById;
    }

    public void setVouchersById(Collection<VouchersEntity> vouchersById) {
        this.vouchersById = vouchersById;
    }
}

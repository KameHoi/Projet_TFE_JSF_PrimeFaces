package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "voucher_histories", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "VoucherHistories.findAllByIdUserAndByIdVoucher", query = "select v from VoucherHistoriesEntity v where (v.vouchersByIdVouchers.id = :idVoucher and v.vouchersByIdVouchers.usersByIdUsers.id = :idUser) ORDER BY v.saveDate desc "),
})
public class VoucherHistoriesEntity {
    private int id;
    private LocalDateTime saveDate;
    private VouchersEntity vouchersByIdVouchers;
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
        VoucherHistoriesEntity that = (VoucherHistoriesEntity) o;
        return id == that.id && Objects.equals(saveDate, that.saveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saveDate);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Vouchers", referencedColumnName = "ID", nullable = false)
    public VouchersEntity getVouchersByIdVouchers() {
        return vouchersByIdVouchers;
    }

    public void setVouchersByIdVouchers(VouchersEntity vouchersByIdVouchers) {
        this.vouchersByIdVouchers = vouchersByIdVouchers;
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

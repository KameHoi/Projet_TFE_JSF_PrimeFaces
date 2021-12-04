package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "company_types", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "CompanyTypes.findAll", query = "SELECT ct from CompanyTypesEntity ct"),
        @NamedQuery(name = "CompanyTypes.findByLabel", query = "select ct from CompanyTypesEntity ct where ( lower(ct.label) like lower(concat('%', :label ,'%')))")
})
public class CompanyTypesEntity {
    private int id;
    private String label;
    private Collection<CompaniesEntity> companiesById;

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
        CompanyTypesEntity that = (CompanyTypesEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "companyTypesByIdCompanyTypes")
    public Collection<CompaniesEntity> getCompaniesById() {
        return companiesById;
    }

    public void setCompaniesById(Collection<CompaniesEntity> companiesById) {
        this.companiesById = companiesById;
    }
}

package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cities", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Cities.findAll", query = "SELECT c from CitiesEntity c"),
})
public class CitiesEntity {
    private int id;
    private String region;
    private String postalCode;
    private String label;
    private Collection<AddressesEntity> addressesById;
    private CountriesEntity countriesByIdCountries;

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
    @Column(name = "Region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "Postal_Code", nullable = false)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
        CitiesEntity that = (CitiesEntity) o;
        return id == that.id && Objects.equals(region, that.region) && Objects.equals(postalCode, that.postalCode) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, region, postalCode, label);
    }

    @OneToMany(mappedBy = "citiesByIdCities")
    public Collection<AddressesEntity> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<AddressesEntity> addressesById) {
        this.addressesById = addressesById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Countries", referencedColumnName = "ID", nullable = false)
    public CountriesEntity getCountriesByIdCountries() {
        return countriesByIdCountries;
    }

    public void setCountriesByIdCountries(CountriesEntity countriesByIdCountries) {
        this.countriesByIdCountries = countriesByIdCountries;
    }
}

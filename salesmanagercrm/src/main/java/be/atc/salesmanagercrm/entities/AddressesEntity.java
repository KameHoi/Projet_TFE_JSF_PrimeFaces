package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Addresses.findByIdCompanies", query = "SELECT a from AddressesEntity a where a.companiesByIdCompanies.id = :id"),
        @NamedQuery(name = "Addresses.findByIdContacts", query = "SELECT a from AddressesEntity a where a.contactsByIdContacts.id = :id"),
})
public class AddressesEntity {
    private int id;
    private String street;
    private String number;
    private String box;
    private CitiesEntity citiesByIdCities;
    private ContactsEntity contactsByIdContacts;
    private CompaniesEntity companiesByIdCompanies;

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
    @Column(name = "Street", nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "Number", nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "Box")
    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressesEntity that = (AddressesEntity) o;
        return id == that.id && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(box, that.box);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, box);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cities", referencedColumnName = "ID", nullable = false)
    public CitiesEntity getCitiesByIdCities() {
        return citiesByIdCities;
    }

    public void setCitiesByIdCities(CitiesEntity citiesByIdCities) {
        this.citiesByIdCities = citiesByIdCities;
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
}

package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "job_titles", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "JobTitles.findAll", query = "SELECT j from JobTitlesEntity j order by j.id DESC"),
        @NamedQuery(name = "JobTitles.findByLabel", query = "select j from JobTitlesEntity j where ( lower(j.label) like lower(:label ))")
})
public class JobTitlesEntity {
    private int id;
    private String label;
    private Collection<ContactsEntity> contactsById;

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
        JobTitlesEntity that = (JobTitlesEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "jobTitlesByIdJobTitles")
    public Collection<ContactsEntity> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<ContactsEntity> contactsById) {
        this.contactsById = contactsById;
    }
}

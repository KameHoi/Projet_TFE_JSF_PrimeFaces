package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "permissions", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Permissions.findAllPermissions", query = "select p from PermissionsEntity p"),
        @NamedQuery(name = "Permissions.findByLabel", query = "select p from PermissionsEntity p where p.label = :label"),
})
public class PermissionsEntity {
    private int id;
    private String label;
    private Collection<RolesPermissionsEntity> rolesPermissionsById;

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
    @Column(name = "Label", nullable = false, unique = true)
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
        PermissionsEntity that = (PermissionsEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "permissionsByIdPermissions")
    public Collection<RolesPermissionsEntity> getRolesPermissionsById() {
        return rolesPermissionsById;
    }

    public void setRolesPermissionsById(Collection<RolesPermissionsEntity> rolesPermissionsById) {
        this.rolesPermissionsById = rolesPermissionsById;
    }
}

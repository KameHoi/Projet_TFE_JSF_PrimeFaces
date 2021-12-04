package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roles", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Roles.findAllRoles", query = "select r from RolesEntity r order by r.active desc"),
        @NamedQuery(name = "Roles.findByLabel", query = "select r from RolesEntity r where r.label = :label"),
        @NamedQuery(name = "Roles.findForDeleteSafe", query = "select r from RolesEntity r join UsersEntity u on u.rolesByIdRoles.id = r.id where r.id = :id and u.active = true"),
        @NamedQuery(name = "Roles.findAllActiveRoles", query = "select r from RolesEntity r where r.active = true"),
        @NamedQuery(name = "Roles.checkRoleForConnection", query = "select r from RolesEntity r where r.active = true and r.label = :label"),
        @NamedQuery(name = "Roles.checkRoleActive", query = "select r from RolesEntity r where r.active = true and r.id = :id"),
})
public class RolesEntity {
    private int id;
    private String label;
    private boolean isActive;
    private Collection<RolesPermissionsEntity> rolesPermissionsById;
    private Collection<UsersEntity> usersById;

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
        RolesEntity that = (RolesEntity) o;
        return id == that.id && isActive == that.isActive && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, isActive);
    }

    @OneToMany(mappedBy = "rolesByIdRoles")
    public Collection<RolesPermissionsEntity> getRolesPermissionsById() {
        return rolesPermissionsById;
    }

    public void setRolesPermissionsById(Collection<RolesPermissionsEntity> rolesPermissionsById) {
        this.rolesPermissionsById = rolesPermissionsById;
    }

    @OneToMany(mappedBy = "rolesByIdRoles")
    public Collection<UsersEntity> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<UsersEntity> usersById) {
        this.usersById = usersById;
    }
}

package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles_permissions", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "RolesPermissions.findAllRolesPermissions", query = "select rp from RolesPermissionsEntity rp order by rp.permissionsByIdPermissions.id"),
        @NamedQuery(name = "RolesPermissions.findComboRolePermission", query = "select rp from RolesPermissionsEntity rp where rp.rolesByIdRoles.id = :idRole and rp.permissionsByIdPermissions.id = :idPermission order by rp.permissionsByIdPermissions.id"),
        @NamedQuery(name = "RolesPermissions.findAllRolesPermissionsWithIdRole", query = "select rp from RolesPermissionsEntity rp where rp.rolesByIdRoles.id = :idRole order by rp.permissionsByIdPermissions.id"),
        @NamedQuery(name = "RolesPermissions.deleteComboRolePermission", query = "delete from RolesPermissionsEntity rp where rp.rolesByIdRoles.id = :idRole and rp.permissionsByIdPermissions.id = :idPermission"),
        @NamedQuery(name = "RolesPermissions.deleteRolesPermissions", query = "delete from RolesPermissionsEntity rp where rp.rolesByIdRoles.id = :idRole"),
        @NamedQuery(name = "RolesPermissions.findPermissionWithRole", query = "select rp from RolesPermissionsEntity rp where rp.rolesByIdRoles.id = :idRole"),

})
public class RolesPermissionsEntity {
    private int id;
    private RolesEntity rolesByIdRoles;
    private PermissionsEntity permissionsByIdPermissions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesPermissionsEntity that = (RolesPermissionsEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Roles", referencedColumnName = "ID", nullable = false)
    public RolesEntity getRolesByIdRoles() {
        return rolesByIdRoles;
    }

    public void setRolesByIdRoles(RolesEntity rolesByIdRoles) {
        this.rolesByIdRoles = rolesByIdRoles;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Permissions", referencedColumnName = "ID", nullable = false)
    public PermissionsEntity getPermissionsByIdPermissions() {
        return permissionsByIdPermissions;
    }

    public void setPermissionsByIdPermissions(PermissionsEntity permissionsByIdPermissions) {
        this.permissionsByIdPermissions = permissionsByIdPermissions;
    }
}

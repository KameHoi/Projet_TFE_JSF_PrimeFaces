package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.PermissionsDao;
import be.atc.salesmanagercrm.dao.impl.PermissionsDaoImpl;
import be.atc.salesmanagercrm.entities.PermissionsEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.utils.EMF;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Larché Marie-Élise
 */
@Slf4j
@Named(value = "permissionsBean")
@SessionScoped
public class PermissionsBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -2338626292552177485L;

    @Getter
    @Setter
    private PermissionsDao dao = new PermissionsDaoImpl();
    @Getter
    @Setter
    private PermissionsEntity permissionsEntity = new PermissionsEntity();
    @Getter
    @Setter
    private List<PermissionsEntity> permissionsEntityList;
    @Getter
    @Setter
    private List<PermissionsEntity> permissionsEntities = new ArrayList<>();

    @Getter
    @Setter
    private PermissionsEntity selectedPermissionsEntity;
    @Getter
    @Setter
    private List<PermissionsEntity> permissionsEntitiesFiltered;
    @Getter
    @Setter
    private List<PermissionsEntity> permissionsEntitiesList;

    /**
     * call findAll()
     */
    public void findAllPermissions() {
        log.info("bgin findallpermissions");
        permissionsEntityList = findAll();
        log.info(String.valueOf(permissionsEntityList));
    }

    /**
     * @return List<PermissionsEntity>
     */
    protected List<PermissionsEntity> findAll() {
        log.info("begin findall");
        EntityManager em = EMF.getEM();
        List<PermissionsEntity> permissionsEntities = dao.findAllPermissions(em);

        em.clear();
        em.close();

        return permissionsEntities;
    }

    /**
     * @param label String
     * @return PermissionsEntity
     */
    public PermissionsEntity findByLabel(String label) {
        if (label == null) {
            log.info("label null in permbean");
            throw new EntityNotFoundException("aucune permission avec le perml " + label + " n'a été trouvé en db", ErrorCodes.PERMISSION_NOT_FOUND);
        }

        EntityManager em = EMF.getEM();
        Optional<PermissionsEntity> optionalPermissionsEntity;
        try {
            optionalPermissionsEntity = dao.findByLabel(em, label);
        } finally {
            em.clear();
            em.close();
        }

        return optionalPermissionsEntity.orElseThrow(() ->
                new EntityNotFoundException("aucune permission avec le perml " + label + " n'a été trouvé en db", ErrorCodes.ROLES_NOT_FOUND)
        );
    }


}

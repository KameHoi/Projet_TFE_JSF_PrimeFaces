package be.atc.salesmanagercrm.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static be.atc.salesmanagercrm.utils.Constants.PERSISTENCE_UNIT_NAME;

/**
 * Class to get a connection to the database
 *
 * @author Renaud DIANA
 */
public final class EMF {

    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    private EMF() {
    }

    public static EntityManagerFactory getEMF() {
        return emfInstance;
    }

    public static EntityManager getEM() {
        return emfInstance.createEntityManager();
    }

    /*	Create EntityManager in others classes
     * EntityManager em = EMF.getEM();
     * try {
     *     // ... do stuff with em ...
     * } finally {
     *     em.close();
     * }
     */
}

package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.*;
import be.atc.salesmanagercrm.dao.impl.*;
import be.atc.salesmanagercrm.entities.*;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@SessionScoped
public class CheckEntities extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -6795998607327751632L;

    @Getter
    @Setter
    private ContactsDao contactsDao = new ContactsDaoImpl();
    @Getter
    @Setter
    private UsersDao usersDao = new UsersDaoImpl();
    @Getter
    @Setter
    private CompaniesDao companiesDao = new CompaniesDaoImpl();
    @Getter
    @Setter
    private CompanyTypesDao companyTypesDao = new CompanyTypesDaoImpl();
    @Getter
    @Setter
    private TaskTypesDao taskTypesDao = new TaskTypesDaoImpl();
    @Getter
    @Setter
    private JobTitlesDao jobTitlesDao = new JobTitlesDaoImpl();
    @Getter
    @Setter
    private BranchActivitiesDao branchActivitiesDao = new BranchActivitiesDaoImpl();
    @Getter
    @Setter
    private VoucherStatusDao voucherStatusDao = new VoucherStatusDaoImpl();
    @Getter
    @Setter
    private TransactionTypesDao transactionTypesDao = new TransactionTypesDaoImpl();
    @Getter
    @Setter
    private TransactionPhasesDao transactionPhasesDao = new TransactionPhasesDaoImpl();
    @Getter
    @Setter
    private RolesDao rolesDao = new RolesDaoImpl();
    @Getter
    @Setter
    private PermissionsDao permissionsDao = new PermissionsDaoImpl();
    @Getter
    @Setter
    private RolesPermissionsDao rolesPermissionsDao = new RolesPermissionsDaoImpl();
    @Getter
    @Setter
    private ContactTypesDao contactTypesDao = new ContactTypesDaoImpl();


    /**
     * Check if Contact exist in DB
     *
     * @param entity ContactsEntity
     */
    public void checkContact(ContactsEntity entity) {
        if (entity == null) {
            log.error("Contact Entity is null");
            throw new EntityNotFoundException(
                    "Aucun contact n a ete trouve", ErrorCodes.CONTACT_NOT_FOUND
            );
        }
        Optional<ContactsEntity> contactsEntity;
        EntityManager em = EMF.getEM();
        try {
            contactsEntity = contactsDao.findById(em, entity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (!contactsEntity.isPresent()) {
            log.warn("Contact with ID {} was not found in the DB", entity.getId());
            throw new EntityNotFoundException(
                    "Aucun contact avec l ID " + entity.getId() + " n a ete trouve dans la BDD", ErrorCodes.CONTACT_NOT_FOUND
            );
        }
    }

    /**
     * Check if company exist in DB
     *
     * @param entity    CompaniesEntity
     */
    public void checkCompany(CompaniesEntity entity) {
        if (entity == null) {
            log.error("Company Entity is null");
            throw new EntityNotFoundException(
                    "Aucune compagnie n a ete trouve", ErrorCodes.COMPANY_NOT_FOUND
            );
        }
        Optional<CompaniesEntity> companiesEntity;
        EntityManager em = EMF.getEM();
        try {
            companiesEntity = companiesDao.findById(em, entity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (!companiesEntity.isPresent()) {
            log.warn("Company with ID {} was not found in the DB", entity.getId());
            throw new EntityNotFoundException(
                    "Aucune compagnie avec l ID " + entity.getId() + " n a ete trouvee dans la BDD", ErrorCodes.COMPANY_NOT_FOUND
            );
        }
    }


    /**
     * Check if user exist in DB
     *
     * @param entity    UsersEntity
     */

    public void checkUser(UsersEntity entity) {
        if (entity == null) {
            log.error("User Entity is null");
            throw new EntityNotFoundException(
                    "Aucun utilisateur n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }
        UsersEntity usersEntity;
        EntityManager em = EMF.getEM();
        try {
            usersEntity = usersDao.findById(em, entity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (usersEntity == null) {
            log.warn("User with ID {} was not found in the DB", entity.getId());
            throw new EntityNotFoundException(
                    "Aucun utilisateur avec l ID " + entity.getId() + " n a ete trouve dans la BDD", ErrorCodes.USER_NOT_FOUND
            );
        }

        if (!usersEntity.isActive()) {
            log.warn("User with ID {} is not active", entity.getId());
            throw new InvalidEntityException(
                    "L utilisateur avec l id " + usersEntity.getId() + " est desactive", ErrorCodes.USER_NOT_VALID
            );
        }
    }

    /**
     * Check if JobTitle already exist in DB
     *
     * @param jobTitlesEntity JobTitlesEntity
     */
    public void checkJobTitlesLabel(JobTitlesEntity jobTitlesEntity) {
        if (jobTitlesEntity == null) {
            log.error("JobTitles Entity is null");
            throw new EntityNotFoundException(
                    "Aucun intitule du poste n a ete trouve", ErrorCodes.JOBTITLES_NOT_FOUND
            );
        }
        List<JobTitlesEntity> jobTitlesEntityList;

        EntityManager em = EMF.getEM();
        try {
            jobTitlesEntityList = jobTitlesDao.findByLabel(em, jobTitlesEntity.getLabel());
        } finally {
            em.clear();
            em.close();
        }

        if (!(jobTitlesEntityList.isEmpty())) {
            log.warn("Job Title label already exist");
            throw new InvalidEntityException(
                    "L'intitulé du poste " + jobTitlesEntity.getLabel() + " existe déjà", ErrorCodes.JOBTITLES_NOT_VALID
            );
        }

    }

    /**
     * Check if Branch_Activities label already exist in DB
     *
     * @param branchActivitiesEntity BranchActivitiesEntity
     */
    public void checkBranchActivitiesLabel(BranchActivitiesEntity branchActivitiesEntity) {
        if (branchActivitiesEntity == null) {
            log.error("Branch Activity Entity is null");
            throw new EntityNotFoundException(
                    "Aucun secteur d activite n a ete trouve", ErrorCodes.BRANCHACTIVITIES_NOT_FOUND
            );
        }
        List<BranchActivitiesEntity> branchActivitiesEntities;
        EntityManager em = EMF.getEM();
        try {
            branchActivitiesEntities = branchActivitiesDao.findByLabel(em, branchActivitiesEntity.getLabel());
        } finally {
            em.clear();
            em.close();
        }
        if (!(branchActivitiesEntities.isEmpty())) {
            log.warn("Branch activities label already exist");
            throw new InvalidEntityException(
                    "Le secteur d activite " + branchActivitiesEntity.getLabel() + " existe déjà", ErrorCodes.BRANCHACTIVITIESLABEL_NOT_VALID
            );
        }
    }


    /**
     * Check if TaskType exist in DB
     *
     * @param entity    TaskTypesEntity
     */
    public void checkTaskType(TaskTypesEntity entity) {
        if (entity == null) {
            log.error("TaskType Entity is null");
            throw new EntityNotFoundException(
                    "Aucun type de tache n a ete trouve", ErrorCodes.TASKTYPE_NOT_FOUND
            );
        }
        TaskTypesEntity taskTypesEntity;
        EntityManager em = EMF.getEM();
        try {
            taskTypesEntity = taskTypesDao.findById(em, entity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (taskTypesEntity == null) {
            log.warn("Task type with ID {} was not found in the DB", entity.getId());
            throw new EntityNotFoundException(
                    "Aucun type de tâche avec l ID " + entity.getId() + " n a ete trouve dans la BDD", ErrorCodes.TASKTYPE_NOT_FOUND
            );
        }
    }


    /**
     * Check if VoucherStatus exit in DB
     *
     * @param entity    VoucherStatusEntity
     */
    public void checkVoucherStatus(VoucherStatusEntity entity) {
        if (entity == null) {
            log.error("Voucher Status Entity is null");
            throw new EntityNotFoundException(
                    "Aucun statut de ticket n a ete trouve", ErrorCodes.VOUCHERSTATUS_NOT_FOUND
            );
        }
        VoucherStatusEntity voucherStatusEntity;
        EntityManager em = EMF.getEM();
        try {
            voucherStatusEntity = voucherStatusDao.findById(em, entity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (voucherStatusEntity == null) {
            log.warn("Voucher Status with ID {} was not found in the DB", entity.getId());
            throw new EntityNotFoundException(
                    "Aucun statut de ticket avec l ID " + entity.getId() + " n a ete trouvee dans la BDD", ErrorCodes.VOUCHERSTATUS_NOT_FOUND
            );
        }
    }

    /**
     * Check if CompanyTypes exist in DB
     *
     * @param companyTypesEntity CompanyTypesEntity
     */
    public void checkCompanyTypes(CompanyTypesEntity companyTypesEntity) {
        if (companyTypesEntity == null) {
            log.error("Company type Entity is null");
            throw new EntityNotFoundException(
                    "Aucun type de compagnie n a ete trouve", ErrorCodes.COMPANYTYPES_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        CompanyTypesEntity companyTypesEntityToCheck;
        try {
            companyTypesEntityToCheck = companyTypesDao.findById(em, companyTypesEntity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (companyTypesEntityToCheck == null) {
            log.warn("Company type with ID {} was not found in the DB", companyTypesEntity.getId());
            throw new EntityNotFoundException(
                    "Aucun type de société avec l ID " + companyTypesEntity.getId() + " n a ete trouvé dans la DB", ErrorCodes.COMPANYTYPES_NOT_FOUND
            );
        }
    }


    /**
     * Check if contact type exist
     *
     * @param contactTypesEntity ContactTypesEntity
     */
    public void checkContactType(ContactTypesEntity contactTypesEntity) {
        if (contactTypesEntity == null) {
            log.error("Contact type Entity is null");
            throw new EntityNotFoundException(
                    "Aucun type de contact n a ete trouve", ErrorCodes.CONTACTTYPE_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        ContactTypesEntity contactTypesEntityToCheck;
        try {
            contactTypesEntityToCheck = contactTypesDao.findById(em, contactTypesEntity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (contactTypesEntityToCheck == null) {
            log.warn("Contact type with ID {} was not found in the DB", contactTypesEntity.getId());
            throw new EntityNotFoundException(
                    "Aucun type de contact avec l ID " + contactTypesEntity.getId() + " n'a été trouvé dans la DB", ErrorCodes.CONTACTTYPE_NOT_FOUND
            );
        }
    }

    /**
     * Check if jobtitle exist
     *
     * @param jobTitlesEntity jobTitlesEntity
     */
    public void checkJobTitles(JobTitlesEntity jobTitlesEntity) {
        if (jobTitlesEntity == null) {
            log.error("JobTitles Entity is null");
            throw new EntityNotFoundException(
                    "Aucun intitule du poste n a ete trouve", ErrorCodes.JOBTITLES_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        JobTitlesEntity jobTitlesEntityToCheck;
        try {
            jobTitlesEntityToCheck = jobTitlesDao.findById(em, jobTitlesEntity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (jobTitlesEntityToCheck == null) {
            log.warn("Contact type with ID {} was not found in the DB", jobTitlesEntity.getId());
            throw new EntityNotFoundException(
                    "Aucun intitulé de poste avec l ID " + jobTitlesEntity.getId() + " n'a été trouvé dans la DB", ErrorCodes.JOBTITLES_NOT_FOUND
            );
        }
    }

    /**
     * Check if branch activities exist
     *
     * @param branchActivitiesEntity BranchActivitiesEntity
     */
    public void checkBranchActivities(BranchActivitiesEntity branchActivitiesEntity) {
        if (branchActivitiesEntity == null) {
            log.error("Branch Activity Entity is null");
            throw new EntityNotFoundException(
                    "Aucun secteur d activite n a ete trouve", ErrorCodes.BRANCHACTIVITIES_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        BranchActivitiesEntity branchActivitiesEntityToCheck;
        try {
            branchActivitiesEntityToCheck = branchActivitiesDao.findById(em, branchActivitiesEntity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (branchActivitiesEntityToCheck == null) {
            log.warn("Company type with ID {} was not found in the DB", branchActivitiesEntity.getId());
            throw new EntityNotFoundException(
                    "Aucun type de société avec l ID " + branchActivitiesEntity.getId() + " n a ete trouvé dans la DB", ErrorCodes.BRANCHACTIVITIES_NOT_FOUND
            );
        }
    }

    /**
     * Check if TransactionType exit in DB
     *
     * @param entity    TransactionTypesEntity
     */
    public void checkTransactionTypes(TransactionTypesEntity entity) {
        if (entity == null) {
            log.error("Transation Type Entity is null");
            throw new EntityNotFoundException(
                    "Aucun type de transaction n a ete trouve", ErrorCodes.TRANSACTIONTYPE_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        TransactionTypesEntity transactionTypesEntity;
        try {
            transactionTypesEntity = transactionTypesDao.findById(em, entity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (transactionTypesEntity == null) {
            log.warn("Transation Type with ID {} was not found in the DB", entity.getId());
            throw new EntityNotFoundException(
                    "Aucun type de transaction avec l ID " + entity.getId() + " n a ete trouvee dans la BDD", ErrorCodes.TRANSACTIONTYPE_NOT_FOUND
            );
        }
    }

    /**
     * Check if TransactionPhase exit in DB
     *
     * @param entity    TransactionPhasesEntity
     */
    public void checkTransactionPhases(TransactionPhasesEntity entity) {
        if (entity == null) {
            log.error("Transation Phase Entity is null");
            throw new EntityNotFoundException(
                    "Aucune phase de transaction n a ete trouve", ErrorCodes.TRANSACTIONPHASE_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        TransactionPhasesEntity transactionPhasesEntity;
        try {
            transactionPhasesEntity = transactionPhasesDao.findById(em, entity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (transactionPhasesEntity == null) {
            log.warn("Transation phase with ID {} was not found in the DB", entity.getId());
            throw new EntityNotFoundException(
                    "Aucune phase de transaction avec l ID " + entity.getId() + " n a ete trouvee dans la BDD", ErrorCodes.TRANSACTIONPHASE_NOT_FOUND
            );
        }
    }


    /**
     * Check if user exist in DB
     * use in user
     *
     * @param entity : UsersEntity
     */

    public void checkUserByUsername(UsersEntity entity) {
        if (entity == null) {
            log.error("User Entity is null");
            throw new EntityNotFoundException(
                    "Aucun utilisateur n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        UsersEntity usersEntity;
        try {
            usersEntity = usersDao.findByUsername(em, entity.getUsername());
        } finally {
            em.clear();
            em.close();
        }
        if (usersEntity != null) {
            log.warn("User exists yet" + entity.getUsername());
            throw new InvalidEntityException(
                    "Il y a déjà un utilisateur avec ce pseudo: " + entity.getUsername(), ErrorCodes.USER_NOT_FOUND
            );
        }
    }

    /**
     * Check if user exist in DB
     * use in user
     *
     * @param entity : UsersEntity
     */

    public void checkUserActiveForConnection(UsersEntity entity) {
        if (entity == null) {
            log.error("User Entity is null");
            throw new EntityNotFoundException(
                    "Aucun utilisateur n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        UsersEntity usersEntity;
        try {
            usersEntity = usersDao.findActiveUserForConnection(em, entity.getUsername());
        } finally {
            em.clear();
            em.close();
        }

        if (usersEntity != null) {
            log.warn("User inactive" + entity.getUsername());
            throw new InvalidEntityException(
                    "L'utilisateur est inactif: " + entity.getUsername(), ErrorCodes.USER_NOT_FOUND
            );
        }
    }


    /**
     * @param username String
     * @param number   int
     * @return String
     */
    public String checkUserByUsernameAuto(String username, int number) {
        if (!username.isEmpty()) {
            UsersEntity usersEntity1;
            EntityManager em = EMF.getEM();
            log.info(username);
            String username1 = username + number;
            log.info(username1);
            if (number < 10) {
                username1 = username + "0" + number;
                usersEntity1 = usersDao.findByUsername(em, username1);

            } else {
                usersEntity1 = usersDao.findByUsername(em, username + number);
            }

            if (usersEntity1 != null) {
                while (usersEntity1 != null) {

                    Random random = new Random();
                    number = random.nextInt(99 - 1);
                    log.info(String.valueOf(number));
                    if (number < 10) {
                        int number1 = number;
                        username1 = username + "0" + number1;

                        usersEntity1 = usersDao.findByUsername(em, username1);
                    } else {
                        username1 = username + number;
                        usersEntity1 = usersDao.findByUsername(em, username1);
                        log.info("je suis dans le else");
                    }
                }
            }

            em.clear();
            em.close();
            log.info(username1);
            return username1;
        } else {
            throw new InvalidEntityException(
                    "L'utilisateur est vide: ", ErrorCodes.USER_NOT_FOUND
            );
        }
    }


    /**
     * Check if user exist in DB
     * use in user
     *
     * @param entity : UsersEntity
     */

    public void checkRole(RolesEntity entity) {
        if (entity == null) {
            log.error("Role Entity is null");
            throw new EntityNotFoundException(
                    "Aucun role n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        RolesEntity rolesEntity;
        try {
            rolesEntity = rolesDao.findById(em, entity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (rolesEntity == null) {
            log.warn("Roles doesn't exists yet" + entity.getId());
            throw new EntityNotFoundException(
                    "Ce rôle n'existe pas: " + entity.getId(), ErrorCodes.ROLES_NOT_FOUND
            );
        }

        em = EMF.getEM();
        try {
            rolesEntity = rolesDao.findActiveById(em, entity);
        } finally {
            em.clear();
            em.close();
        }
        if (rolesEntity == null) {
            log.warn("Roles doesn't exists yet" + entity.getId());
            throw new EntityNotFoundException(
                    "Ce rôle est inactif: " + entity.getId(), ErrorCodes.ROLES_NOT_FOUND
            );
        }

    }

    /**
     * check if role exist in DB
     * use in role
     *
     * @param entity RolesEntity
     */
    public void checkRoleByLabel(RolesEntity entity) {
        if (entity == null) {
            log.error("Role Entity is null");
            throw new EntityNotFoundException(
                    "Aucun role n a ete trouve", ErrorCodes.ROLES_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        RolesEntity rolesEntity;
        try {
            rolesEntity = rolesDao.findByLabel(em, entity.getLabel());
        } finally {
            em.clear();
            em.close();
        }

        if (rolesEntity != null) {
            log.warn("Role label exists yet" + entity.getLabel());
            throw new InvalidEntityException(
                    "Il y a déjà un role avec ce nom: " + entity.getLabel(), ErrorCodes.ROLES_FOUND
            );
        }
    }

    /**
     * @param entity RolesEntity
     */
    public void checkForSafeDeleteRole(RolesEntity entity) {
        if (entity == null) {
            log.error("Role Entity is null");
            throw new EntityNotFoundException(
                    "Aucun role n a ete trouve", ErrorCodes.ROLES_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        PermissionsEntity permissionsEntity;
        try {
            RolesEntity rolesEntity = (RolesEntity) rolesDao.findForDeleteSafe(em, entity.getId());
            log.info(String.valueOf(rolesEntity));
            permissionsEntity = permissionsDao.findById(em, entity.getId());
        } finally {
            em.clear();
            em.close();
        }
        if (permissionsEntity == null) {
            log.warn("permission doesn't exists yet" + entity.getId());
            throw new EntityNotFoundException(
                    "Cette permission n'existe pas: " + entity.getId(), ErrorCodes.PERMISSION_NOT_FOUND
            );
        }
    }


    /**
     * @param entity UsersEntity
     */
    public void checkRoleForConnection(UsersEntity entity) {
        if (entity == null) {
            log.error("User Entity is null");
            throw new EntityNotFoundException(
                    "Aucun utilisateur n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        RolesEntity entityActive;
        try {
            entityActive = rolesDao.findRoleForConnection(em, entity.getRolesByIdRoles().getLabel());
        } finally {
            em.clear();
            em.close();
        }
        if (entityActive == null) {
            log.warn("role is inactive" + entity.getId());
            throw new EntityNotFoundException(
                    "Le role est inactif " + entity.getId(), ErrorCodes.ROLES_NOT_VALID
            );
        }
    }

    /**
     * @param entity UsersEntity
     */
    public void checkRoleByLabel(UsersEntity entity) {
        if (entity == null) {
            log.error("role is null");
            throw new EntityNotFoundException(
                    "Aucun utilisateur n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }
        EntityManager em = EMF.getEM();
        RolesEntity entityActive;
        try {
            entityActive = rolesDao.findByLabel(em, entity.getRolesByIdRoles().getLabel());
        } finally {
            em.clear();
            em.close();
        }
        if (entityActive == null) {
            log.warn("role isn't exist" + entity.getId());
            throw new EntityNotFoundException(
                    "Le role est inexistant " + entity.getId(), ErrorCodes.ROLES_NOT_VALID
            );
        }
    }

}

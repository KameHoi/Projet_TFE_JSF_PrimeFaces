package be.atc.salesmanagercrm.validators;


import be.atc.salesmanagercrm.entities.RolesPermissionsEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Larché Marie-Élise
 */
@Slf4j
public class RolesPermissionsValidator {

    public static List<String> validate(RolesPermissionsEntity entity) {
        List<String> errors = new ArrayList<>();

        log.info(String.valueOf(entity));

        if (entity == null) {
            errors.add("La reception des données à échouée");
            errors.add("Veuillez recommencer le combo role-permissions");
            return errors;
        }


        return errors;
    }

}

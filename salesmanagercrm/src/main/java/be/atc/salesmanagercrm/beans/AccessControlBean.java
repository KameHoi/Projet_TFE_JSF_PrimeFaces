package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.exceptions.AccessDeniedException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Younes Arifi
 */
@Slf4j
@Named(value = "accessControlBean")
@ViewScoped
public class AccessControlBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -3081158727046882548L;

    private final FacesContext fc = FacesContext.getCurrentInstance();
    private final ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

    /**
     * Check if user isn't Logged
     */
    public void isNotLogged() {
        log.info("AccessControlBean => method : isNotLogged()");
        nav.performNavigation("/connection.xhtml?faces-redirect=true");
    }

    /**
     * Check user access
     *
     * @param value     String
     */
    public void checkPermission(String value) {
        log.info("AccessControlBean => method : checkPermission(String value)");

        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isPermitted(value)) {
            log.error("Access denied {}", value);
            throw new AccessDeniedException("L utilisateur n a pas les droits d acces necessaire", ErrorCodes.USER_ACCESS_DENIED);
        }
    }

    /**
     * Redirect user to AccessDenied !
     */
    public void hasNotPermission() {
        log.info("AccessControlBean => method : hasNotPermission()");
        nav.performNavigation("/errorPages/accessDenied.xhtml?faces-redirect=true");
    }
}

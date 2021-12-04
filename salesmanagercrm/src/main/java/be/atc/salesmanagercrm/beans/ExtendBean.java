package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.Map;

/**
 * @author Younes Arifi
 */
@Slf4j
public abstract class ExtendBean {

    @Getter
    @Setter
    private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    /**
     * Méthode pour retourner les paramètres récupéré
     *
     * @param name String
     * @return String
     */
    public String getParam(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(name);
    }

    /**
     * Méthode pour afficher les messages dans les pages
     *
     * @param severity      FacesMessage.Severity
     * @param message       String
     */
    public void getFacesMessage(FacesMessage.Severity severity, String message) {
        FacesMessage msg;
        msg = new FacesMessage(severity, JsfUtils.returnMessage(getLocale(), message), null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}

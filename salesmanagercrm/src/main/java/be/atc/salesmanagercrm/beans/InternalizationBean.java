package be.atc.salesmanagercrm.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@SessionScoped
public class InternalizationBean implements Serializable {


    private static final long serialVersionUID = -8171657472426005417L;
    private Locale locale;
    private String language;

    /**
     * Method PostContruct : Called first
     * Gets current Faces context instance Locale value
     */
    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }


    /**
     * This method sets the current locale in language User
     */
    public void changeLanguage() {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }


}

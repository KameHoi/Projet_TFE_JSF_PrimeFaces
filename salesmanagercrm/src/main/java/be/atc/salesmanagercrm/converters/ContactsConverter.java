package be.atc.salesmanagercrm.converters;

import be.atc.salesmanagercrm.beans.ContactsBean;
import be.atc.salesmanagercrm.beans.UsersBean;
import be.atc.salesmanagercrm.entities.ContactsEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Locale;

/**
 * @author Arifi Younes
 */
@RequestScoped
@Slf4j
@Named
public class ContactsConverter implements Converter {
    @Inject
    private ContactsBean contactsBean;
    @Inject
    private UsersBean usersBean;

    @Getter
    private final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * get Object
     *
     * @param context   FacesContext
     * @param component UIComponent
     * @param value     String
     * @return Object
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        ContactsEntity contactsEntity;
        log.info("Value = " + value);

        int id;
        if (value == null) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contactNotExist"), null));
        }
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contactNotExist"), null));
        }


        if (id != 0) {
            try {
                contactsEntity = contactsBean.findByIdContactAndByIdUser(id, usersBean.getUsersEntity());
                return contactsEntity;
            } catch (EntityNotFoundException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contactNotExist"), null));
            }
        } else {
            log.warn("Erreur Converter Contact");
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contactNotExist"), null));
        }
    }

    /**
     * Get String
     *
     * @param context   FacesContext
     * @param component UIComponent
     * @param value     Object
     * @return String
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((ContactsEntity) value).getId());
        } else {
            return null;
        }
    }
}

package be.atc.salesmanagercrm.converters;

import be.atc.salesmanagercrm.beans.ContactTypesBean;
import be.atc.salesmanagercrm.entities.ContactTypesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.Locale;

/**
 * @author Zabbara - Maximilien
 */
@Slf4j
@FacesConverter("contactTypesConverter")
public class ContactTypesConverter implements Converter {

    private final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private final ContactTypesBean contactTypesBean = new ContactTypesBean();

    /**
     * Get Object
     *
     * @param facesContext FacesContext
     * @param uiComponent  UIComponent
     * @param value        String
     * @return Object
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        log.info("Value = " + value);

        ContactTypesEntity contactTypesEntity;

        int id;
        if (value == null) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contactTypes.NotExist"), null));
        }
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contactTypes.NotExist"), null));
        }

        if (id != 0) {
            try {
                contactTypesEntity = contactTypesBean.findById(id);
                return contactTypesEntity;
            } catch (EntityNotFoundException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contactTypes.NotExist"), null));
            }
        } else {
            log.warn("Erreur Converter ContactTypes");
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "contactTypes.NotExist"), null));
        }
    }

    /**
     * Get String
     *
     * @param facesContext FacesContext
     * @param uiComponent  UIComponent
     * @param value        Object
     * @return String
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value != null) {
            return String.valueOf(((ContactTypesEntity) value).getId());
        } else {
            return null;
        }
    }
}

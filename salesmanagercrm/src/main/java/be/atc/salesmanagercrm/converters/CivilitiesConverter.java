package be.atc.salesmanagercrm.converters;

import be.atc.salesmanagercrm.beans.CivilitiesBean;
import be.atc.salesmanagercrm.entities.CivilitiesEntity;
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
 * @author Maximilien Zabbara
 */
@Slf4j
@FacesConverter(value = "civilitiesConverter")
public class CivilitiesConverter implements Converter {

    private final CivilitiesBean civilitiesBean = new CivilitiesBean();

    private final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * get object
     *
     * @param facesContext FacesContext
     * @param uiComponent  UIComponent
     * @param value        String
     * @return Object
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        CivilitiesEntity civilitiesEntity;
        log.info("Value = " + value);

        int id;

        if (value == null) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "civilitiesNotExist"), null));
        }

        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "civilitiesNotExist"), null));
        }

        if (id != 0) {
            try {
                civilitiesEntity = civilitiesBean.returnFindById(id);
                return civilitiesEntity;
            } catch (EntityNotFoundException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "civilitiesNotExist"), null));
            }
        } else {
            log.warn("Erreur Converter Civility");
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "civilitiesNotExist"), null));
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
            return String.valueOf(((CivilitiesEntity) value).getId());
        } else {
            return null;
        }
    }
}

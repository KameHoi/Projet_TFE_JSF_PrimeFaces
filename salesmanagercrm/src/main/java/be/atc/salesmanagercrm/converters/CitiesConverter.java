package be.atc.salesmanagercrm.converters;

import be.atc.salesmanagercrm.beans.CitiesBean;
import be.atc.salesmanagercrm.entities.CitiesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.Getter;
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
@FacesConverter(value = "citiesConverter")
public class CitiesConverter implements Converter {

    private final CitiesBean citiesBean = new CitiesBean();

    @Getter
    private final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

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

        CitiesEntity citiesEntity;
        log.info("Value = " + value);

        int id;

        if (value == null) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "CitiesNotExist"), null));
        }
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "CitiesNotExist"), null));
        }

        if (id != 0) {
            try {
                citiesEntity = citiesBean.findById(id);
                return citiesEntity;
            } catch (EntityNotFoundException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "CitiesNotExist"), null));
            }
        } else {
            log.warn("Erreur Converter Cities");
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "CitiesNotExist"), null));
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
            return String.valueOf(((CitiesEntity) value).getId());
        } else {
            return null;
        }
    }
}

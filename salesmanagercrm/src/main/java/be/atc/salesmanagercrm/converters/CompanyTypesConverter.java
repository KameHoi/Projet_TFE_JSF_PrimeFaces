package be.atc.salesmanagercrm.converters;

import be.atc.salesmanagercrm.dao.CompanyTypesDao;
import be.atc.salesmanagercrm.dao.impl.CompanyTypesDaoImpl;
import be.atc.salesmanagercrm.entities.CompanyTypesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import java.util.Locale;

/**
 * @author Zabbara - Maximilien
 */
@Slf4j
@FacesConverter("companyTypesConverter")
public class CompanyTypesConverter implements Converter {

    private final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    CompanyTypesDao companyTypesDao = new CompanyTypesDaoImpl();

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

        CompanyTypesEntity companyTypesEntity;
        EntityManager em = EMF.getEM();

        int id;
        if (value == null) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "companyTypes.NotExist"), null));
        }
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "companyTypes.NotExist"), null));
        }

        if (id != 0) {
            try {
                companyTypesEntity = companyTypesDao.findById(em, id);
                return companyTypesEntity;
            } catch (EntityNotFoundException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "companyTypes.NotExist"), null));
            }
        } else {
            log.warn("Erreur Converter company types");
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "companyTypes.NotExist"), null));
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
            return String.valueOf(((CompanyTypesEntity) value).getId());
        } else {
            return null;
        }
    }
}

package be.atc.salesmanagercrm.converters;

import be.atc.salesmanagercrm.beans.TransactionTypesBean;
import be.atc.salesmanagercrm.entities.TransactionTypesEntity;
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
 * Transaction Types Converter
 *
 * @author Younes Arifi
 */
@Slf4j
@FacesConverter("transactionTypesConverter")
public class TransactionTypesConverter implements Converter {

    private final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    private final TransactionTypesBean transactionTypesBean = new TransactionTypesBean();

    /**
     * Get Object
     * @param context FacesContext
     * @param component UIComponent
     * @param value String
     * @return Object
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        TransactionTypesEntity transactionTypesEntity;

        log.info("value:  " + value);

        if (value != null) {
            try {
                transactionTypesEntity = transactionTypesBean.findByLabel(value);
                return transactionTypesEntity;
            } catch (EntityNotFoundException exception) {
                log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "transactionTypeNotExist"), null));
            }
        } else {
            log.warn("Erreur Converter Transaction Type");
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "transactionTypeNotExist"), null));
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
        return null;
    }
}

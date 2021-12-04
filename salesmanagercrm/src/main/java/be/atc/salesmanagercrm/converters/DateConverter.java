package be.atc.salesmanagercrm.converters;

import be.atc.salesmanagercrm.utils.JsfUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;
/**
 * @author Zabbara - Maximilien
 */
@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter {

    private static final DateTimeFormatter DATE_FORMAT =
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy[ [HH][:mm][:ss][.SSS]]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 12)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();
    private final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * Get Object
     *
     * @param context   FacesContext
     * @param component UIComponent
     * @param value     String
     * @return Object
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null) {
            return LocalDateTime.parse(value, DATE_FORMAT);
        } else {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(locale, "localDateTimeConvertor.error"), null));
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

        LocalDateTime dateValue = (LocalDateTime) value;

        return dateValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}

package be.atc.salesmanagercrm.validators;

import be.atc.salesmanagercrm.enums.EnumPriority;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Younes Arifi
 */
@Slf4j
@FacesValidator("enumPriorityValidator")
public class EnumPriorityValidator implements Validator {


    @Getter
    private final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.info("Value = " + value);
        if (value != null) {
            if (
                    !Arrays.stream(EnumPriority.values())
                            .map(EnumPriority::name)
                            .collect(Collectors.toSet())
                            .contains(value.toString())
            ) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageError(), null));
            }
        }
    }

    /**
     * Return message for label already in DB
     *
     * @return jsf utils message
     */
    private String getMessageError() {
        return JsfUtils.returnMessage(locale, "task.validator.priority");
    }
}

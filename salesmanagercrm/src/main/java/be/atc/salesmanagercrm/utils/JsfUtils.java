package be.atc.salesmanagercrm.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class for internalization in class and bean...
 */
public final class JsfUtils {

    private JsfUtils() {
    }

    /**
     * This method return messages translated
     *
     * @param locale  Locale
     * @param message String
     * @return String
     */
    public static String returnMessage(Locale locale, String message) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(
                "i18n.messages", locale);
        return resourceBundle.getString(message);
    }
}

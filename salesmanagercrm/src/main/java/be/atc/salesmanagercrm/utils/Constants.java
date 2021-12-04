package be.atc.salesmanagercrm.utils;

import javax.faces.context.FacesContext;

/**
 * @author Younes Arifi
 * Constant values used
 */
public interface Constants {

    String PERSISTENCE_UNIT_NAME = "salesmanagercrm";

    String SMTPHOST = "smtp.gmail.com";
    String SMTPPORT = "465";
    String SMTPSOCKETFACTORYCLASS = "javax.net.ssl.SSLSocketFactory";
    String SMTPSOCKETFACTORYPORT = "465";
    String MYACCOUNTEMAIL = "locacarbacinfo@gmail.com";
    String MYPASSWORDEMAIL = "bacInfo123";

    String UPLOADS = "/uploads/";
    String GENERATED_FILES = "/generated_files/";
    String IMAGES = "/images/";


    String APP_ROOT_DIR = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/");
    String UPLOADS_ROOT_DIR = APP_ROOT_DIR + UPLOADS;
    String FILE_OUT_PUT_STREAM = UPLOADS_ROOT_DIR + GENERATED_FILES;
    String FILE_OUTPUT_IMAGE = UPLOADS_ROOT_DIR + IMAGES;
}

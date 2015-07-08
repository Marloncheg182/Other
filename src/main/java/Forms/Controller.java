package Forms;

import Utils.Loggson;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @author Romanenchuk Oleg
 * Controller class
 */

@Loggson

public class Controller {

    @Inject
    private transient Logger logger;

    private String getMessage(FacesContext context, String mKey, Object... args) {
        Locale locale = context.getViewRoot().getLocale();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale, loader);
        String mValue = bundle.getString(mKey);
        return MessageFormat.format(mValue, args);
    }

    protected void addInformation(String message, Object... args) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage(context, message, args), null));
    }

    protected void addWarning(String message, Object... args) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage(context, message, args), null));

    }

    protected String getParameter(String parameter) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        return map.get(parameter);
    }

    protected Long getParameterId(String parameter) {
        return Long.valueOf(getParameter(parameter));
    }

}

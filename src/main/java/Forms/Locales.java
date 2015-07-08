package Forms;

import Utils.Loggson;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

/**
 * @author Romanenchuk Oleg
 * Localizations and internationalization
 */

@Named
@SessionScoped
@Loggson
public class Locales implements Serializable{

    @Produces
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale(){
        return locale;
    }

    public String getLanguage(){
        return locale.getLanguage();
    }

    public void setLanguage(String language){
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}

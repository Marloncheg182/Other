package Forms;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author Romanenchuk Oleg
 * Interceptor
 */

@Interceptor
@CatchException
public class ExceptionHandler implements Serializable {

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object catcher(InvocationContext cxt) throws Exception {
        try {
            return cxt.proceed();
        } catch (Exception e) {
            errorMessage(e.getMessage());
            logger.severe("/!\\" + cxt.getTarget().getClass().getName() + " - " + cxt.getMethod().getName() + " - " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }



    private void errorMessage(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));

    }
}

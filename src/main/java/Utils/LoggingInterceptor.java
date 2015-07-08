package Utils;

import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author Romanenchuk Oleg
 */

@Loggson
@Interceptor
public class LoggingInterceptor implements Serializable {

    @Inject
    private transient Logger logger;

    public Object loggingMethod(InvocationContext context)throws Exception{
        logger.entering(context.getTarget().getClass().getName(), context.getMethod().getName());
        logger.info(">>>" + context.getTarget().getClass().getName() + " - " + context.getMethod().getName());
        try {
            return context.proceed();
        } finally {
            logger.exiting(context.getTarget().getClass().getName(), context.getMethod().getName());
            logger.info("<<<" + context.getTarget().getClass().getName() + " - " + context.getMethod().getName());
        }
    }
}

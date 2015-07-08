package Utils;

import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * @author Romanenchuk Oleg
 */
public class LoggingProducer {

    public Logger produceLogger(InjectionPoint point){
        return Logger.getLogger(point.getMember().getDeclaringClass().getName());
    }
}

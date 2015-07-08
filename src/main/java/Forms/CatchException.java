package Forms;

/**
 * @author Romanenchuk Oleg
 */

import javax.interceptor.InterceptorBinding;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface CatchException {
}

package Utils;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

/**
 * @author Romanenchuk Oleg
 * Property annottation
 */
@Qualifier
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface ConfigProperty {
    @Nonbinding String value() default "";
}

package Utils;

import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Romanenchuk Oleg
 * Configuation producer
 */
public class PropertyProducer {

    private static Properties properties;
    // static block
    static {
        properties = new Properties();
        try {
            properties.load(PropertyProducer.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String produceConfigProperty(InjectionPoint point){
        String key = point.getAnnotated().getAnnotation(ConfigProperty.class).value();

        return properties.getProperty(key);
    }
}

package Utils;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Romanenchuk Oleg
 */
public class DBCreator {

    @Produces
    @PersistenceContext(unitName = "applicationInternetShop")
    private EntityManager manager;
}

package peyton.training.rap.demoSecond.DbUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The Class ConnectData.
 * 
 * @author Ace
 */
public class DBConnection {
    public static final String RESOURCE_LOCAL = "jpa-osgi-resourcelocal";
    /** The factory. */
    private static EntityManagerFactory factory = Persistence
	    .createEntityManagerFactory(RESOURCE_LOCAL);
    
    static {
        factory.getCache().evictAll();
    }
    /**
     * Gets the entity manager.
     * 
     * @return the entity manager
     */
    public static EntityManager getEntityManager() {
	return factory.createEntityManager();
    }
}

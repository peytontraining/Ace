package peyton.training.rap.demoSecond.DbUtils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import peyton.training.rap.demoSecond.Entites.Version;

/**
 * The Class TableDataModel.
 * 
 * @author Ace
 */
public class VersionDAO {

    EntityManager manager = DBConnection.getEntityManager();

    public List<Version> getAll() {
        manager.getEntityManagerFactory().getCache().evictAll();
        TypedQuery<Version> query = manager.createNamedQuery("Version.findAll",
                Version.class);
        List<Version> version = query.getResultList();
        manager.close();
        return version;
    }

    // Update Version
    public Version updateVersion(Version version) {
        manager.getEntityManagerFactory().getCache().evictAll();
        manager.getTransaction().begin();
        version = manager.merge(version);
        manager.getTransaction().commit();
        manager.close();
        return version;
    }

    // Add New Version
    public Version AddnewVersion(Version version) {
        manager.getEntityManagerFactory().getCache().evictAll();
        manager.getTransaction().begin();
        version = manager.merge(version);
        manager.getTransaction().commit();
        manager.close();
        return version;
    }

    // Delete Version
    public Version deleteVersion(Version version) {
        manager.getEntityManagerFactory().getCache().evictAll();
        manager.getTransaction().begin();
        manager.remove(manager.merge(version));
        manager.getTransaction().commit();
        manager.close();
        return version;
    }
}

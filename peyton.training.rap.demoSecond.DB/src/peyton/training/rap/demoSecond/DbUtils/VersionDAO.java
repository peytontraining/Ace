package peyton.training.rap.demoSecond.DbUtils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
        TypedQuery<Version> query = manager.createNamedQuery("Version.findAll",
                Version.class);
        List<Version> version = query.getResultList();
        manager.close();
        return version;
    }

    // Find ID Version
    public Version findById(String versionId) {
        Version version = manager.find(Version.class, versionId);
        if (version == null) {
            throw new EntityNotFoundException("Can't find Version for ID "
                    + versionId);
        }
        return version;
    }

    // Update Version
    public void updateVersion(Version version) {
        manager.getTransaction().begin();
        manager.merge(version);
        manager.getTransaction().commit();
        manager.close();
    }

    public void AddnewVersion(Version version) {
        manager.getTransaction().begin();
        manager.persist(version);
        manager.flush();
        manager.refresh(version);
        manager.getTransaction().commit();
    }

    // Delete Version by ID
    public void deleteVersionById(Long versionId) {
        Version version = manager.find(Version.class, versionId);
        if (versionId != null) {
            manager.getTransaction().begin();
            manager.remove(version);
            manager.getTransaction().commit();
        }
    }

    // Delete Version
    public void deleteVersion(Version version) {
        manager.getTransaction().begin();
        manager.remove(manager.merge(version));
        manager.getTransaction().commit();
    }
}

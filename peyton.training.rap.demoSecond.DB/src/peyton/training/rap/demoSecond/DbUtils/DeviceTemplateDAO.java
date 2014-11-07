package peyton.training.rap.demoSecond.DbUtils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import peyton.training.rap.demoSecond.Entites.TreeColumnParent;

public class DeviceTemplateDAO {
    EntityManager em = DBConnection.getEntityManager();

    public List<TreeColumnParent> getAllTreeColumn() {
        TypedQuery<TreeColumnParent> query = em
                .createNamedQuery("TreeColumnParent.findAll", TreeColumnParent.class);
        List<TreeColumnParent> treeColumnParent = query.getResultList();
        em.close();
        return treeColumnParent;
    }
}

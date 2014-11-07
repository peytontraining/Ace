/*
 * 
 */
package peyton.training.rap.demoSecond.DbUtils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import peyton.training.rap.demoSecond.Entites.Device;
import peyton.training.rap.demoSecond.Entites.Machine;
import peyton.training.rap.demoSecond.Entites.Type;

/**
 * The Class DemoTreeModel.
 * 
 * @author Ace
 */
public class TreeViewDAO {
	
	EntityManager em = DBConnection.getEntityManager();
	
        public List<Type> getAllType() {
            TypedQuery<Type> query = em
                    .createNamedQuery("Type.findAll", Type.class);
            List<Type> types = query.getResultList();
            em.close();
            return types;
        }
        
        public List<Device> getAllDevice() {
            TypedQuery<Device> query = 
                em.createNamedQuery("Device.findAll", Device.class);
            List<Device> devices = query.getResultList();
            em.close();
            return devices;
        }
        
        public void updateMachine(Machine machine) {
            em.getTransaction().begin();
            em.merge(machine);
            em.getTransaction().commit();
            em.close();
        }
}

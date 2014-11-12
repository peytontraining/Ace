/*
 * 
 */
package peyton.training.rap.demoSecond.DbUtils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import peyton.training.rap.demoSecond.Entites.Device;
import peyton.training.rap.demoSecond.Entites.Project;
import peyton.training.rap.demoSecond.Entites.Type;

/**
 * The Class DemoTreeModel.
 * 
 * @author Ace
 */
public class TreeViewDAO {
	
	EntityManager manager = DBConnection.getEntityManager();
	
        public List<Type> getAllType() {
            TypedQuery<Type> query = manager
                    .createNamedQuery("Type.findAll", Type.class);
            List<Type> types = query.getResultList();
            manager.close();
            return types;
        }
        
        public List<Device> getAllDevice() {
            TypedQuery<Device> query = 
                    manager.createNamedQuery("Device.findAll", Device.class);
            List<Device> devices = query.getResultList();
            manager.close();
            return devices;
        }
        
        public Project updateMachine(Project machine) {
            manager.getTransaction().begin();
            machine = manager.merge(machine);
            manager.getTransaction().commit();
            manager.close();
            return machine;
        }
}

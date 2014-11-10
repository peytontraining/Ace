package peyton.training.rap.demoSecond.DbUtils;

import javax.persistence.EntityManager;

import peyton.training.rap.demoSecond.Entites.Device;
import peyton.training.rap.demoSecond.Entites.DeviceTemplate;

public class DeviceModifyDAO {

    EntityManager manager = DBConnection.getEntityManager();

    public void updateDevice(Device device) {
        manager.getTransaction().begin();
        manager.merge(device);
        manager.getTransaction().commit();
        manager.close();
    }
    
    public void updateDeviceTemplate(DeviceTemplate deviceTemplate) {
        manager.getTransaction().begin();
        manager.merge(deviceTemplate);
        manager.getTransaction().commit();
        manager.close();
    }
}

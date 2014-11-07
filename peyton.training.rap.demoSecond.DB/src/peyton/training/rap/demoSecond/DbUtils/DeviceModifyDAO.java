package peyton.training.rap.demoSecond.DbUtils;

import javax.persistence.EntityManager;

import peyton.training.rap.demoSecond.Entites.Device;

public class DeviceModifyDAO {

    EntityManager manager = DBConnection.getEntityManager();

    public void updateDevice(Device device) {
        manager.getTransaction().begin();
        manager.merge(device);
        manager.getTransaction().commit();
        manager.close();
    }
}

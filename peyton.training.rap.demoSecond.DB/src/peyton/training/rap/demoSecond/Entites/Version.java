package peyton.training.rap.demoSecond.Entites;

import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the versions database table.
 * 
 * @author Ace
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "versions")
@NamedQuery(name = "Version.findAll", query = "SELECT v FROM Version v")
public class Version {  
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String deploySource;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deployTime;
    
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    private String targetVersion;

    @OneToMany(mappedBy="version",cascade={CascadeType.PERSIST})
    private List<Device> devices;
    
    @ManyToOne(cascade = {CascadeType.PERSIST} )
    @JoinColumn(name="idMachine")
    private Machine machine;
    
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
    public Version() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeploySource() {
        return this.deploySource;
    }

    public void setDeploySource(String deploySource) {
        this.deploySource = deploySource;
    }

    public Date getDeployTime() {
        return this.deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSaveTime() {
        return this.saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public String getTargetVersion() {
        return this.targetVersion;
    }

    public void setTargetVersion(String targetVersion) {
        this.targetVersion = targetVersion;
    }

    public List<Device> getDevices() {
        return this.devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public Device addDevice(Device device) {
        getDevices().add(device);
        device.setVersion(this);

        return device;
    }

    public Device removeDevice(Device device) {
        getDevices().remove(device);
        device.setVersion(null);

        return device;
    }
}
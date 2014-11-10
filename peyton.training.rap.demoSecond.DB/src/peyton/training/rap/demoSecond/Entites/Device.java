package peyton.training.rap.demoSecond.Entites;

import java.sql.Date;
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

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the devices database table.
 * 
 * @author Ace
 * 
 */
@Entity
@Table(name = "devices")
@Cacheable(true)
@NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
public class Device {

    /** The idversion. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** The app module. */
    private String appModule;

    /** The device type. */
    private String deviceType;

    /** The manufacturer. */
    private String manufacture;

    /** The name. */
    private String name;

    /** The physical location. */
    private String physicalLocation;
    
    private String rooms;
    
    private boolean status;
    
    private String controlType;
    
    private String versionContent;
    
    private String modelNumber;
    
    private String notes;
    
    private Date lastModifield;
    
    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getLastModifield() {
        return lastModifield;
    }

    public void setLastModifield(Date lastModifield) {
        this.lastModifield = lastModifield;
    }
    
    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }
    public String getVersionContent() {
        return versionContent;
    }

    public void setVersionContent(String versionContent) {
        this.versionContent = versionContent;
    }
    
    @OneToMany(mappedBy="device",cascade={CascadeType.PERSIST})
    private List<DeviceTableDetail> deviceTableDetail;
    
    /** The version bean. */
    @ManyToOne(cascade = {CascadeType.PERSIST} )
    @JoinColumn(name = "idVersion")
    private Version version;

    public List<DeviceTableDetail> getDeviceTableDetail() {
        return deviceTableDetail;
    }

    public void setDeviceTableDetail(List<DeviceTableDetail> deviceTableDetail) {
        this.deviceTableDetail = deviceTableDetail;
    }

    /**
     * Instantiates a new device.
     */
    public Device() {
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the app module.
     * 
     * @return the app module
     */
    public String getAppModule() {
        return this.appModule;
    }

    /**
     * Sets the app module.
     * 
     * @param appModule the new app module
     */
    public void setAppModule(String appModule) {
        this.appModule = appModule;
    }

    /**
     * Gets the device type.
     * 
     * @return the device type
     */
    public String getDeviceType() {
        return this.deviceType;
    }

    /**
     * Sets the device type.
     * 
     * @param deviceType the new device type
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    
    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     * 
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the physical location.
     * 
     * @return the physical location
     */
    public String getPhysicalLocation() {
        return this.physicalLocation;
    }

    /**
     * Sets the physical location.
     * 
     * @param physicalLocation the new physical location
     */
    public void setPhysicalLocation(String physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    /**
     * Gets the version.
     * 
     * @return the version
     */
    public Version getVersion() {
        return version;
    }

    /**
     * Sets the version.
     * 
     * @param version the new version
     */
    public void setVersion(Version version) {
        this.version = version;
    }
    
    public DeviceTableDetail addDeviceTableDetail(DeviceTableDetail deviceTableDetail) {
        getDeviceTableDetail().add(deviceTableDetail);
        deviceTableDetail.setDevice(this);
        
        return deviceTableDetail;
    }

    public DeviceTableDetail removeDeviceTableDetail(DeviceTableDetail deviceTableDetail) {
        getDeviceTableDetail().remove(deviceTableDetail);
        deviceTableDetail.setDevice(null);
        
        return deviceTableDetail;
    }
}
package peyton.training.rap.demoSecond.Entites;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the deviceTemplates database table.
 * 
 */
@Entity
@Table(name = "deviceTemplates")
@NamedQuery(name = "DeviceTemplate.findAll", query = "SELECT d FROM DeviceTemplate d")
public class DeviceTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Timestamp lastModifield;

    private String manufacturer;

    private String modelNumber;

    private String name;

    private String version;

    private String deviceDriver;

    private String note;

    public String getDeviceDriver() {
        return deviceDriver;
    }

    public void setDeviceDriver(String deviceDriver) {
        this.deviceDriver = deviceDriver;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // bi-directional many-to-one association to TypeDeviceTemplate
    @ManyToOne
    @JoinColumn(name = "idTypeDevice")
    private TypeDeviceTemplate typeDeviceTemplate;

    public DeviceTemplate() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getLastModifield() {
        return this.lastModifield;
    }

    public void setLastModifield(Timestamp lastModifield) {
        this.lastModifield = lastModifield;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelNumber() {
        return this.modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public TypeDeviceTemplate getTypeDeviceTemplate() {
        return this.typeDeviceTemplate;
    }

    public void setTypeDeviceTemplate(TypeDeviceTemplate typeDeviceTemplate) {
        this.typeDeviceTemplate = typeDeviceTemplate;
    }

}
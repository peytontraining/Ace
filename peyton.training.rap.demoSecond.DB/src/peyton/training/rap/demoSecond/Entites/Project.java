package peyton.training.rap.demoSecond.Entites;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * The persistent class for the equipment database table.
 * 
 * @author Ace
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "Project")
@NamedQuery(name = "Project.findAll", query = "SELECT m FROM Project m")
public class Project {

    /** The id machine. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** The name project. */
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name = "idType")
    private Type type;

    @OneToMany(mappedBy = "project", cascade={CascadeType.PERSIST})
    @OrderBy(value = "id DESC")
    private List<Version> versions;

    /**
     * Instantiates a new machine.
     */
    public Project() {
    }

    /**
     * Gets the type.
     * 
     * @return the type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Sets the type.
     * 
     * @param type the new type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets the versions.
     * 
     * @return the versions
     */
    public List<Version> getVersions() {
        return this.versions;
    }

    /**
     * Sets the versions.
     * 
     * @param versions the new versions
     */
    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    /**
     * Adds the version.
     * 
     * @param version the version
     * @return the version
     */
    public Version addVersion(Version version) {
        getVersions().add(version);
        version.setProject(this);
        return version;
    }

    /**
     * Removes the version.
     * 
     * @param version the version
     * @return the version
     */
    public Version removeVersion(Version version) {
        getVersions().remove(version);
        version.setProject(null);
        return version;
    }

}
package peyton.training.rap.demoSecond.Entites;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the typeDeviceTemplate database table.
 * 
 */
@Entity
@Cacheable(true)
@Table(name="typeDeviceTemplate")
@NamedQuery(name="TypeDeviceTemplate.findAll", query="SELECT t FROM TypeDeviceTemplate t")
public class TypeDeviceTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to DeviceTemplate
	@OneToMany(mappedBy="typeDeviceTemplate")
	private List<DeviceTemplate> deviceTemplates;

	//bi-directional many-to-one association to TreeColumnParent
	@ManyToOne
	@JoinColumn(name="idTreeColumnParent")
	private TreeColumnParent treeColumnParent;

	public TypeDeviceTemplate() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DeviceTemplate> getDeviceTemplates() {
		return this.deviceTemplates;
	}

	public void setDeviceTemplates(List<DeviceTemplate> deviceTemplates) {
		this.deviceTemplates = deviceTemplates;
	}

	public DeviceTemplate addDeviceTemplate(DeviceTemplate deviceTemplate) {
		getDeviceTemplates().add(deviceTemplate);
		deviceTemplate.setTypeDeviceTemplate(this);

		return deviceTemplate;
	}

	public DeviceTemplate removeDeviceTemplate(DeviceTemplate deviceTemplate) {
		getDeviceTemplates().remove(deviceTemplate);
		deviceTemplate.setTypeDeviceTemplate(null);

		return deviceTemplate;
	}

	public TreeColumnParent getTreeColumnParent() {
		return this.treeColumnParent;
	}

	public void setTreeColumnParent(TreeColumnParent treeColumnParent) {
		this.treeColumnParent = treeColumnParent;
	}

}
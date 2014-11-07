package peyton.training.rap.demoSecond.Entites;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the treeColumnParent database table.
 * 
 */
@Entity
@Table(name="treeColumnParent")
@NamedQuery(name="TreeColumnParent.findAll", query="SELECT t FROM TreeColumnParent t")
public class TreeColumnParent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;
	
	//bi-directional many-to-one association to TypeDeviceTemplate
	@OneToMany(mappedBy="treeColumnParent")
	private List<TypeDeviceTemplate> typeDeviceTemplates;

	public TreeColumnParent() {
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

	public List<TypeDeviceTemplate> getTypeDeviceTemplates() {
		return this.typeDeviceTemplates;
	}

	public void setTypeDeviceTemplates(List<TypeDeviceTemplate> typeDeviceTemplates) {
		this.typeDeviceTemplates = typeDeviceTemplates;
	}

	public TypeDeviceTemplate addTypeDeviceTemplate(TypeDeviceTemplate typeDeviceTemplate) {
		getTypeDeviceTemplates().add(typeDeviceTemplate);
		typeDeviceTemplate.setTreeColumnParent(this);

		return typeDeviceTemplate;
	}

	public TypeDeviceTemplate removeTypeDeviceTemplate(TypeDeviceTemplate typeDeviceTemplate) {
		getTypeDeviceTemplates().remove(typeDeviceTemplate);
		typeDeviceTemplate.setTreeColumnParent(null);

		return typeDeviceTemplate;
	}

}
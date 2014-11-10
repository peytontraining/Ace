package peyton.training.rap.demoSecond.Entites;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * The persistent class for the type database table.
 * 
 * @author Ace
 * 
 */
@Entity
@Cacheable(true)
@Table(name="types")
@NamedQuery(name="Type.findAll", query= "SELECT t FROM Type t")
public class Type{
	
	/** The idtype. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** The typename. */
	private String name;

	/** The machines. */
	@OneToMany(mappedBy="type",cascade={CascadeType.PERSIST})
	@OrderBy(value = "id DESC")
	private List<Machine> machines;

	/**
	 * Instantiates a new type.
	 */
	public Type() {
	}
    
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

    /**
	 * Gets the machines.
	 *
	 * @return the machines
	 */
	public List<Machine> getMachines() {
		return this.machines;
	}

	/**
	 * Sets the machines.
	 *
	 * @param machines the new machines
	 */
	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}

	/**
	 * Adds the machine.
	 *
	 * @param machines the machines
	 * @return the machine
	 */
	public Machine addMachine(Machine machines) {
		getMachines().add(machines);
		machines.setType(this);

		return machines;
	}

	/**
	 * Removes the machine.
	 *
	 * @param machines the machines
	 * @return the machine
	 */
	public Machine removeMachine(Machine machines) {
		getMachines().remove(machines);
		machines.setType(null);

		return machines;
	}
}
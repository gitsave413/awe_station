package cg.beylhond.soft.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe representant une categorie de vehicule
 * 
 * @author Delphin BONDONGO
 * @since mer.30.mars 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"libelle"}))
public class Categorie extends ClasseMere
{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min=3)
	private String libelle;
	
	@OneToMany(mappedBy="categorie", fetch=FetchType.EAGER)
	private List<Vehicule> vehicules = new ArrayList<Vehicule>();
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public List<Vehicule> getVehicules() {
		return vehicules;
	}

	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}
}

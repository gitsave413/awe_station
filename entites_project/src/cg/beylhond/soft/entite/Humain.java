package cg.beylhond.soft.entite;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe representant un etre humain (travailleur ou agent)
 * 
 * @author Delphin BONDONGO
 * @since 15.8.mer.2016 22:00
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="fonction")
@Entity
public class Humain extends ClasseMere
{
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min=3)
	private String nom;
	
	@NotNull
	@Size(min=3)
	private String prenom;
	
	@NotNull
	@Size(min=8)
	private String telephone;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}
}

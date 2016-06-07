package cg.beylhond.soft.entite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe representant un service preste par A.W.E STATION SERVICE
 * 
 * @author Delphin BONDONGO
 * @since mer.30.mars 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"libelle"}))
public class Service extends ClasseMere
{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min=3, max=20)
	private String libelle;
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	@Override
	public String toString() {
		return "Service [libelle=" + libelle + "]";
	}
}

package cg.beylhond.soft.entite;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe representant un article (vehicule, comme objet).
 * après modification avec zobath
 * 
 * @author Delphin BONDONGO
 * @since mer.30.mars 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING, length=1,name="type")
public class Article extends ClasseMere
{ 
	private static final long serialVersionUID = 1L;
	
	//le libelle est compris comme l'immatriculation du vehicule
	
	@NotNull
	@Size(min=3, max=20)
	private String libelle;
	
	public String getLibelle() 
	{
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}

package cg.beylhond.soft.entite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


/**
 * Classe representant un vehicule
 *  * 
 * @author Delphin BONDONGO
 * @since jeu.28.avril 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
@DiscriminatorValue(value="V")
public class Vehicule extends Article 
{
	@ManyToOne
	@NotNull
	private Categorie categorie;
	
	private static final long serialVersionUID = 1L;
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
}

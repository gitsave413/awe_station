package cg.beylhond.soft.entite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
/**
 * Classe derivee d'Article, representant un objet isolé tel que la moquette.
 * 
 * @author Delphin BONDONGO
 * @since mer.30.mars 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
@DiscriminatorValue(value="O")
public class Objet extends Article 
{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne
	private Etat etat;
	
	@NotNull
	@ManyToOne
	private Taille taille;
	 
	public Taille getTaille() {
		return taille;
	}
	public void setTaille(Taille taille) {
		this.taille = taille;
	}
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
}

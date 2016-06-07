package cg.beylhond.soft.entite;

import javax.persistence.MappedSuperclass;

/**
 * Classe principale pour toutes les entites qui utilisent l'attribut mois et montant pour faire des manipulations
 * 
 * @author Delphin BONDONGO
 * @since dim.29.mai.2015 03:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@MappedSuperclass
public class CalculBean extends ClasseMere
{
	private static final long serialVersionUID = 1L;
	
	private String mois;
	private int montant;
	
	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}
	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}
}

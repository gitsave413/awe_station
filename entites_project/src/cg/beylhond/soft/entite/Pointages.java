package cg.beylhond.soft.entite;

/**
 * Cette Structure de donnée a ete creee pour permet de pointer le salaire des travailleur de facon global quand le total du recu atteint 8000
 * 
 * @author Delphin BONDONGO
 * @since ven.27.mai 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

public class Pointages extends ClasseMere
{
	private static final long serialVersionUID = 1L;
	
	private Travailleur travailleur;
	private int montant;

	public Travailleur getTravailleur() {
		return travailleur;
	}
	public void setTravailleur(Travailleur travailleur) {
		this.travailleur = travailleur;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
}

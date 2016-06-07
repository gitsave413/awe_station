package cg.beylhond.soft.entite;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Classe representant un ligne du recu pour les vehicule de A.W.E STATION SERVICE
 * 
 * @author Delphin BONDONGO
 * @since mer.30.mars 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
public class LigneRecu extends ClasseMere 
{
	private static final long serialVersionUID = 1L;

	@NotNull	
	@ManyToOne
	private RecuVehicule recu;

	@NotNull
	@ManyToOne
	private Travailleur travailleur;

	@OneToOne
	@NotNull
	private CatService catService;

	//le revenu du travailleur après un service rendu
	private int pointage;

	//**************************************************************************

	public CatService getCatService() {
		return catService;
	}

	public void setCatService(CatService catService) {
		this.catService = catService;
	}

	public Travailleur getTravailleur() {
		return travailleur;
	}

	public void setTravailleur(Travailleur travailleur) {
		this.travailleur = travailleur;
	}

	public RecuVehicule getRecu() {
		return recu;
	}

	public void setRecu(RecuVehicule recu) {
		this.recu = recu;
	}
	@Override
	public String toString() {
		return "LigneRecu [recu=" + recu + ", travailleur=" + travailleur
				+ ", catService=" + catService + "]";
	}
	public int getPointage() {
		return pointage;
	}

	public void setPointage(int pointage) {
		this.pointage = pointage;
	}
}

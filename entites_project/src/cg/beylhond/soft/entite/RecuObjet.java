package cg.beylhond.soft.entite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Classe representant le recu dedie aux objets
 * 
 * @author Delphin BONDONGO
 * @since jeu.28.aril 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
@DiscriminatorValue(value="RO")
public class RecuObjet extends Recu 
{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@NotNull
	private Travailleur travailleur;
	
	@OneToOne
	@NotNull
	private Objet objet;
	
	@ManyToOne
	@NotNull
	private Service service;
	
	//le revenu du travailleur après un service rendu
	private int pointage;
	
	//getters --------------------------------------------------------------------------------------------
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Objet getObjet() {
		return objet;
	}

	public void setObjet(Objet objet) {
		this.objet = objet;
	}
	
	public Travailleur getTravailleur() {
		return travailleur;
	}

	public void setTravailleur(Travailleur travailleur) {
		this.travailleur = travailleur;
	}
	public int getPointage() {
		return pointage;
	}

	public void setPointage(int pointage) {
		this.pointage = pointage;
	}
}

package cg.beylhond.soft.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Classe representant le recu dedie aux vehicules
 * 
 * @author Delphin BONDONGO
 * @since jeu.28.aril 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
@DiscriminatorValue(value="RV")
public class RecuVehicule extends Recu 
{
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="recu", cascade=CascadeType.ALL)
	private List<LigneRecu> ligneRecu = new ArrayList<LigneRecu>();

	@NotNull
	@OneToOne
	private Vehicule vehicule;

	//******************************************************************************

	public List<LigneRecu> getLigneRecu() {
		return ligneRecu;
	}

	public RecuVehicule()
	{
		super();
	}

	public RecuVehicule(int id) 
	{
		this.id = id;
	}

	public void setLigneRecu(List<LigneRecu> ligneRecu) {
		this.ligneRecu = ligneRecu;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
}

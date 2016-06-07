package cg.beylhond.soft.entite;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Classe representant les prix de chaque service en fonction de la categorie du vehicule A.W.E STATION SERVICE
 * 
 * @author Delphin BONDONGO
 * @since mer.30.mars 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
public class CatService extends ClasseMere 
{
	private static final long serialVersionUID = 1L;
	
	private int prix;
	
	@OneToOne
	private Categorie categorie;
	
	@NotNull
	@OneToOne
	private Service service;
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	} 
}

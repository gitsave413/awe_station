package cg.beylhond.soft.entite;

import javax.persistence.Entity;

/**
 * Cette classe a pour but de combler la limite du model
 * 
 * elle enregistrera les libelle des article de type objet
 * 
 * pendant la création du recu l'objet sera choisit
 * 
 * @author Delphin BONDONGO
 * @since mer.30.mars 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */


@Entity
public class LibelleObjet extends ClasseMere
{
	private static final long serialVersionUID = 1L;
	
	private String libelle;
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}

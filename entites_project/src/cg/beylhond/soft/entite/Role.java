package cg.beylhond.soft.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * Classe representant le role d'un agent
 * 
 * fait avec l'aide de malanda (JAAS)
 * 
 * @author Delphin BONDONGO
 * @since mer.27.aril 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
public class Role extends ClasseMere 
{
	private static final long serialVersionUID = 5731807021113448203L;

	private String libelle;
	
	@OneToMany(mappedBy="role", fetch=FetchType.EAGER)
	private List<Agent> agents = new ArrayList<Agent>();
	
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}
}

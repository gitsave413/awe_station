package cg.beylhond.soft.entite;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe representant les depenses effectuees par les agents 
 * 
 * @author Delphin BONDONGO
 * @since ven.27.mai 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
public class Depense extends CalculBean
{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne
	private Agent agent;
	
	@NotNull
	@Size(min=3)
	private String motif;
	
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}

	@PrePersist
	public void verifierSeuil()
	{
		System.out.println("impossible d'effectuer la dépense");
	}
}

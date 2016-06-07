package cg.beylhond.soft.entite;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Classe representant le registre de salaire
 * 
 * @author Delphin BONDONGO
 * @since mer.14.mai 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
public class Salaire extends CalculBean
{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne
	private Humain humain;

	public Humain getHumain() {
		return humain;
	}

	public void setHumain(Humain humain) {
		this.humain = humain;
	}
}

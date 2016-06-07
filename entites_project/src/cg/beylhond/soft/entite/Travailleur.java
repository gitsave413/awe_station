package cg.beylhond.soft.entite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe representant un travailleur de A.W.E STATION SERVICE
 * 
 * @author Delphin BONDONGO
 * @since mer.30.mars 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
@DiscriminatorValue(value="travailleur")
public class Travailleur extends Humain
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() 
	{
		return ""+getPrenom() + " "+getNom();
	}
}

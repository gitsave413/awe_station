package cg.beylhond.soft.tools;

/**
 * Enumeration des modes de vue qui existe
 * 
 * classe abstraite factorisant les traitements et attributs communs aux differents controlleur de ce projet
 * 
 * @author Delphin BONDONGO
 * @since 11.mai.2016 15:44
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

public enum ModeVue 
{
	Modification(3), Ajout(2), Consultation(1);
	
	private int mode;
	
	
	ModeVue(int mode)
	{
		this.mode = mode;
	}
	
	public int getMode()
	{
		return mode;
	}

	public void setMode(int mode) 
	{
		this.mode = mode;
	}
}

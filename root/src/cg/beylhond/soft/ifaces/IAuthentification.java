package cg.beylhond.soft.ifaces;

import cg.beylhond.soft.exception.EzandoErreur;

/**
 * interface des services d'authentification
 * 
 * @author Delphin BONDONGO
 * @since mer.06.jan.2015 11:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

public interface IAuthentification 
{
	/**
	 * connecter un utilisateur
	 */
	
	public String connecter();
	
	/**
	 * deconnecter un utilisateur
	 * @throws EzandoErreur 
	 */
	
	public String deconnecter();
}

package cg.beylhond.soft.tools;

import java.util.ResourceBundle;

/**
 * cette classe permet simplement de lire les messages qui sont stockés dans le fichier message.properties
 * 
 * @author Delphin BONDONGO
 * @since mer.29.dec.2015 11:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

public class AccesRessource
{
	//banque des textes du programme
	public final static String  MESSAGE = "message";
	
	//banque des textes du programme
	public final static String  CONFIG = "cg.beylhond.soft.config.config";
	
	/**
	 * lire les message contenu dans la base message
	 * 
	 * @param key
	 * @return
	 */
	
	public static String getMessage(String key)
	{
		return ResourceBundle.getBundle(MESSAGE).getString(key);
	}
	
	/**
	 * acceder a une ressource
	 * 
	 * lire un message
	 * 
	 * @param key
	 * @return
	 */
	
	public static String getRessource(String base, String key)
	{
		return ResourceBundle.getBundle(base).getString(key);
	}
}

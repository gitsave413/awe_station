package cg.beylhond.soft.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;


/**
 * classe utilitaire de manipulation des dates java
 * 
 * @author Delphin BONDONGO
 * @since mar.31.mai.2016 03:19
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

public class DateTools 
{
	static SimpleDateFormat simpleDateFormat;
	
	public static Date today = new Date();
	
	/**
	 * formater une date en texte
	 * 
	 * @param patterne
	 * @param date
	 * @return
	 */
	
	public static String format(String patterne, Date date) 
	{
		simpleDateFormat = new SimpleDateFormat(patterne);
		
		return simpleDateFormat.format(date);
	}
	
	/**
	 * former un objet Date a partir d'une chaine de caracteres
	 * 
	 * @param patterne
	 * @param date
	 * @return
	 */
	
	public Date parse(String patterne, String date)
	{
		simpleDateFormat = new SimpleDateFormat(patterne);
		
		try 
		{
			return simpleDateFormat.parse(date);
		}
		catch (ParseException e)
		{
			Logger.getLogger("").warning("impossible de parser la date : "+date);
		}
		
		return null;
	}
}

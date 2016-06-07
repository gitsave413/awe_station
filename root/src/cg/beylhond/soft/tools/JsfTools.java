package cg.beylhond.soft.tools;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import cg.beylhond.soft.exception.EzandoErreur;


/**
 * classe utilitaire de manipulation des fonctionnalites de jsf
 * 
 * @author Delphin BONDONGO
 * @since dim.29.nov.2015 22:59
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

public class JsfTools
{
	
	//liste des differentes portees prisent en compte
	
	public static String REQUEST = "request";
	public static String SESSION = "session";
	public static String APPLICATION = "application";
	
	private static Logger logger = Logger.getLogger("JsfTools");
	
	/**
	 * obtention du context (externe) manipulable de jsf
	 * @return
	 */
	
	public static ExternalContext getEnternalContext()
	{
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	/**
	 * obtention du context manipulable de jsf
	 * @return
	 */
	
	public static FacesContext getContext()
	{
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * recuperer un parametre dans une portee donnee
	 * 
	 * @throws EzandoErreur 
	 */
	
	public static Object getParametre(String scope, String key)  
	{
		if(scope == null)   logger.warning("Une erreur est survenue lors de la recuperation du parametre ; portee invalide");
		if(scope.isEmpty())  logger.warning("Une erreur est survenue lors de la recuperation du parametre ; portee invalide");
		
		if(key == null)  logger.warning("Une erreur est survenue lors de la recuperation du parametre ; cle invalide");
		if(key.isEmpty())  logger.warning("Une erreur est survenue lors de la recuperation du parametre ; cle invalide");
		
		//teste sur les differentes portee
		if(scope.equals(REQUEST))
		{
			return getEnternalContext().getRequestParameterMap().get(key);
		}
		if(scope.equals(SESSION))
		{
			return getEnternalContext().getSessionMap().get(key);
		}
		if(scope.equals(APPLICATION))
		{
			return getEnternalContext().getApplicationMap().get(key);
		}
		
		return null;
	}
	
	/**
	 * ajouter un message dans le context jsf
	 * 
	 * @param msg
	 * @param severity
	 */
	
	public static void addMessage(String msg, Severity severity) 
	{
		String summary = null;
		
		if(FacesMessage.SEVERITY_INFO == severity)
			summary = AccesRessource.getMessage("info");
		
		if(FacesMessage.SEVERITY_WARN == severity)
			summary = AccesRessource.getMessage("warn");
		
		if(FacesMessage.SEVERITY_ERROR == severity)
			summary = AccesRessource.getMessage("error");
		  
		
		FacesMessage fmsg = new FacesMessage(severity, summary , msg);
		
		getContext().addMessage(null, fmsg);
	}
}

package cg.beylhond.soft.tools;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *  classe gerant les problemes de navigation entre les pages
 * 
 * @author Delphin BONDONGO
 * @since dim.29.nov.2015 23:01
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@ManagedBean(name="nav")
@SessionScoped
public class NavigationBean implements Serializable
{ 
	private static final long serialVersionUID = 1L;
	public static String suffixe = "?faces-redirect=true";
	public static String xhtml = ".xhtml";
	public static String jsp = ".jsp";
	private String extention = xhtml;
	
	Logger logger = Logger.getLogger("NavigationBean");
		
	/**
	 * assurer un navigation en recuperant d'abord le nom et l'emplacement de la page source
	 * 
	 * p_name nom de la page à acceder
	 * v_name : nom d'une variable
	 * p_jsp : traitement spécifique pour le cas d'une page jsp
	 * @return
	 */

	public String orienter()
	{
		String pageName = JsfTools.getEnternalContext().getRequestParameterMap().get("p_name");
		
		//page a atteindre
		String p_cible = pageName+extention+suffixe;
		
	
		return p_cible;
	} 
}

package cg.beylhond.soft.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import cg.beylhond.soft.entite.Agent;
import cg.beylhond.soft.tools.JsfTools;

@ManagedBean
public class LoginControleur extends ControleurMere<Agent>
{	
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	private static final long serialVersionUID = 1L;
	private Agent agent;

	/**
	 *  Afficher un message d'erreur apres echec d'authentification
	 *  
	 * @param event
	 */

	public void checkErrors(ComponentSystemEvent event)
	{
		if ("true".equals(JsfTools.getParametre(JsfTools.REQUEST, "failed")))
		{
			setErreursStatut(true);
		}
	}
	
	/**
	 * deconnecter un utilisateur
	 */
	
	public String logout()
	{
		HttpServletRequest request = (HttpServletRequest) JsfTools.getEnternalContext().getRequest();
		
		try 
		{
			request.logout();
			
			return HOME_FULL;
		} 
		catch (ServletException e)
		{
			logger.warning(e.getMessage());
			
			setErreursStatut(true);
		}
		
		return null;
	}
	
	/**
	 * connecter un utilisateur
	 */
	
	public void login()
	{
		HttpServletRequest request = (HttpServletRequest) JsfTools.getEnternalContext().getRequest();
		
		try 
		{
			request.login(agent.getPseudo(), agent.getPwd());;
		} 
		catch (ServletException e)
		{
			logger.warning(e.getMessage());
			 setErreursStatut(true);
		}
	}

	@Override
	public Agent getInstance()
	{
		return new Agent();
	}
}

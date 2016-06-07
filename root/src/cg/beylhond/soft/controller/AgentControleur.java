package cg.beylhond.soft.controller;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.Agent;
import cg.beylhond.soft.mode.TableModel;
import cg.beylhond.soft.tools.JsfTools;


/**
 * classe representant les agents de AWE SERVICE cote vue
 * 
 * @author Delphin BONDONGO
 * @since 12.avril.2016 14:54
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */


@ManagedBean(name="agent")
@SessionScoped
public class AgentControleur extends ControleurMere<Agent>  
{
	private static final long serialVersionUID = 1L;
	
	//le mot de passe de confirmation
	private String confirme_pwd;
	 
	@Override
	public Agent getInstance() 
	{
		title = "Nouveau";
		return new Agent();
	}
	
	/**
	 * cettte methode permet d'affichier le formulaire d'ajout
	 */
	
	public void aff()
	{
		if(title.equals("Nouveau"))
		{
			show = true;
			
			title = "Masquer";
		}
		else if(title.equals("Masquer"))
		{
			show = false;
			title = "Nouveau";
		}
	}
	
	/**
	 * accès à l'agent connecte , après authentification
	 */
	
	Agent getPrincipal()
	{
		Agent agent = null;
		
		Principal principal = JsfTools.getEnternalContext().getUserPrincipal();
		
		for (int i = 0; i < datas.size(); i++)
		{
			if(datas.get(i).getPseudo().equalsIgnoreCase(principal.getName()))
			{
				agent = datas.get(i);
			}
		}
		
		//affirmer qu'un user est en ligne
		agent.setConnecter(true);

		daoMereLocal.update(agent);

		workingMemory.put("principal",agent);
		
		return null;
	}
	
	@Override
	public String getCible() 
	{
		return "agent";
	}
	
	@PostConstruct
	@Override
	public void loadData()
	{
		tableModel = new TableModel<Agent>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}
	
	@Override
	public void search() 
	{
		updateTable(daoMereLocal.search(Agent.class, "bean.nom", "'"+field1_+"%'", "bean.nom", ""));
	}
	
	
	//********************************************************
	
	public String getConfirme_pwd() {
		return confirme_pwd;
	}

	public void setConfirme_pwd(String confirme_pwd) {
		this.confirme_pwd = confirme_pwd;
	}
}

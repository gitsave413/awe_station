package cg.beylhond.soft.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.Etat;
import cg.beylhond.soft.entite.Service;
import cg.beylhond.soft.mode.TableModel;


/**
 * classe representant l'etat d'un objet cote vue
 * 
 * @author Delphin BONDONGO
 * @since 7.mai.2016 19:3
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@ManagedBean(name="etat")
@SessionScoped
public class EtatControleur extends ControleurMere<Etat>  
{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{recu_o}")
	RecuObjetControleur recuObjetControleur;
	
	@PostConstruct
	@Override
	public void loadData()
	{
		setSelection_btn_state(true);
		setUpdate_btn_state(true);
		
		tableModel = new TableModel<Etat>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}
	
	@Override
	public Etat getInstance() 
	{
		return new Etat();
	}
	
	@Override
	public String getCible() 
	{
		return "etat";
	}
	
	@Override
	public boolean valider() 
	{
		if(!daoMereLocal.alreadyExist(Service.class, "libelle", editedObject.getLibelle()))
		{
			setErreursStatut(false);
			return true;
		}

		setErreursStatut(true);

		return false;
	}
	
	@Override
	public void search() 
	{
		updateTable(daoMereLocal.search(Etat.class, "bean.libelle", "'"+field1_+"%'", "bean.libelle", ""));
	}
	
	/**
	 * passer l'etat selectionne a 
	 */
	
	@Override
	public void takeSelectedObjet()
	{
		String started_Page = (String) workingMemory.get("started_Page");
		
		 if(selectedObject != null && started_Page != null)
		 {
			 recuObjetControleur.getEditedObject().getObjet().setEtat(selectedObject);
		 }
	}
	
	@Override
	public String makeChoice()
	{
		takeSelectedObjet();
		
		return super.makeChoice();
	}
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	public RecuObjetControleur getRecuObjetControleur() {
		return recuObjetControleur;
	}

	public void setRecuObjetControleur(RecuObjetControleur recuObjetControleur) {
		this.recuObjetControleur = recuObjetControleur;
	}
}

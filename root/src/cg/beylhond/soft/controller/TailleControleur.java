package cg.beylhond.soft.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.Service;
import cg.beylhond.soft.entite.Taille;
import cg.beylhond.soft.mode.TableModel;


/**
 * classe representant la taille d'un objet cote vue
 * 
 * @author Delphin BONDONGO
 * @since 7.mai.2016 12:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */


@ManagedBean(name="taille")
@SessionScoped
public class TailleControleur extends ControleurMere<Taille>  
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
		
		tableModel = new TableModel<Taille>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}
	
	@Override
	public Taille getInstance() 
	{
		return new Taille();
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
	public String getCible() 
	{
		return "taille";
	}
	
	@Override
	public void search() 
	{
		updateTable(daoMereLocal.search(Taille.class, "bean.libelle", "'"+field1_+"%'", "bean.libelle", ""));
	}
	
	/**
	 * passer le service selectionne a catServiceControleur
	 */
	
	@Override
	public void takeSelectedObjet()
	{
		String started_Page = (String) workingMemory.get("started_Page");
		
		 if(selectedObject != null && started_Page != null)
		 {
			  recuObjetControleur.getEditedObject().getObjet().setTaille(selectedObject);
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

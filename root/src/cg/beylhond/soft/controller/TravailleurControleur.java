package cg.beylhond.soft.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.Travailleur;
import cg.beylhond.soft.mode.TableModel;


/**
 * classe representant les travailleurs (objet isole) cote vue
 * 
 * @author Delphin BONDONGO
 * @since 12.avril.2016 13:39
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */


@ManagedBean(name="travailleur")
@SessionScoped
public class TravailleurControleur extends ControleurMere<Travailleur>  
{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{recu_o}")
	private RecuObjetControleur recuObjetControleur;
	
	@ManagedProperty(value="#{recu_v}")
	private RecuVehiculeControleur recuVehiculeControleur;
	 
	@Override
	public Travailleur getInstance() 
	{
		return new Travailleur();
	}
	
	@Override
	public String getCible() 
	{
		return "travailleur";
	}
	
	@PostConstruct
	@Override
	public void loadData()
	{
		setSelection_btn_state(true);
		setUpdate_btn_state(true);
		
		tableModel = new TableModel<Travailleur>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}
	
	@Override
	public void search() 
	{
		updateTable(daoMereLocal.search(Travailleur.class, "bean.nom", "'"+field1_+"%'", "bean.nom", ""));
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
			if(started_Page.contains(recuObjetControleur.getAddView()))
			{
				recuObjetControleur.getEditedObject().setTravailleur(selectedObject);
			}
			if(started_Page.contains(recuVehiculeControleur.getAddView()))
			{
				recuVehiculeControleur.getLigneRecu().setTravailleur(selectedObject);
				recuVehiculeControleur.setTravailleur(selectedObject);
			}
		}
	}

	@Override
	public String makeChoice()
	{
		takeSelectedObjet();

		return super.makeChoice();
	}
	
	//getters --------------------------------------------------------------------------------------------
	
	public RecuObjetControleur getRecuObjetControleur() {
		return recuObjetControleur;
	}

	public void setRecuObjetControleur(RecuObjetControleur recuObjetControleur) {
		this.recuObjetControleur = recuObjetControleur;
	}
	
	public RecuVehiculeControleur getRecuVehiculeControleur() {
		return recuVehiculeControleur;
	}

	public void setRecuVehiculeControleur(
			RecuVehiculeControleur recuVehiculeControleur) {
		this.recuVehiculeControleur = recuVehiculeControleur;
	}
}

package cg.beylhond.soft.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.Service;
import cg.beylhond.soft.mode.TableModel;


/**
 * classe representant les services cote vue
 * 
 * @author Delphin BONDONGO
 * @since 20.mar.2016 12:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */


@ManagedBean(name="service")
@SessionScoped
public class ServiceControleur extends ControleurMere<Service>  
{
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{catService}")
	public CatServiceControleur catServiceControleur;

	@ManagedProperty(value="#{recu_v}")
	private RecuVehiculeControleur recuVehiculeControleur;

	@PostConstruct
	@Override
	public void loadData()
	{
		setSelection_btn_state(true);
		setUpdate_btn_state(true);
		tableModel = new TableModel<Service>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}

	@Override
	public Service getInstance() 
	{
		return new Service();
	}

	@Override
	public String getCible() 
	{
		return "service";
	}

	@Override
	public void search() 
	{
		updateTable(daoMereLocal.search(Service.class, "bean.libelle", "'"+field1_+"%'", "bean.libelle", ""));
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
			if(started_Page.contains(catServiceControleur.getCible()))
			{
				catServiceControleur.getEditedObject().setService(selectedObject);
			}

			if(started_Page.contains(recuVehiculeControleur.getCible()))
			{
				recuVehiculeControleur.getLigneRecu().getCatService().setService(selectedObject);
			}
		}
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
	public String getDialogName()
	{
		return "date"+FULL_EXTENTION;
	}

	@Override
	int getContentHeight() 
	{
		return 150;
	}

	@Override
	int getContentWidth() 
	{
		return  300;
	}

	@Override
	public String makeChoice()
	{
		takeSelectedObjet();

		return super.makeChoice();
	}

	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	public CatServiceControleur getCatServiceControleur()
	{
		return catServiceControleur;
	}

	public void setCatServiceControleur(CatServiceControleur catServiceControleur) {
		this.catServiceControleur = catServiceControleur;
	}

	public RecuVehiculeControleur getRecuVehiculeControleur() {
		return recuVehiculeControleur;
	}

	public void setRecuVehiculeControleur(
			RecuVehiculeControleur recuVehiculeControleur) {
		this.recuVehiculeControleur = recuVehiculeControleur;
	}

}

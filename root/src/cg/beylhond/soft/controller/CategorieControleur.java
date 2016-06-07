package cg.beylhond.soft.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.Categorie;
import cg.beylhond.soft.entite.Service;
import cg.beylhond.soft.mode.TableModel;


/**
 * classe representant la categorie cote vue
 * 
 * @author Delphin BONDONGO
 * @since 20.mar.2016 12:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@ManagedBean(name="cat")
@SessionScoped
public class CategorieControleur extends ControleurMere<Categorie>  
{
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{catService}")
	public CatServiceControleur catServiceControleur;	
	 	
	@ManagedProperty(value="#{vehicule}")
	private VehiculeControlleur vehiculeControlleur;

	@PostConstruct
	@Override
	public void loadData()
	{
		setSelection_btn_state(true);
		setUpdate_btn_state(true);

		tableModel = new TableModel<Categorie>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}

	@Override
	public Categorie getInstance()
	{
		return new Categorie();
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
		return "categorie";
	}

	@Override
	public void search() 
	{
		updateTable(daoMereLocal.search(Categorie.class, "bean.libelle", "'"+field1_+"%'", "bean.libelle", ""));
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
			if(started_Page.contains(vehiculeControlleur.getCible()))
			{
				 vehiculeControlleur.getEditedObject().setCategorie(selectedObject);
			}
			
			if(started_Page.contains(catServiceControleur.getCible()))
			{
				catServiceControleur.getEditedObject().setCategorie(selectedObject);
			}
		}
	}

	@Override
	public String makeChoice()
	{
		takeSelectedObjet();

		return super.makeChoice();
	}

	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	public CatServiceControleur getCatServiceControleur() {
		return catServiceControleur;
	}

	public void setCatServiceControleur(CatServiceControleur catServiceControleur) {
		this.catServiceControleur = catServiceControleur;
	}
	
	public VehiculeControlleur getVehiculeControlleur() {
		return vehiculeControlleur;
	}

	public void setVehiculeControlleur(VehiculeControlleur vehiculeControlleur) {
		this.vehiculeControlleur = vehiculeControlleur;
	}
}

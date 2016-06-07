package cg.beylhond.soft.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.Vehicule;
import cg.beylhond.soft.mode.TableModel;
import cg.beylhond.soft.tools.JsfTools;

/**
 * classe representant un vehicule cote vue
 * 
 * @author Delphin BONDONGO
 * @since 2.mai.2016 12:59
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@SessionScoped
@ManagedBean(name="vehicule")
public class VehiculeControlleur extends ControleurMere<Vehicule>
{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value ="#{recu_v}")
	private RecuVehiculeControleur recuVehiculeControleur;

	private List<Vehicule> doublures = new ArrayList<Vehicule>();

	@Override
	public Vehicule getInstance() 
	{
		Vehicule vehicule = new Vehicule();
		return vehicule;
	}

	@PostConstruct
	@Override
	public void loadData()
	{
		setSelection_btn_state(true);

		tableModel = new TableModel<Vehicule>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}

	@Override
	public void clear()
	{
		super.clear();
		
		doublures.clear();
	}
	
	@Override
	public boolean valider() 
	{
		if(editedObject.getCategorie() != null)
		{
			search1();
			
			if(daoMereLocal.alreadyExist(Vehicule.class,"libelle", editedObject.getLibelle()))
			{
				return false;
			}
			
			//rendre l'immatriculation en majiscule
			editedObject.setLibelle(editedObject.getLibelle().toUpperCase());
			
			setErreursStatut(false);

			return true;
		}
		else
		{
			setErreursStatut(true);
		}
		
		return false;
	}

	/**
	 * aller  vers la page vue de selection
	 */

	public String goSelection() 
	{
		workingMemory.put("started_Page", getCible()+FULL_EXTENTION);

		String cible = (String) JsfTools.getParametre(JsfTools.REQUEST, "cible");

		return cible+FULL_EXTENTION;
	}

	@Override
	public String getCible()
	{
		return "vehicule";
	}

	@Override
	public void search() 
	{
		updateTable(daoMereLocal.search(Vehicule.class, "bean.libelle", "'"+field1_+"%'", "bean.libelle", ""));
	}

	/**
	 * rechercher par rapport au libelle de l'objet en edition pour trouver des doublures
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public void search1() 
	{
		doublures = daoMereLocal.search(Vehicule.class, "bean.libelle", "'"+editedObject.getLibelle()+"%'", "bean.libelle", "");
	}

	@Override
	public void takeSelectedObjet()
	{
		String started_Page = (String) workingMemory.get("started_Page");

		if(selectedObject != null && started_Page != null)
		{
			if(started_Page.contains(recuVehiculeControleur.getAddView()))
			{
				 recuVehiculeControleur.getEditedObject().setVehicule(selectedObject);
			}
		}
	}

	@Override
	public String makeChoice()
	{
		takeSelectedObjet();

		return super.makeChoice();
	}
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	public List<Vehicule> getDoublures() 
	{
		return doublures;
	}

	public void setDoublures(List<Vehicule> doublures)
	{
		this.doublures = doublures;
	}
	
	public RecuVehiculeControleur getRecuVehiculeControleur() {
		return recuVehiculeControleur;
	}

	public void setRecuVehiculeControleur(
			RecuVehiculeControleur recuVehiculeControleur) {
		this.recuVehiculeControleur = recuVehiculeControleur;
	}
}

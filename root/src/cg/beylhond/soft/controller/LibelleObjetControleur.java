package cg.beylhond.soft.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.LibelleObjet;
import cg.beylhond.soft.mode.TableModel;


/**
 * classe representant les libelles d'objet cote vue
 * 
 * @author Delphin BONDONGO
 * @since 25.avril.2016 23:35
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@ManagedBean(name="libelle")
@SessionScoped
public class LibelleObjetControleur extends ControleurMere<LibelleObjet>  
{
	@ManagedProperty(value="#{recu_o}")
	RecuObjetControleur objetControleur;

	private static final long serialVersionUID = 1L;

	/**
	 * passer le service selectionne a catServiceControleur
	 */

	@Override
	public void takeSelectedObjet()
	{
		String started_Page = (String) workingMemory.get("started_Page");

		if(selectedObject != null && started_Page != null)
		{
			if(started_Page.contains(objetControleur.getAddView()))
			{
				objetControleur.getEditedObject().getObjet().setLibelle(selectedObject.getLibelle());
			}
		}
	}

	@Override
	public String makeChoice()
	{
		takeSelectedObjet();

		return super.makeChoice();
	}

	@Override
	public LibelleObjet getInstance() 
	{
		return new LibelleObjet();
	}

	@Override
	public String getCible() 
	{
		return "objet";
	}

	@PostConstruct
	@Override
	public void loadData()
	{		
		setSelection_btn_state(true);
		setUpdate_btn_state(true);

		tableModel = new TableModel<LibelleObjet>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}

	@Override
	public void search() 
	{
		updateTable(daoMereLocal.search(LibelleObjet.class, "bean.libelle", "'"+field1_+"%'", "bean.libelle", ""));
	}

	public RecuObjetControleur getObjetControleur() {
		return objetControleur;
	}

	public void setObjetControleur(RecuObjetControleur objetControleur) {
		this.objetControleur = objetControleur;
	}
}

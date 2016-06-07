package cg.beylhond.soft.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.Objet;
import cg.beylhond.soft.entite.RecuObjet;
import cg.beylhond.soft.entite.Service;
import cg.beylhond.soft.mode.TableModel;
import cg.beylhond.soft.tools.JsfTools;


/**
 * classe representant les materiel isole (objet isole) cote vue
 * 
 * @author Delphin BONDONGO
 * @since 20.mar.2016 12:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@ManagedBean(name="recu_o")
@SessionScoped
public class RecuObjetControleur extends ControleurMere<RecuObjet>  
{
	private static final long serialVersionUID = 1L;

	@Override
	public RecuObjet getInstance() 
	{
		RecuObjet recu = new RecuObjet();
		recu.setObjet(new Objet());
		recu.setService(new Service());

		return recu;
	}

	@Override
	public String getCible() 
	{
		return "liste_recu_o";
	}

	@PostConstruct
	@Override
	public void loadData()
	{		
		setNew_btn_state(true);
		
		tableModel = new TableModel<RecuObjet>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}

	@Override
	public String getAddView() 
	{
		return "recu_o"+FULL_EXTENTION;	
	}
	
	@Override
	public String save()
	{
		if(valider())
		{
			//par defaut le pointage est 30% du montant du service
			editedObject.setPointage((int)gestionSalaire.pourcentage(editedObject.getTotal(), 30));
			 
			daoMereLocal.save(editedObject);
			
			//enregistrement du salaire
			gestionSalaire.save(editedObject);
			
			//modification du solde de l'entreprise
			
			getEntreprise().setSolde(editedObject.getTotal()-editedObject.getPointage());
			daoMereLocal.update(getEntreprise());

			clear();

			updateTable(init());

			return getCible();
		}

		return null;
	}

	@Override
	public boolean valider() 
	{
		if((editedObject.getTravailleur() != null && editedObject.getObjet().getLibelle() != null) && (editedObject.getObjet().getTaille() != null && editedObject.getObjet().getEtat() != null))
		{
			setErreursStatut(false);
			
			daoMereLocal.save(editedObject.getObjet());

			editedObject.setAgent(getCurrentUsr());
			editedObject.setService(getObjetService());

			String prix = (String) JsfTools.getParametre(JsfTools.REQUEST, "prix_o");
			editedObject.setTotal(Integer.parseInt(prix));


			return true;
		}

		setErreursStatut(true);

		return false;
	}

	/**
	 * aller  vers la page vue de selection
	 */

	public String goSelection() 
	{
		workingMemory.put("started_Page", getAddView()+FULL_EXTENTION);

		String cible = (String) JsfTools.getParametre(JsfTools.REQUEST, "cible");

		return cible+FULL_EXTENTION;
	}

	@Override
	public void search() 
	{
		updateTable(daoMereLocal.search(Objet.class, "bean.libelle", "'"+field1_+"%'", "bean.libelle", ""));
	}
}

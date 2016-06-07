package cg.beylhond.soft.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.CatService;
import cg.beylhond.soft.entite.LigneRecu;
import cg.beylhond.soft.entite.Pointages;
import cg.beylhond.soft.entite.RecuVehicule;
import cg.beylhond.soft.entite.Travailleur;
import cg.beylhond.soft.mode.TableModel;
import cg.beylhond.soft.tools.JsfTools;


/**
 * classe representant le reçu des vehicule cote vue
 * 
 * @author Delphin BONDONGO
 * @since 28.avril.2016 08:44
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@ManagedBean(name="recu_v")
@SessionScoped
public class RecuVehiculeControleur extends ControleurMere<RecuVehicule>  
{
	private static final long serialVersionUID = 1L;

	//elle permet de creer a chaque fois une ligne du recu 
	private LigneRecu ligneRecu;

	//afficher le statut d'erreur pour le vehicule et sa categorie
	private boolean erreurValidation1;

	//afficher le statut d'erreur pour le service et travailleur
	private boolean erreurValidation2;

	//cet objet permetra de pointer le travailleur si le montant total atteint 8000
	private Travailleur travailleur;

	//cette liste permet de pointer pour travailleur sa renumeration quand le total recu atteint 8000
	private List<Pointages> pointages = new ArrayList<Pointages>();

	@Override
	public RecuVehicule getInstance() 
	{
		RecuVehicule recu = new RecuVehicule();
		ligneRecu = new LigneRecu();
		ligneRecu.setCatService(new CatService());

		return recu;
	}
	
	@Override
	public String getShowPageName() 
	{
		return "ligne_recu_v"+FULL_EXTENTION;
	}
	
	/**
	 * pendant la creation du recu, cette methode permet de passer de la confirmation du choix du vehicule à l'ajout des services et travailleurs
	 */

	public String valider1()
	{
		if(editedObject.getVehicule() != null)
		{
			//constitution du recu vehicule
			editedObject.setAgent(getCurrentUsr());

			setShow(true);

			editedObject = (RecuVehicule) daoMereLocal.update(editedObject);

			//garder l'id du recu
			workingMemory.put("recu_id", editedObject.getId());

			setErreursStatut(false);
			setErreurValidation1(false);

			return getCible();
		}

		setErreursStatut(true);
		setErreurValidation2(false);
		setErreurValidation1(true);

		return null;
	}

	@Override
	public String getCible() 
	{
		return "liste_recu_v";
	}

	@PostConstruct
	@Override
	public void loadData()
	{
		setNew_btn_state(true);
		setShow_btn_state(true);

		tableModel = new TableModel<RecuVehicule>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}

	@Override
	public String getAddView()
	{
		return "recu_v"+FULL_EXTENTION;
	}

	@Override
	public boolean valider() 
	{
		if(ligneRecu.getTravailleur() != null && ligneRecu.getCatService().getService() != null)
		{
			setErreursStatut(false);
			setErreurValidation2(false);

			return true;
		}

		setErreurValidation2(true);
		setErreursStatut(true);
		setErreurValidation1(false);

		return false;
	}

	public void removeitem(int position)
	{
		//décrémenter le montant total du recu lors de la suppression d'une ligne recu
		editedObject.setTotal(editedObject.getTotal()-editedObject.getLigneRecu().get(position).getCatService().getPrix());

		editedObject.getLigneRecu().remove(position);
	}

	/**
	 * ajouter des lignes à un recu de vehicule (dans une liste temporaire ensuite persister dans la methode save)
	 * 
	 * @throws Exception 
	 */

	public void addItem() throws Exception
	{
		if(valider())
		{
			int recu_id = (int) workingMemory.get("recu_id");

			ligneRecu.setRecu((RecuVehicule) daoMereLocal.findById(new RecuVehicule(recu_id), RecuVehicule.class));

			ligneRecu.setCatService((CatService) daoMereLocal.search(CatService.class, "bean.categorie.id = '"+editedObject.getVehicule().getCategorie().getId()+"' and bean.service.id = '"+ligneRecu.getCatService().getService().getId()+"'", "", "", "").get(0));

			//le revenu du travailleur est de 30% du prix du service rendu
			ligneRecu.setPointage((int)gestionSalaire.pourcentage(ligneRecu.getCatService().getPrix(), 30));

			//incrementer le montant total du recu lors de l'ajout d'une ligne recu
			editedObject.setTotal(editedObject.getTotal()+ligneRecu.getCatService().getPrix());

			editedObject.getLigneRecu().add(ligneRecu);

			ligneRecu = new LigneRecu();
			ligneRecu.setCatService(new CatService());
		}
	}

	//Pointer manuellement un travailleur
	public void pointer()
	{
		if(travailleur != null)
		{
			Pointages pointage = new Pointages();
			pointage.setMontant(Integer.parseInt((String)JsfTools.getParametre(JsfTools.REQUEST, "prix_o")));
			pointage.setTravailleur(travailleur);

			for (int i = 0; i < editedObject.getLigneRecu().size(); i++)
			{
				if(travailleur.equals(editedObject.getLigneRecu().get(i).getTravailleur()) && !pointages.contains(pointage))
				{
					pointages.add(pointage);
				}
			}
		}

		travailleur = new Travailleur();
	}

	@Override
	public void clear() 
	{
		super.clear();
		setShow(false);
		pointages.clear();
	}

	@Override
	public String save()
	{
		if(!editedObject.getLigneRecu().isEmpty())
		{
			daoMereLocal.update(editedObject);

			//a partir de 8000 le pointage devient fixe
			if(editedObject.getTotal() >= 8000)
			{
				gestionSalaire.save8000(pointages);

				mAjSolde();
			}
			else
			{
				gestionSalaire.save(editedObject.getLigneRecu());

				mAjSolde30();
			}

			clear();

			workingMemory.remove("recu_id");

			updateTable(init());

			setErreurValidation2(false);

			return "liste_recu_v"+FULL_EXTENTION;
		}

		setErreursStatut(true);
		setErreurValidation2(true);

		return null;
	}

	/**
	 * mettre a jour le solde de l'entreprise pour les pointages de 30%
	 */

	public void mAjSolde30()
	{
		int total = 0;

		for (int i = 0; i < editedObject.getLigneRecu().size(); i++) 
		{
			total += editedObject.getLigneRecu().get(i).getPointage();
		}

		//modification du solde de l'entreprise
		getEntreprise().setSolde(getEntreprise().getSolde()+(editedObject.getTotal()- total));

		daoMereLocal.update(getEntreprise());
	}

	/**
	 * mettre a jour le solde de l'entreprise pour les pointages fixes
	 */

	public void mAjSolde()
	{
		int total = 0;

		for (int i = 0; i < pointages.size(); i++) 
		{
			total += pointages.get(i).getMontant();
		}

		//modification du solde de l'entreprise
		getEntreprise().setSolde(getEntreprise().getSolde()+(editedObject.getTotal()- total));

		daoMereLocal.update(getEntreprise());
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

	//**********************************************************************getters and setters

	public LigneRecu getLigneRecu() {
		return ligneRecu;
	}

	public void setLigneRecu(LigneRecu ligneRecu) {
		this.ligneRecu = ligneRecu;
	}

	public boolean isErreurValidation2() {
		return erreurValidation2;
	}

	public void setErreurValidation2(boolean erreurValidation2) {
		this.erreurValidation2 = erreurValidation2;
	}

	public boolean isErreurValidation1() {
		return erreurValidation1;
	}

	public void setErreurValidation1(boolean erreurValidation1) {
		this.erreurValidation1 = erreurValidation1;
	}

	public Travailleur getTravailleur() {
		return travailleur;
	}

	public void setTravailleur(Travailleur travailleur) {
		this.travailleur = travailleur;
	}
}

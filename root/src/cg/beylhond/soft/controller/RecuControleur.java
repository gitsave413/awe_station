package cg.beylhond.soft.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.Query;

import cg.beylhond.soft.entite.Depense;
import cg.beylhond.soft.entite.Entreprise;
import cg.beylhond.soft.entite.Recu;
import cg.beylhond.soft.entite.Salaire;
import cg.beylhond.soft.mode.TableModel;
import cg.beylhond.soft.tools.DateTools;


/**
 * classe representant le reçu cote vue
 * 
 * @author Delphin BONDONGO
 * @since 18.avril.2016 14:00
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@ManagedBean(name="recu")
@SessionScoped
public class RecuControleur extends ControleurMere<Recu>  
{
	private static final long serialVersionUID = 1L;
	private String annee;

	@ManagedProperty(value="#{depense}")
	DepenseControleur depenseControleur;

	@ManagedProperty(value="#{salaire}")
	SalaireControleur salaireControleur;

	//simuler plusieurs années dans une liste
	private List<String> annees = new ArrayList<String>();

	@Override
	public Recu getInstance() 
	{
		return new Recu();
	}

	@Override
	public String getCible() 
	{
		return "liste_recu";
	}

	@PostConstruct
	@Override
	public void loadData()
	{
		initEntreprise();
		
		tableModel = new TableModel<Recu>();
		tableModel.setDatas(init());

		setDataModel(tableModel);

		//charger les annees jusqu'a 2030
		for (int j = 3; j < 51; j++)
		{
			if(j < 10)
			{
				annees.add("200"+j);
			}
			else
			{
				annees.add("20"+j);
			}
		}
	}
	
	/**
	 * initialisation de l'entreprise
	 * 
	 * creation d'un ligne vide en bd
	 */
	
	public void initEntreprise()
	{
		Entreprise entreprise = new Entreprise();
		
		if(daoMereLocal.getAll(Entreprise.class).isEmpty())
		{
			daoMereLocal.update(entreprise);
			
			logger.info("création de l'entreprise");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recu> init()
	{
		String rq ="select CASE month(r.creerLe) WHEN 1 THEN 'Janvier' WHEN 2 THEN 'Février' WHEN 3 THEN 'Mars' WHEN 4 THEN 'Avril' WHEN 5 THEN 'Mai'"+
				" WHEN 6 THEN 'Juin' WHEN 7 THEN 'Juillet' WHEN 8 THEN 'Août' WHEN 9 THEN 'Septembre' WHEN 10 THEN 'Octobre' WHEN 11 THEN 'Novemebre' WHEN 12 THEN 'Décembre'"+
				" END as mois, sum(r.total) as total, "
				+ "count(r.id) as nbre_article, "
				+ "(sum(r.total)-((sum(r.total)*30)/100)) as benefices, "
				+ "((sum(r.total)*30)/100) as masse_salariale, "
				+ "r.id, r.pointage, r.active, r.creerLe, r.type, r.agent_id, r.objet_id, r.service_id, r.travailleur_id, r.vehicule_id "
				+ "from Recu as r "
				+ "where year(r.creerLe) = year(now()) "
				+ "group by month(r.creerLe)";

		Query query = daoMereLocal.getEntityManager().createNativeQuery(rq, Recu.class);

		datas = query.getResultList();
		
		//calculer la somme des depenses avec une autre requete
		depenseControleur.sum(DateTools.format("yyyy", DateTools.today), datas, Depense.class);

		//calculer la masse salariale avec une autre requette
		salaireControleur.sum(DateTools.format("yyyy", DateTools.today), datas, Salaire.class);

		return datas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void search() 
	{
		String rq ="select CASE month(r.creerLe) WHEN 1 THEN 'Janvier' WHEN 2 THEN 'Février' WHEN 3 THEN 'Mars' WHEN 4 THEN 'Avril' WHEN 5 THEN 'Mai'"+
				" WHEN 6 THEN 'Juin' WHEN 7 THEN 'Juillet' WHEN 8 THEN 'Août' WHEN 9 THEN 'Septembre' WHEN 10 THEN 'Octobre' WHEN 11 THEN 'Novemebre' WHEN 12 THEN 'Décembre'"+
				" END as mois, sum(r.total) as total, "
				+ "count(r.id) as nbre_article, "
				+ "r.id, r.pointage, r.active, r.creerLe, r.type, r.agent_id, r.objet_id, r.service_id, r.travailleur_id, r.vehicule_id "
				+ "from Recu as r "
				+ "where year(r.creerLe) = ? "
				+ "group by month(r.creerLe)"; 

		Query query = daoMereLocal.getEntityManager().createNativeQuery(rq, Recu.class).setParameter(1, annee);

		datas = query.getResultList();

		//calculer la somme des depenses avec une autre requete
		depenseControleur.sum(annee, datas, Depense.class);

		//calculer la masse salariale avec une autre requette
		salaireControleur.sum(annee, datas, Salaire.class);
	}

	@Override
	public String getAddView() 
	{
		return "recu";
	}

	@Override
	public String getDialogName() 
	{
		return "date";
	}

	//********************************************************************

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public List<String> getAnnees() {
		return annees;
	}

	public void setAnnees(List<String> annees) {
		this.annees = annees;
	}

	public DepenseControleur getDepenseControleur() {
		return depenseControleur;
	}

	public void setDepenseControleur(DepenseControleur depenseControleur) {
		this.depenseControleur = depenseControleur;
	}
	public SalaireControleur getSalaireControleur() {
		return salaireControleur;
	}

	public void setSalaireControleur(SalaireControleur salaireControleur) {
		this.salaireControleur = salaireControleur;
	}
}

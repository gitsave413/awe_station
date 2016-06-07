package cg.beylhond.soft.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.persistence.Query;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import cg.beylhond.soft.entite.Agent;
import cg.beylhond.soft.entite.CalculBean;
import cg.beylhond.soft.entite.ClasseMere;
import cg.beylhond.soft.entite.Depense;
import cg.beylhond.soft.entite.Entreprise;
import cg.beylhond.soft.entite.Recu;
import cg.beylhond.soft.entite.Salaire;
import cg.beylhond.soft.entite.Service;
import cg.beylhond.soft.exception.EzandoErreur;
import cg.beylhond.soft.ifaces.IDaoMereLocal;
import cg.beylhond.soft.ifaces.IGestionSalaireRemote;
import cg.beylhond.soft.mode.TableModel;
import cg.beylhond.soft.tools.NavigationBean;

/**
 * controleur de base de tout le projet
 * 
 * classe abstraite factorisant les traitements et attributs communs aux differents controlleur de ce projet
 * 
 * @author Delphin BONDONGO
 * @since 8.dec.2015 10:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

public abstract class ControleurMere<T extends ClasseMere> implements Serializable
{
	//T represente une classe fille.

	private static final long serialVersionUID = 1L;

	//objet metier garant de la validation des donnees

	@EJB
	IDaoMereLocal<ClasseMere> daoMereLocal;

	@EJB 
	IGestionSalaireRemote gestionSalaire;

	protected String succes = ""; //message de success
	protected boolean erreursStatut = false;//visibilite des erreurs
	protected boolean show = false; //visibilite d'un composant quelconque
	protected String succesStatut ="false";//visibilite du msg de confirmation
	protected String HOME ="index"; //la page d'accueil
	protected String HOME_FULL = HOME+NavigationBean.xhtml+NavigationBean.suffixe;
	protected String FULL_EXTENTION = NavigationBean.xhtml+NavigationBean.suffixe;
	protected String VISIBLE = "visible"; //visibilite des composants bootstrap
	protected String rendered = "false"; //visibilite des composants jsf
	protected String HIDDEN = "hidden";
	protected String TRUE = "true";
	protected String FAlSE = "false";
	protected String title; //titre d'une page
	protected Logger logger = Logger.getLogger(ControleurMere.class.getSimpleName());
	public static String DEFAULT_WAY = "index";//cette variable permet au programme, après le passage au filtre, de definir la page a affichie.

	//par defaut le programmme tourne en mode offline donc avec des donnees chargees en dur.
	protected boolean offline = true;

	//liste stockant les donnees utilisees lors de l'execution du programme
	public static HashMap<String, Object> workingMemory = new HashMap<>();

	//objet utilise lors de l'édition de donnee d'une entite
	protected T editedObject = getInstance();

	//objet utilise lors d'une selection de donnée dans une liste (pour une entite donnee)
	protected T selectedObject = getInstance();

	//liste contenant les donnees provenant de la bd
	protected List<T> datas; 

	//datable represente le tableau de donnees : manipulation cote serveur
	protected DataTable dataTable;

	//model de donnee abstrait : il definit la logique de remplissage du tableau dans la vue
	protected DataModel<T> dataModel;

	//table model est l'implementation concrete de data model : table model recoi les donnees venant de la base ensuite remplie le dataModel
	protected TableModel<T> tableModel;

	//ce champ sera utilise pour effectuer une recherche
	protected String field1_;

	// activation ou non du bouton de selection -->
	protected boolean selection_btn_state;

	// activation ou non du bouton de consultation -->
	protected boolean show_btn_state;

	// activation ou non du bouton de creation -->
	protected boolean new_btn_state;

	// activation ou non du bouton de modification -->
	protected boolean update_btn_state;

	// activation ou non du bouton de modification du formulaire de modification-->
	protected boolean form_update_btn_state;

	//modes d'une vue
	protected boolean ajout_mode;
	protected boolean consultation_mode;

	//masquer , affichier les erreurs de sélection
	private boolean erreurSelection;

	//masquer , affichier les erreurs de suppression
	private boolean erreurSuppression;

	//masquer, affichier les erreurs de modification
	private boolean erreurModification;

	/**
	 * recuperation de toutes les categorie de la bd
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<T> init()
	{
		logger.info("init() : selectionner tous les "+getInstance().getClass().getSimpleName().toLowerCase());

		return datas = daoMereLocal.getAll(getInstance().getClass());
	}
	
	/**
	 * retourner vers la page liste en detruisant l'objet en sélection
	 * 
	 * @return
	 */
	
	public String goBack()
	{
		setSelectedObject(null);
		
		return getCible();
	}

	//methode concraite***************************************************************************************************************


	/**
	 * retourner l'instance de la classe fille
	 * 
	 * s'il s'agit d'un user par exemple
	 * 
	 * cela permetra des manipulations abstraite a la classe mere
	 * 
	 * @return
	 */

	public abstract T getInstance();

	/**
	 * ouvrir une boite de dialogue
	 */

	public void showDialog() 
	{
		Map<String,Object> options = new HashMap<String, Object>();

		options.put("contentHeight", getContentHeight());
		options.put("contentWidth", getContentWidth());

		RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
	}

	/**
	 * methode a redefinir pour personnaliser la taille de la boite de dialogue
	 * @return
	 */

	int getContentHeight()
	{
		return 500;
	}

	int getContentWidth()
	{
		return 500;
	}

	/**
	 * fermer une boite de dialogue
	 */

	public void closeDialog() 
	{
		RequestContext.getCurrentInstance().closeDialog(getDialogName());
	}

	/**
	 * indiquer le nom de la boite de dialogue a ouvrir en redefinissant cette methode
	 * 
	 * @return
	 */

	public String getDialogName()
	{
		return "default_df";
	}


	/**
	 * ajouter un nouvelle ligne en bd
	 * @throws EzandoErreur 
	 * 
	 */

	public String save()
	{
		if(valider())
		{
			daoMereLocal.save(editedObject);

			clear();

			updateTable(init());

			return getCible();
		}

		return null;
	}


	/**
	 * cette méthode supprimer un  ligne en base de données
	 **/

	public void delete()
	{
		if(selectedObject != null)
		{
			setErreurSuppression(false);

			daoMereLocal.delete(selectedObject);
			selectedObject = getInstance();

			updateTable(init());
		}
		else
		{
			setErreurSuppression(true);
		}
	}

	Agent getCurrentUsr()
	{
		Agent agent = new Agent(461);
		agent = (Agent) daoMereLocal.findById(agent, Agent.class);

		workingMemory.get("principal");

		return agent;
	}

	/**
	 * cette methode charger les donnees dans le tabeau prime faces après deploiement des ejbs et autres....
	 */

	@PostConstruct
	public void loadData()
	{

	}

	/**
	 * cette methode retourne le nom de la page a acceder
	 * 
	 * @param cible
	 * @return
	 */

	public String getCible()
	{
		return "index";
	}

	/**
	 * redefinissez cette methode pour effectuer une recherche par critere. 
	 * 
	 * @return
	 * @throws EzandoErreur 
	 */

	public void search()
	{
		logger.info("exécution de la méthode search de ControleurMere");
	}

	/**
	 * sélectionner une ligne du tableau puis revenir sur la vue de depart
	 */

	public String makeChoice()
	{
		String started_Page = (String) workingMemory.get("started_Page");

		if(selectedObject != null && started_Page != null)
		{
			setErreurSelection(false);

			setSelectedObject(null);

			workingMemory.remove("started_Page");

			return started_Page;
		}
		else
		{
			setErreurSelection(true);
			return null;
		}
	}

	/**
	 * methode de recuperation du service des objets
	 */

	public Service getObjetService()
	{
		return daoMereLocal.getObjetService();
	}
	
	/**
	 * recuperation de la seule entreprise qui est creer au le lancement du programme
	 * 
	 * @return
	 */
	
	public Entreprise getEntreprise() 
	{
		Entreprise entreprise = (Entreprise) workingMemory.get("entreprise");
		
		if(entreprise == null)
		{
			entreprise = (Entreprise) daoMereLocal.getAll(Entreprise.class).get(0);
			
			workingMemory.put("entreprise", entreprise);
		}
		
		
		return entreprise;
	}

	/**
	 * lire la valeur de l'objet selectionne ,d'un autre bean, dans un autre controleur
	 */

	public void takeSelectedObjet()
	{

	}

	/**
	 * cette méthode affiche les détails d'un enregistrement dans un formulaire non editable
	 * 
	 * @return
	 */

	public String showData()
	{
		if(selectedObject != null)
			return getShowPageName();
		
		return null;
	}
	
	
	/**
	 * retourner le nom de la page de consultation
	 * 
	 * @return
	 */
	
	public String getShowPageName() 
	{
		return null;
	}

	/**
	 * aller vers la vue de création a patir du bouton d'ajout du tableau
	 */

	public String goToAddView()
	{
		editedObject = getInstance();

		setAjout_mode(true);

		return getAddView();
	}

	/**
	 * aller vers la vue de modification ou afficher l'objet en selection dans le formulaire d'edition
	 */


	public void goForUpdate()
	{
		if(selectedObject != null)
		{
			editedObject = selectedObject;

			setForm_update_btn_state(true);
			setErreurModification(false);
		}
		else
		{
			setErreurModification(true);
		}
	}

	/**
	 * recuperer le nom de la page d'edition
	 * @return
	 */

	public String getAddView()
	{
		return null;
	}

	/**
	 * renitialiser un formulaire , masquer les messages d'erreurs
	 */

	public void clear() 
	{
		field1_ = null;
		setSelectedObject(null);
		setEditedObject(getInstance());

		setErreurSelection(false);
		setErreurSuppression(false);
		setErreursStatut(false);
		setErreurModification(false);
		setForm_update_btn_state(false);
	}
	
	/**
	 * verifier si un libelle existe en base de données
	 */
	
	@SuppressWarnings("rawtypes")
	public boolean existe(Class type, String field, String value)
	{
		return daoMereLocal.alreadyExist(type, field, value);
	}


	/**
	 * mettre à jour les donnees dans le tableau se trouvant dans la vue
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	void updateTable(List list)
	{
		tableModel.setDatas(list);

		setDataModel(tableModel);
	}

	/**
	 * mettre a jour les donnees d'une entite
	 */

	public void update()
	{
		daoMereLocal.update(editedObject);
		setForm_update_btn_state(false);

		clear();
	}

	/**
	 * calculer en fonction d'une annee, calculer la somme soit des salaires soit des benefices
	 * 
	 *  puis mettre a jour le resume de calcul
	 *  
	 * @param annee
	 * @param recus
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void sum(String annee, List<Recu> recus, Class type)
	{

		String rq = "select CASE month(bean.creerLe) WHEN 1 THEN 'Janvier' "
				+"WHEN 2 THEN 'Février' WHEN 3 THEN 'Mars' WHEN 4 THEN 'Avril' WHEN 5 THEN 'Mai' "
				+"WHEN 6 THEN 'Juin' WHEN 7 THEN 'Juillet' WHEN 8 THEN 'Août' WHEN 9 THEN 'Septembre' "
				+"WHEN 10 THEN 'Octobre' WHEN 11 THEN 'Novemebre' WHEN 12 THEN 'Décembre' END as mois,"
				+giveSumField()+" "
				+"from "+type.getSimpleName()+" bean where bean.active = 1 and year(bean.creerLe) = ? "
				+"group by month(bean.creerLe)";

		Query query = daoMereLocal.getEntityManager().createNativeQuery(rq, type).setParameter(1, annee);

		List<CalculBean> list = query.getResultList();

		//astuce pour mettre a jour les depenses, le salaire au niveau du resumé de calcul
		for (int i = 0; i < list.size(); i++)
		{
			for (int j = 0; j < recus.size(); j++)
			{
				if(recus.get(j).getMois().equals(list.get(i).getMois()))
				{
					if(list.get(i) instanceof Salaire)
					{
						recus.get(j).setMasse_salariale(list.get(i).getMontant());
					}
					if(list.get(i) instanceof Depense)
					{
						recus.get(j).setDepences(list.get(i).getMontant());
					}
				}
			}
		}
	}

	/**
	 * indiquer les attributs qui seront en lecture dans le calcul de la somme
	 * 
	 * @return
	 */

	public String giveSumField()
	{
		return "bean.active,bean.creerLe,bean.id,";
	}

	/**
	 * ouvrir une boite de dialogue pour la modification
	 * 
	 * @return
	 */

	public String openInUpdateMode()
	{
		return "";
	}
	
	/**
	 * vider le working memory
	 */
	
	@PreDestroy
	public void clearWorkingMemory()
	{
		workingMemory.clear();
	}

	//methode abstraite***************************************************************************************************************

	/**
	 * methode permettant la validation des donnees avant une action quelconque
	 */

	public boolean valider()
	{
		return true;
	}

	//accesseurs--------------------------------------------------------------------------------------------------------------------

	public String getSucces() {
		return succes;
	}

	public void setSucces(String succes) {
		this.succes = succes;
	}

	public String getSuccesStatut() {
		return succesStatut;
	}

	public void setSuccesStatut(String succesStatut) {
		this.succesStatut = succesStatut;
	}

	public void setSelectedObject(T selectedObject) {
		this.selectedObject = selectedObject;
	}

	public T getSelectedObject() {
		return selectedObject;
	}

	public List<T> getDatas()
	{
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public boolean isErreursStatut() {
		return erreursStatut;
	}

	public void setErreursStatut(boolean erreursStatut) {
		this.erreursStatut = erreursStatut;
	}

	public T getEditedObject()
	{
		return editedObject;
	}

	public void setEditedObject(T editedObject) {
		this.editedObject = editedObject;
	}


	public boolean isOffline() {
		return offline;
	}

	public void setOffline(boolean offline) {
		this.offline = offline;
	}

	public void setRendered(String rendered) {
		this.rendered = rendered;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	} 

	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}

	public DataModel<T> getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModel<T> dataModel) {
		this.dataModel = dataModel;
	}

	public String getField1_() {
		return field1_;
	}

	public void setField1_(String field1_) {
		this.field1_ = field1_;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public boolean getSelection_btn_state() {
		return selection_btn_state;
	}

	public void setSelection_btn_state(boolean selection_btn_state) {
		this.selection_btn_state = selection_btn_state;
	}

	public boolean isErreurSelection() {
		return erreurSelection;
	}

	public void setErreurSelection(boolean erreurSelection) {
		this.erreurSelection = erreurSelection;
	}

	public boolean isErreurSuppression() {
		return erreurSuppression;
	}

	public void setErreurSuppression(boolean erreurSuppression) {
		this.erreurSuppression = erreurSuppression;
	}

	public boolean isShow_btn_state() {
		return show_btn_state;
	}

	public void setShow_btn_state(boolean show_btn_state) {
		this.show_btn_state = show_btn_state;
	}

	public boolean isNew_btn_state() {
		return new_btn_state;
	}

	public void setNew_btn_state(boolean new_btn_state) {
		this.new_btn_state = new_btn_state;
	}

	public boolean isAjout_mode() {
		return ajout_mode;
	}

	public void setAjout_mode(boolean ajout_mode) {
		this.ajout_mode = ajout_mode;
	}

	public boolean isConsultation_mode() {
		return consultation_mode;
	}

	public void setConsultation_mode(boolean consultation_mode) {
		this.consultation_mode = consultation_mode;
	}

	public boolean isUpdate_btn_state() {
		return update_btn_state;
	}

	public void setUpdate_btn_state(boolean update_btn_state) {
		this.update_btn_state = update_btn_state;
	}

	public boolean isErreurModification() {
		return erreurModification;
	}

	public void setErreurModification(boolean erreurModification) {
		this.erreurModification = erreurModification;
	}

	public boolean isForm_update_btn_state() {
		return form_update_btn_state;
	}

	public void setForm_update_btn_state(boolean form_update_btn_state) {
		this.form_update_btn_state = form_update_btn_state;
	}
}

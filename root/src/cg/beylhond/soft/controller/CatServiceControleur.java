package cg.beylhond.soft.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.CatService;
import cg.beylhond.soft.mode.TableModel;
import cg.beylhond.soft.tools.JsfTools;


/**
 * classe representant l'assocition entre une categorie et un service cote vue
 * 
 * @author Delphin BONDONGO
 * @since 13.avril.2016 09:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */


@ManagedBean(name="catService")
@SessionScoped
public class CatServiceControleur extends ControleurMere<CatService>  
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public CatService getInstance() 
	{
		CatService catService = new CatService();
		
		return catService;
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
		return "cat_service";
	}
	
	@Override
	public boolean valider()
	{
		 if(editedObject.getService() != null && editedObject.getCategorie() != null)
		 {
			 setErreursStatut(false);
			 
			 return true;
		 }
		 else
		 {
			setErreursStatut(true);
			 
			return false;
		 }
	}
	
	/**
	 * mettre a jour les donnees d'une entite
	 */
	
	@Override
	public void update()
	{
		String prix = (String) JsfTools.getParametre(JsfTools.REQUEST, "prix_o");
		editedObject.setPrix(Integer.parseInt(prix));
		
		super.update();
	}
	
	@Override
	public String save() 
	{
		String prix = (String) JsfTools.getParametre(JsfTools.REQUEST, "prix_o");
		
		editedObject.setPrix(Integer.parseInt(prix));
		
		return super.save();
	}
	
	@PostConstruct
	@Override
	public void loadData()
	{
		setUpdate_btn_state(true);
		
		tableModel = new TableModel<CatService>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}
}

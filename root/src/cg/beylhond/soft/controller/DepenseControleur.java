package cg.beylhond.soft.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.Depense;
import cg.beylhond.soft.mode.TableModel;
import cg.beylhond.soft.tools.JsfTools;


/**
 * classe representant les depenses cote vue
 * 
 * @author Delphin BONDONGO
 * @since 27.mai.2016 08:36
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@ManagedBean(name="depense")
@SessionScoped
public class DepenseControleur extends ControleurMere<Depense>  
{
	private static final long serialVersionUID = 1L;

	private int depense;

	@PostConstruct
	@Override
	public void loadData()
	{
		tableModel = new TableModel<Depense>();
		tableModel.setDatas(super.init());

		setDataModel(tableModel);
	}

	@Override
	public boolean valider() 
	{
		int montant = Integer.parseInt((String) JsfTools.getParametre(JsfTools.REQUEST, "prix"));

		//le solde doit etre superieur ou egale au montant
		if(getEntreprise().getSolde() >= montant)
		{
			//recupération de l'auteur et du montant de la dépense

			editedObject.setAgent(getCurrentUsr());
			editedObject.setMontant(montant);
			
			setErreursStatut(false);
			
			return true;
		}

		setErreursStatut(true);
		
		return false;
	}

	@Override
	public Depense getInstance()
	{
		return new Depense();
	}   

	@Override
	public String getCible() 
	{
		return "depense";
	}

	@Override
	public String giveSumField()
	{
		return super.giveSumField()+"bean.motif, bean.agent_id, sum(bean.montant) as montant";
	}

	public int getDepense() {
		return depense;
	}

	public void setDepense(int depense) {
		this.depense = depense;
	}
}

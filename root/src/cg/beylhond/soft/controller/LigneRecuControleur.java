package cg.beylhond.soft.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import cg.beylhond.soft.entite.LigneRecu;
import cg.beylhond.soft.mode.TableModel;


/**
 * classe representant une ligne du reçu cote vue
 * 
 * @author Delphin BONDONGO
 * @since 18.avril.2016 14:00
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@ManagedBean(name="detail")
@SessionScoped
public class LigneRecuControleur extends ControleurMere<LigneRecu>  
{
	private static final long serialVersionUID = 1L;

	@Override
	public LigneRecu getInstance() 
	{
		return new LigneRecu();
	}

	@Override
	public String getCible() 
	{
		return "recu";
	}

	@PostConstruct
	@Override
	public void loadData()
	{
		tableModel = new TableModel<LigneRecu>();
		setDataModel(tableModel);
	}
}

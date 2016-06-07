package cg.beylhond.soft.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Query;

import cg.beylhond.soft.entite.Salaire;
import cg.beylhond.soft.mode.TableModel;

/**
 * classe representant les salaires cote vue
 * 
 * @author Delphin BONDONGO
 * @since 16.avril.2016 12:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 *
 */

@ManagedBean(name="salaire")
@SessionScoped
public class SalaireControleur extends ControleurMere<Salaire>  
{
	private String mois_;
	private String annee;

	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	@Override
	public void loadData() 
	{
		tableModel = new TableModel<Salaire>();
		tableModel.setDatas(init());

		setDataModel(tableModel);
	}

	@Override
	public Salaire getInstance() 
	{
		return new Salaire();
	}

	@Override
	public String getCible() 
	{
		return "salaire";
	}
	
	@Override
	public String giveSumField()
	{
		return super.giveSumField()+"sum(bean.montant) as montant, bean.humain_id";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void search() 
	{
		String rq = "select h.id, h.active, h.creerLe, h.nom, h.prenom, h.telephone, h.adresse, h.avatar, h.connecter, h.dateNaiss, h.pseudo, h.pwd, h.hold_pwd, h.email, h.ville, h.pays, h.genre, h.profession, "
				+"sum(s.montant) as montant, s.humain_id, s.mois "
				+"from Salaire s, Humain h where month(s.creerLe) = ? and year(s.creerLe) = ? "
				+"and h.id = s.humain_id "
				+"group by s.humain_id";
		
		int month = Integer.parseInt(mois_);
		int year = Integer.parseInt(annee);
		
		Query query = daoMereLocal.getEntityManager().createNativeQuery(rq, Salaire.class).setParameter(1, month).setParameter(2, year);
		
		datas = query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Salaire> init() 
	{
		String rq = "select h.id, h.active, h.creerLe, h.nom, h.prenom, h.telephone, h.adresse, h.avatar, h.connecter, h.dateNaiss, h.pseudo, h.pwd, h.hold_pwd, h.email, h.ville, h.pays, h.genre, h.profession, "
				+"sum(s.montant) as montant, s.humain_id, s.mois "
				+"from Salaire s, Humain h where month(s.creerLe) =  month(now()) and year(s.creerLe) = year(now()) "
				+"and h.id = s.humain_id "
				+"group by s.humain_id";
		 
		Query query = daoMereLocal.getEntityManager().createNativeQuery(rq, Salaire.class);
		
		datas = query.getResultList();
		
		return datas;
	}

	//getters and setters-----------------------------

	public String getMois_() {
		return mois_;
	}

	public void setMois_(String mois_) {
		this.mois_ = mois_;
	}
	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}
}

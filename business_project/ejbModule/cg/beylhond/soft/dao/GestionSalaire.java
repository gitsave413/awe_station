package cg.beylhond.soft.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cg.beylhond.soft.entite.ClasseMere;
import cg.beylhond.soft.entite.LigneRecu;
import cg.beylhond.soft.entite.Pointages;
import cg.beylhond.soft.entite.RecuObjet;
import cg.beylhond.soft.entite.Salaire;
import cg.beylhond.soft.ifaces.IDaoMereRemote;
import cg.beylhond.soft.ifaces.IGestionSalaireLocal;
import cg.beylhond.soft.ifaces.IGestionSalaireRemote;

/**
 * Classe representant le systeme d'affectation des salaires
 * 
 * @author Delphin BONDONGO
 * @since lun.16.avril 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Stateless
public class GestionSalaire implements IGestionSalaireRemote, IGestionSalaireLocal
{
	@EJB IDaoMereRemote<ClasseMere> daoMere;

	/**
	 * calculer le pourcentage
	 */

	public double pourcentage(int valeur, int pourcentage)
	{
		return (valeur * pourcentage) / 100;
	}

	/**
	 * application de la logique des 30 %
	 */

	@Override
	public void save(List<LigneRecu> ligneRecus) 
	{
		for (int i = 0; i < ligneRecus.size(); i++)
		{
			Salaire salaire = new Salaire();
			salaire.setHumain(ligneRecus.get(i).getTravailleur());

			salaire.setMontant(ligneRecus.get(i).getPointage());

			daoMere.save(salaire);
		}
	}

	/**
	 * application de la logique des 30 %
	 */

	@Override
	public void save(RecuObjet recu)
	{
		Salaire salaire = new Salaire();
		salaire.setMontant(recu.getPointage());

		salaire.setHumain(recu.getTravailleur());

		daoMere.save(salaire);
	}

	@Override
	public void save8000(List<Pointages> pointages)
	{
		for (int i = 0; i < pointages.size(); i++) 
		{
			Salaire salaire = new Salaire();

			//recuperation du travailleur
			salaire.setHumain(pointages.get(i).getTravailleur());

			//recuperation du montant associé au travailleur
			salaire.setMontant(pointages.get(i).getMontant());

			daoMere.save(salaire);
		}
	}
}

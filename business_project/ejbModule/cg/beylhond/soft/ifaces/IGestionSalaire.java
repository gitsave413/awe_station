package cg.beylhond.soft.ifaces;

import java.util.List;

import cg.beylhond.soft.entite.LigneRecu;
import cg.beylhond.soft.entite.Pointages;
import cg.beylhond.soft.entite.RecuObjet;

/**
 * interface exposant tous les services utiles a la gestion des salaires de AWE STATION SERVICE
 * @author migration
 *
 */
public interface IGestionSalaire
{
	/**
	 * pointer le salaire journalier d'un travailleur pour un RecuObjet
	 * 
	 * @param recu
	 */

	public void save(RecuObjet recu);
	
	/**
	 * pointer le salaire journalier d'un travailleur pour un RecuVehicule
	 * 
	 * @param ligneRecus
	 */

	public void save(List<LigneRecu> ligneRecus);
	
	/**
	 * pointer le salaire journalier d'un travailleur pour un RecuVehicule quand le total du recu atteint 8000
	 *
	 *@param pointages
	 */
	
	public void save8000(List<Pointages> pointages);
	
	/**
	 * calculer le pourcentage d'un nombre
	 * 
	 * @param valeur
	 * @param pourcentage
	 * @return
	 */
	
	public double pourcentage(int valeur, int pourcentage);

}

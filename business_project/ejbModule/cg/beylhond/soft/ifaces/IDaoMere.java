package cg.beylhond.soft.ifaces;

import java.util.List;

import javax.persistence.EntityManager;

import cg.beylhond.soft.entite.Agent;
import cg.beylhond.soft.entite.ClasseMere;
import cg.beylhond.soft.entite.Service;

public interface IDaoMere <T extends ClasseMere> 
{
	/**
	 * method de creer d'une entite dans le datastore
	 * 
	 * @param entity
	 * @return
	 * @throws Exception 
	 * @throws EzandoErreur 
	 */

	public T save(T entity);
	
		/**
	 * methode d modification des vaeurs d'une entite
	 * 
	 * @param entity
	 * @return
	 */
	
	public T update(T entity);

	/**
	 * methode de suppression logique d'une entite
	 * 
	 * @param id
	 */
	
	public void delete(Object id);

	 /**
	  * methode de recherche dans le datastore
	  * 
	  * @param id
	  * @param properties
	  * @return
	  */
	
	@SuppressWarnings("rawtypes")
	public T findById(T object, Class type);
	
	@SuppressWarnings("rawtypes")
	public List getAll(Class t);
	
	/**
	 * connecter un utilisateur
	 */
	
	public Agent login(Agent usr);
	
	/**
	 * deconnecter un utilisateur
	 * @throws EzandoErreur 
	 */
	
	public boolean logout(Agent usr);
	
	/**
	 * recherche avec full options
	 * 
	 * @param type
	 * @param where
	 * @param like
	 * @param orderBy
	 * @param groupBy
	 * @return
	 */
	
	@SuppressWarnings("rawtypes")
	public List search(Class type, String where, String like, String orderBy, String groupBy);
	
	/**
	 * faire une recherche avec jointure : parametrable
	 * 
	 * colonne represente la colonne sur laquelle sera appliqué la recherche
	 * 
	 * alias represente les alias des entites
	 */
	
	@SuppressWarnings("rawtypes")
	public List search(String alias, String tables, String join, String where, String orderBy);
	
	/**
	 * faire une recherche sur un champ en envoyan une valeur
	 * 
	 * @param type
	 * @param field
	 * @param value
	 * @return
	 */
	
	@SuppressWarnings("rawtypes")
	public List search(Class type, String field, String value);
	
	/**
	 * methode de recuperation/fabrication du service des objets
	 */
	
	public Service getObjetService();
	
	/**
	 * acceder au gestionnaire de persistance
	 * @return
	 */
	
	public EntityManager getEntityManager();
	
	/**
	 * ecrivez une requette entiere
	 * 
	 * @param rq
	 * @return
	 */
	
	@SuppressWarnings("rawtypes")
	public List search(String rq) ;
	
	/**
	 * controler l'existance d'une donnée en bd
	 * 
	 * @return
	 */
	
	@SuppressWarnings("rawtypes")
	public boolean alreadyExist(Class type,String field, String value);
	 
}

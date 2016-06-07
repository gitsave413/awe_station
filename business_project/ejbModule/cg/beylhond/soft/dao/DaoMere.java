package cg.beylhond.soft.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import cg.beylhond.soft.entite.Agent;
import cg.beylhond.soft.entite.ClasseMere;
import cg.beylhond.soft.entite.Service;
import cg.beylhond.soft.ifaces.IDaoMereLocal;
import cg.beylhond.soft.ifaces.IDaoMereRemote;

/**
 * Session Bean implementation class DaoMere
 * @param <T>
 */

@Stateless
public class DaoMere<T> implements IDaoMereLocal<ClasseMere>, IDaoMereRemote<ClasseMere>
{ 
	@PersistenceUnit(unitName="awe-service-persistence-unit")
	private EntityManagerFactory factory;

	@PersistenceContext(unitName="awe-service-persistence-unit")
	private EntityManager entityManager;

	//cette liste permet de sauvegarder les résultats temporairement
	private List<ClasseMere> dataHandler = new ArrayList<ClasseMere>();

	public ClasseMere save(ClasseMere param)
	{
		entityManager.persist(param);

		return param;
	}

	@Override
	public ClasseMere update(ClasseMere entity) 
	{
		return  entityManager.merge(entity);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ClasseMere> getAll(Class t) 
	{
		String requete = "select dto from "+t.getSimpleName()+" dto where active = 1"; //la requête jpql
		List<ClasseMere> liste = new ArrayList<ClasseMere>();

		liste = entityManager.createQuery(requete).getResultList(); //reception de la liste.

		return liste;
	}

	@Override
	public void delete(Object id)
	{
		entityManager.remove(entityManager.merge(id));//tentative de supression
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ClasseMere findById(ClasseMere object, Class type) 
	{
		return entityManager.find(type, object.getId());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List search(Class type, String field, String value)  
	{
		String rq = "select bean from "+type.getSimpleName()+" bean where bean.active = 1 and bean."+field+" = ?)";
		List<ClasseMere> list =  entityManager.createQuery(rq).setParameter(1, value).getResultList();

		return list;
	}

	@SuppressWarnings({ "rawtypes"})
	public List search(Class type, String where, String like, String orderBy, String groupBy)  
	{
		//activation de la clause where
		if(where != null && !where.isEmpty())
			where = "where bean.active = 1 and "+where;

		//activation de la clause like
		if(like != null && !like.isEmpty())
			like = "like "+like;

		//activation de la clause order by
		if(orderBy != null && !orderBy.isEmpty())
			orderBy = "order by "+orderBy;

		//activation de la clause groupe by
		if(groupBy != null && !groupBy.isEmpty())
			groupBy = "group by "+groupBy;

		//requête jpql 
		String requete = "select bean from "+type.getSimpleName()+" bean "+where+" "+like+" "+orderBy+" "+groupBy;

		Query q = entityManager.createQuery(requete);

		List liste = q.getResultList(); //récupération du résultat

		return liste;
	}

	@SuppressWarnings({ "rawtypes"})
	public List search(String rq)  
	{
		Query q = entityManager.createQuery(rq);

		List liste = q.getResultList(); //récupération du résultat

		return liste;
	}


	/**
	 * faire une recherche avec jointure : parametrable
	 * 
	 * alias represente les alias des entites
	 */

	@SuppressWarnings("rawtypes")
	@Override
	public List search(String alias, String tables, String join, String where, String orderBy)
	{
		//activation de la clause where
		if(where != null && !where.isEmpty())
			where = "where "+where;

		//activation de la clause order by
		if(orderBy != null && !orderBy.isEmpty())
			orderBy = "order by "+orderBy; 

		//requête jpql 
		String requete = "select "+alias+" from "+tables+" join "+join+" "+where+" "+orderBy;

		Query q = entityManager.createQuery(requete);

		List liste = q.getResultList(); //récupération du résultat

		return liste;
	} 

	@SuppressWarnings("unchecked")
	@Override
	public Agent login(Agent usr) 
	{
		dataHandler = search(Agent.class, "bean.pseudo = '"+usr.getPseudo()+"' and bean.pwd =  '"+usr.getPwd()+"' ", "", "", "");

		if (!dataHandler.isEmpty()) 
		{
			return (Agent) dataHandler.get(0);
		}
		return null;
	}

	@Override
	public boolean logout(Agent usr) 
	{
		usr = (Agent) findById(usr, Agent.class);

		return usr == null;
	}

	/**
	 * methode de recuperation/fabrication du service des objets
	 */

	@SuppressWarnings("unchecked")
	public Service getObjetService()
	{
		Service service = new Service();
		service.setLibelle("Prestation");

		dataHandler = search(service.getClass(), "bean.libelle = "+"'"+service.getLibelle()+"'", "", "", "");

		if(!dataHandler.isEmpty())
		{
			service = (Service) dataHandler.get(0);
			return service;
		}

		service = entityManager.merge(service);

		return service;
	}

	@Override
	public EntityManager getEntityManager()
	{
		return entityManager;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean alreadyExist(Class type,String field, String value) 
	{
		String rq = "select bean from "+type.getSimpleName()+" bean where bean.active = 1 and bean."+field+" = ?)";
		List<ClasseMere> list =  entityManager.createQuery(rq).setParameter(1, value).getResultList();

		return !list.isEmpty();
	}
}

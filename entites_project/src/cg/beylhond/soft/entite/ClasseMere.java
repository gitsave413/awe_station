package cg.beylhond.soft.entite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe principale pour toutes les entites de ezando
 * elle est basee sur objectify : orm pour le datastore
 * 
 * @author Delphin BONDONGO
 * @since dim.29.nov.2015 11:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@MappedSuperclass
public class ClasseMere implements Serializable
{ 

	private static final long serialVersionUID = 1L;
	
	//attribut permettant de savoir si une entite est en status active ou pas
	protected boolean active;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
	
	//date de creation d'un element
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creerLe;
	
	//constructeur sans arguments
	
	public ClasseMere() 
	{
		super();
		creerLe = new Date();
		active = true;
	}
	
	public ClasseMere(int id) {
		super();
		this.id = id;
		creerLe = new Date();
		active = true;
	}

	//les accesseurs *************************************************************************************************

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreerLe() {
		return creerLe;
	}


	public void setCreerLe(Date creerLe) {
		this.creerLe = creerLe;
	} 
	
}

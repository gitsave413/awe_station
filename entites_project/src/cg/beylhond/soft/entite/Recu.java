package cg.beylhond.soft.entite;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
/**
 * Classe representant un recu de A.W.E STATION SERVICE
 * 
 * @author Delphin BONDONGO
 * @since mer.30.mars 2016
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(length=2,name="type")
public class Recu extends ClasseMere 
{
	private static final long serialVersionUID = 1L;
	
	private int total;
	
	private String mois;
	
	private int nbre_article;
	
	@Transient
	private double benefices;
	
	@Transient
	private double masse_salariale;
	
	@Transient
	private int depences;
	
	@NotNull
	@OneToOne
	private Agent agent;
	
	//******************************************************************************
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getMois() {
		return mois;
	}
	
	public int getNbre_article() {
		return nbre_article;
	}
	
	public double getBenefices() {
		return benefices;
	}

	public double getMasse_salariale() {
		return masse_salariale;
	}
	public int getDepences() {
		return depences;
	}

	public void setDepences(int depences) {
		this.depences = depences;
	}
	public void setBenefices(double benefices) {
		this.benefices = benefices;
	}

	public void setMasse_salariale(double masse_salariale) {
		this.masse_salariale = masse_salariale;
	}
}

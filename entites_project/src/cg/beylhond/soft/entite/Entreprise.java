package cg.beylhond.soft.entite;

import javax.persistence.Entity;
/**
 * Classe representant l'entreprise soit A.W.E STATION SERVICE 
 * 
 * @author Delphin BONDONGO
 * @since mar.31.mai 2016 07:27
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
public class Entreprise extends ClasseMere
{
	private static final long serialVersionUID = 1L;
	
	private String nom;
	private String adresse;
	private String telephone;
	private String email;
	private String boitePostale;
	private String nombreEmploye;
	
	private double solde;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBoitePostale() {
		return boitePostale;
	}
	public void setBoitePostale(String boitePostale) {
		this.boitePostale = boitePostale;
	}
	public String getNombreEmploye() {
		return nombreEmploye;
	}
	public void setNombreEmploye(String nombreEmploye) {
		this.nombreEmploye = nombreEmploye;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
}

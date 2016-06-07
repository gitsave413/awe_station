package cg.beylhond.soft.entite;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * Classe representant un utilisateur du programme
 * 
 * @author Delphin BONDONGO
 * @since mar.8.dec.2015 09:16
 * 
 * <a href="mailto:delphinpc.4@gmail.com">delphin BONDONGO (Programmeur)</a>
 */

@Entity
@DiscriminatorValue(value="Agent")
public class Agent extends Humain
{
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Role role;
	@Size(min=3, max=20)
	@NotNull
	private String pseudo;

	@Size(min=6, max=20)
	@NotNull
	private String pwd;

	@Size(min=6, max=20)
	private String hold_pwd;

	@Email 
	private String email;

	private boolean connecter;
	private String ville;
	private String pays;
	private String adresse;

	@Temporal(TemporalType.DATE)
	private Date dateNaiss;

	private Genre genre;
	private String profession;
	private String avatar;

	//constructeur
	public Agent()
	{
		super();

		avatar = "/avatars/man.png";
		pays = "Congo";
		ville = "Brazzaville";
	}

	//constructeur
	public Agent(int id)
	{
		this.id = id;

		pays = "Congo";
		ville = "Brazzaville";
	}


	//les accesseurs-------------------------------------------------------------------------------------------------------------------------------

	public String getPseudo()
	{
		return pseudo;
	}
	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}

	public String getEmail()
	{
		return email;
	} 

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getAvatar()
	{
		return avatar;
	}
	public void setAvatar(String avatar)
	{
		this.avatar = avatar;
	}
	public String getProfession()
	{
		return profession;
	}
	public void setProfession(String profession)
	{
		this.profession = profession;
	}
	public boolean isConnecter()
	{
		return connecter;
	}
	public void setConnecter(boolean connecter)
	{
		this.connecter = connecter;
	}

	public String getVille()
	{
		return ville;
	}
	public void setVille(String ville)
	{
		this.ville = ville;
	}
	public String getPays()
	{
		return pays;
	}

	public void setPays(String pays)
	{
		this.pays = pays;
	}

	public String getAdresse()
	{
		return adresse;
	}
	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}
	public Date getDateNaiss()
	{
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss)
	{
		this.dateNaiss = dateNaiss;
	}

	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getHold_pwd() {
		return hold_pwd;
	}


	public void setHold_pwd(String hold_pwd) {
		this.hold_pwd = hold_pwd;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}
}

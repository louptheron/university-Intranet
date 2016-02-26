package tp_portail;

public class Utilisateur {
	private String nom;
	private String prenom;
	private String adresse;
	private String civilite;
	private String email;
	private String classe;
	private String telephone;
	private String motdepasse;
	private String typeUtilisateur;
	
	// Public Getters & Setters
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom= nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom= prenom;
	}
	public void setAdresse(String adresse) {
		this.adresse= adresse;
	}
	public String getAdresse() {
		return adresse;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	public String getTypeUtilisateur() {
		return typeUtilisateur;
	}
	public void setTypeUtilisateur(String typeUtilisateur) {
		this.typeUtilisateur = typeUtilisateur;
	}
}

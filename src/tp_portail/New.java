package tp_portail;

public class New {
	private int id;
	private String titre;
	private String description;
	private String image;
	private String date_expiration;
	private int actif;
	
	public int getId() {

		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getActif() {
		return actif;
	}
	public void setActif(int actif) {
		this.actif = actif;
	}
	public String getDate_expiration() {
		return date_expiration;
	}
	public void setDate_expiration(String date_expiration) {
		this.date_expiration = date_expiration;
	}
}

package tp_portail;

public class Enseignement {
	private int id;
	private String matiere;
	private String classe;
	private String description;
	private int nb_heures;
	private int enseignant;
	
	public int getId() {

		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatiere() {
		return matiere;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNb_heures() {
		return nb_heures;
	}
	public void setNb_heures(int nb_heures) {
		this.nb_heures = nb_heures;
	}
	public int getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(int enseignant) {
		this.enseignant = enseignant;
	}
	
}

package tp_portail;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.*;

public class UtilisateurDB {
	private Connection connexion;
	/** Fonction pour le chargement du jDBCdriver & connexion */
	private void loadDatabase() {
		// Chargement du driver
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("Errorloadingdriver: "+ e);
		}

		try{
			connexion= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/cours_j2ee", "root", "");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public Utilisateur connectionUtilisateur(String emailUser, String motdepasseUser, String typeUtilisateur) {
		Utilisateur utilisateurBean = new Utilisateur();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement= (Statement) connexion.createStatement();

			resultat= statement.executeQuery("SELECT * FROM " + typeUtilisateur + " WHERE email = " + emailUser + ";");

			if(resultat != null){
				String email = resultat.getString("email");
				String motdepasse = resultat.getString("motdepasse");
				utilisateurBean.setNom(email);
				utilisateurBean.setMotdepasse(motdepasse);

				if(utilisateurBean.getMotdepasse().equals(motdepasseUser)){
					
				}
			}
		} catch(SQLException e) {
		} finally{
			// Fermeture de la connexion
			try{
				if(resultat!= null)
					resultat.close();
				if(statement!= null)
					statement.close();
				if(connexion!= null)
					connexion.close();
			} catch(SQLException ignore) { }
		}
		return utilisateurBean;
	}
	
	public void insertionUtilisateur(Utilisateur utilisateur) {
		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement= (Statement) connexion.createStatement();

			switch(utilisateur.getTypeUtilisateur()){
			case "etudiant":
				resultat = statement.executeQuery("SELECT * FROM " + utilisateur.getTypeUtilisateur() + " WHERE nom = " + utilisateur.getNom() + " "
						+ " AND prenom = " +  utilisateur.getPrenom() + ";");
				if(resultat != null){
					statement.executeQuery("UPDATE " + utilisateur.getTypeUtilisateur() + " SET "
							+ "email = " + utilisateur.getEmail()
							+ " AND classe = " + utilisateur.getClasse()
							+ " AND adresse = " + utilisateur.getAdresse()
							+ " AND civilite = " + utilisateur.getCivilite()
							+ " AND telephone = " + utilisateur.getTelephone()
							+ " AND motdepasse = " + utilisateur.getMotdepasse()
							+ " WHERE "
							+ " prenom = " + utilisateur.getPrenom()
							+ " AND nom = " + utilisateur.getNom()
							+ ";");

				}
				else {
					statement.executeQuery("INSERT INTO " + utilisateur.getTypeUtilisateur() + " SET "
							+ "email = " + utilisateur.getEmail()
							+ " AND classe = " + utilisateur.getClasse()
							+ " AND adresse = " + utilisateur.getAdresse()
							+ " AND civilite = " + utilisateur.getCivilite()
							+ " AND telephone = " + utilisateur.getTelephone()
							+ " AND motdepasse = " + utilisateur.getMotdepasse()
							+ " AND prenom = " + utilisateur.getPrenom()
							+ " AND nom = " + utilisateur.getNom()
							+ ";");
				}
				break;
			case "enseignant":
				resultat = statement.executeQuery("SELECT * FROM " + utilisateur.getTypeUtilisateur() + " WHERE nom = " + utilisateur.getNom() + " "
						+ " AND prenom = " +  utilisateur.getPrenom() + ";");
				if(resultat != null){
					statement.executeQuery("UPDATE " + utilisateur.getTypeUtilisateur() + " SET "
							+ "email = " + utilisateur.getEmail()
							+ " AND adresse = " + utilisateur.getAdresse()
							+ " AND civilite = " + utilisateur.getCivilite()
							+ " AND telephone = " + utilisateur.getTelephone()
							+ " AND motdepasse = " + utilisateur.getMotdepasse()
							+ " WHERE "
							+ " prenom = " + utilisateur.getPrenom()
							+ " AND nom = " + utilisateur.getNom()
							+ ";");

				}
				else {
					statement.executeQuery("INSERT INTO " + utilisateur.getTypeUtilisateur() + " SET "
							+ "email = " + utilisateur.getEmail()
							+ " AND adresse = " + utilisateur.getAdresse()
							+ " AND civilite = " + utilisateur.getCivilite()
							+ " AND telephone = " + utilisateur.getTelephone()
							+ " AND motdepasse = " + utilisateur.getMotdepasse()
							+ " AND prenom = " + utilisateur.getPrenom()
							+ " AND nom = " + utilisateur.getNom()
							+ ";");
				}
				break;
			case "administrateur":
				resultat = statement.executeQuery("SELECT * FROM " + utilisateur.getTypeUtilisateur() + " WHERE nom = " + utilisateur.getNom() + " "
						+ " AND prenom = " +  utilisateur.getPrenom() + ";");
				if(resultat != null){
					statement.executeQuery("UPDATE " + utilisateur.getTypeUtilisateur() + " SET "
							+ "email = " + utilisateur.getEmail()
							+ " AND adresse = " + utilisateur.getAdresse()
							+ " AND telephone = " + utilisateur.getTelephone()
							+ " AND civilite = " + utilisateur.getCivilite()
							+ " AND motdepasse = " + utilisateur.getMotdepasse()
							+ " WHERE "
							+ " prenom = " + utilisateur.getPrenom()
							+ " AND nom = " + utilisateur.getNom()
							+ ";");

				}
				else {
					statement.executeQuery("INSERT INTO " + utilisateur.getTypeUtilisateur() + " SET "
							+ "email = " + utilisateur.getEmail()
							+ " AND adresse = " + utilisateur.getAdresse()
							+ " AND telephone = " + utilisateur.getTelephone()
							+ " AND civilite = " + utilisateur.getCivilite()
							+ " AND motdepasse = " + utilisateur.getMotdepasse()
							+ " AND prenom = " + utilisateur.getPrenom()
							+ " AND nom = " + utilisateur.getNom()
							+ ";");
				}
				break;
			}
		} catch(SQLException e) {
		} finally{
			// Fermeture de la connexion
			try{
				if(resultat!= null)
					resultat.close();
				if(statement!= null)
					statement.close();
				if(connexion!= null)
					connexion.close();
			} catch(SQLException ignore) { }
		}
	}
	
	public List<Utilisateur> recupererEtudiants() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM etudiant;");

			while(resultat.next()){
				String email = resultat.getString("email");
				System.out.print(email);

				String prenom = resultat.getString("prenom");
				String nom = resultat.getString("nom");
				String classe = resultat.getString("classe");
				String adresse = resultat.getString("adresse");
				String telephone = resultat.getString("telephone");
				String civilite = resultat.getString("civilite");
				String motdepasse = resultat.getString("motdepasse");
				System.out.println(motdepasse);
				
				Utilisateur utilisateurBean = new Utilisateur();
				utilisateurBean.setEmail(email);
				utilisateurBean.setPrenom(prenom);
				utilisateurBean.setCivilite(civilite);
				utilisateurBean.setNom(nom);
				utilisateurBean.setTelephone(telephone);
				utilisateurBean.setClasse(classe);
				utilisateurBean.setAdresse(adresse);
				utilisateurBean.setMotdepasse(motdepasse);

				System.out.print(utilisateurBean.getPrenom());
				
				utilisateurs.add(utilisateurBean);
			}
		} catch(SQLException e) {
		} finally{

			try{
				if(resultat!= null)
					resultat.close();
				if(statement!= null)
					statement.close();
				if(connexion!= null)
					connexion.close();
			} catch(SQLException ignore) { }
		}
		return utilisateurs;
	}
}

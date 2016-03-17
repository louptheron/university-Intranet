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
			
			if(!utilisateur.getTypeUtilisateur().isEmpty()){
				switch(utilisateur.getTypeUtilisateur()){
				case "etudiant":
					resultat = statement.executeQuery("SELECT * FROM " + utilisateur.getTypeUtilisateur() + " WHERE id = " + utilisateur.getId() + ";");
					if(resultat.next()){
						statement.executeUpdate("UPDATE " + utilisateur.getTypeUtilisateur() + " SET "
								+ "email = '" + utilisateur.getEmail()
								+ "' , classe = '" + utilisateur.getClasse()
								+ "' , nom = '" + utilisateur.getNom()
								+ "' , prenom = '" + utilisateur.getPrenom()
								+ "' , adresse = '" + utilisateur.getAdresse()
								+ "' , civilite = '" + utilisateur.getCivilite()
								+ "' , telephone = '" + utilisateur.getTelephone()
								+ "' , motdepasse = '" + utilisateur.getMotdepasse()
								+ "' WHERE "
								+ " id = " + utilisateur.getId()
								+ ";");

					}
					else {
						statement.executeUpdate("INSERT INTO " + utilisateur.getTypeUtilisateur() + " (email, classe, nom, prenom, adresse, civilite, telephone, motdepasse) "
								+ "VALUES ('" + utilisateur.getEmail()
								+ "' , '" + utilisateur.getClasse()
								+ "' , '" + utilisateur.getNom()
								+ "' , '" + utilisateur.getPrenom()
								+ "' , '" + utilisateur.getAdresse()
								+ "' , '" + utilisateur.getCivilite()
								+ "' , '" + utilisateur.getTelephone()
								+ "' , '" + utilisateur.getMotdepasse()
								+ "');");
					}
					break;
				case "enseignant":
					resultat = statement.executeQuery("SELECT * FROM " + utilisateur.getTypeUtilisateur() + " WHERE id = " + utilisateur.getId() + ";");
					if(resultat.next()){
						statement.executeUpdate("UPDATE " + utilisateur.getTypeUtilisateur() + " SET "
								+ "email = '" + utilisateur.getEmail()
								+ "' , nom = '" + utilisateur.getNom()
								+ "' , prenom = '" + utilisateur.getPrenom()
								+ "' , adresse = '" + utilisateur.getAdresse()
								+ "' , civilite = '" + utilisateur.getCivilite()
								+ "' , telephone = '" + utilisateur.getTelephone()
								+ "' , motdepasse = '" + utilisateur.getMotdepasse()
								+ "' WHERE "
								+ " id = '" + utilisateur.getId()
								+ "';");

					}
					else {
						statement.executeUpdate("INSERT INTO " + utilisateur.getTypeUtilisateur() + " (email, nom, prenom, adresse, civilite, telephone, motdepasse) "
								+ "VALUES ('" + utilisateur.getEmail()
								+ "' , '" + utilisateur.getNom()
								+ "' , '" + utilisateur.getPrenom()
								+ "' , '" + utilisateur.getAdresse()
								+ "' , '" + utilisateur.getCivilite()
								+ "' , '" + utilisateur.getTelephone()
								+ "' , '" + utilisateur.getMotdepasse()
								+ "');");
					}
					break;
				case "administrateur":
					resultat = statement.executeQuery("SELECT * FROM " + utilisateur.getTypeUtilisateur() + " WHERE id = " + utilisateur.getId() + ";");
					if(resultat.next()){
						statement.executeUpdate("UPDATE " + utilisateur.getTypeUtilisateur() + " SET "
								+ "email = '" + utilisateur.getEmail()
								+ "' , nom = '" + utilisateur.getNom()
								+ "' , prenom = '" + utilisateur.getPrenom()
								+ "' , adresse = '" + utilisateur.getAdresse()
								+ "' , civilite = '" + utilisateur.getCivilite()
								+ "' , telephone = '" + utilisateur.getTelephone()
								+ "' , motdepasse = '" + utilisateur.getMotdepasse()
								+ "' WHERE "
								+ " id = '" + utilisateur.getId()
								+ "';");

					}
					else {
						statement.executeUpdate("INSERT INTO " + utilisateur.getTypeUtilisateur() + " (email, nom, prenom, adresse, civilite, telephone, motdepasse) "
								+ "VALUES ('" + utilisateur.getEmail()
								+ "' , '" + utilisateur.getNom()
								+ "' , '" + utilisateur.getPrenom()
								+ "' , '" + utilisateur.getAdresse()
								+ "' , '" + utilisateur.getCivilite()
								+ "' , '" + utilisateur.getTelephone()
								+ "' , '" + utilisateur.getMotdepasse()
								+ "');");
					}
					break;
				}
			}
		
		} catch(SQLException e) {
			System.out.println(e);
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

				String prenom = resultat.getString("prenom");
				String nom = resultat.getString("nom");
				String classe = resultat.getString("classe");
				String adresse = resultat.getString("adresse");
				String telephone = resultat.getString("telephone");
				String civilite = resultat.getString("civilite");
				String motdepasse = resultat.getString("motdepasse");
				int id = resultat.getInt("id");

				Utilisateur utilisateurBean = new Utilisateur();
				utilisateurBean.setEmail(email);
				utilisateurBean.setPrenom(prenom);
				utilisateurBean.setCivilite(civilite);
				utilisateurBean.setNom(nom);
				utilisateurBean.setTelephone(telephone);
				utilisateurBean.setClasse(classe);
				utilisateurBean.setAdresse(adresse);
				utilisateurBean.setMotdepasse(motdepasse);
				utilisateurBean.setId(id);

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
	
	public List<Utilisateur> recupererEnseignants() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM enseignant;");

			while(resultat.next()){
				String email = resultat.getString("email");
				int id = resultat.getInt("id");
				String prenom = resultat.getString("prenom");
				String nom = resultat.getString("nom");
				String adresse = resultat.getString("adresse");
				String telephone = resultat.getString("telephone");
				String civilite = resultat.getString("civilite");
				String motdepasse = resultat.getString("motdepasse");
				
				System.out.print(email);

				Utilisateur utilisateurBean = new Utilisateur();
				utilisateurBean.setEmail(email);
				utilisateurBean.setPrenom(prenom);
				utilisateurBean.setCivilite(civilite);
				utilisateurBean.setNom(nom);
				utilisateurBean.setTelephone(telephone);
				utilisateurBean.setAdresse(adresse);
				utilisateurBean.setMotdepasse(motdepasse);
				utilisateurBean.setId(id);
				
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
	
	public List<Utilisateur> recupererAdministrateurs() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM administrateur;");

			while(resultat.next()){
				String email = resultat.getString("email");
				int id = resultat.getInt("id");
				String prenom = resultat.getString("prenom");
				String nom = resultat.getString("nom");
				String adresse = resultat.getString("adresse");
				String telephone = resultat.getString("telephone");
				String civilite = resultat.getString("civilite");
				String motdepasse = resultat.getString("motdepasse");
				
				System.out.print(email);

				Utilisateur utilisateurBean = new Utilisateur();
				utilisateurBean.setEmail(email);
				utilisateurBean.setPrenom(prenom);
				utilisateurBean.setCivilite(civilite);
				utilisateurBean.setNom(nom);
				utilisateurBean.setTelephone(telephone);
				utilisateurBean.setAdresse(adresse);
				utilisateurBean.setMotdepasse(motdepasse);
				utilisateurBean.setId(id);
				
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
	
	public Utilisateur recupererEtudiant(int _id) {
		Utilisateur utilisateur = new Utilisateur();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM etudiant where id=" + _id + ";");

			if(resultat.next()){
				String email = resultat.getString("email");

				String prenom = resultat.getString("prenom");
				String nom = resultat.getString("nom");
				String classe = resultat.getString("classe");
				String adresse = resultat.getString("adresse");
				String telephone = resultat.getString("telephone");
				String civilite = resultat.getString("civilite");
				String motdepasse = resultat.getString("motdepasse");
				int id = resultat.getInt("id");
 
				System.out.println(telephone);
				utilisateur.setEmail(email);
				utilisateur.setPrenom(prenom);
				utilisateur.setCivilite(civilite);
				utilisateur.setNom(nom);
				utilisateur.setTelephone(telephone);
				utilisateur.setClasse(classe);
				utilisateur.setAdresse(adresse);
				utilisateur.setMotdepasse(motdepasse);
				utilisateur.setId(id);
			}
				

		} catch(SQLException e) {
			System.out.println(e);
		} finally{

			try{
				if(resultat!= null)
					resultat.close();
				if(statement!= null)
					statement.close();
				if(connexion!= null)
					connexion.close();
			} catch(SQLException ignore) { System.out.println(ignore); }
		}
		return utilisateur;
	}
	
	public Utilisateur recupererEnseignant(int _id) {
		Utilisateur utilisateur = new Utilisateur();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM enseignant where id=" + _id + ";");

			if(resultat.next()){
				String email = resultat.getString("email");

				String prenom = resultat.getString("prenom");
				String nom = resultat.getString("nom");
				String adresse = resultat.getString("adresse");
				String telephone = resultat.getString("telephone");
				String civilite = resultat.getString("civilite");
				String motdepasse = resultat.getString("motdepasse");
				int id = resultat.getInt("id");

				utilisateur.setEmail(email);
				utilisateur.setPrenom(prenom);
				utilisateur.setCivilite(civilite);
				utilisateur.setNom(nom);
				utilisateur.setTelephone(telephone);
				utilisateur.setAdresse(adresse);
				utilisateur.setMotdepasse(motdepasse);
				utilisateur.setId(id);
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
		return utilisateur;
	}
	
	public Utilisateur recupererAdministrateur(int _id) {
		Utilisateur utilisateur = new Utilisateur();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM administrateur where id=" + _id + ";");

			if(resultat.next()){
				String email = resultat.getString("email");

				String prenom = resultat.getString("prenom");
				String nom = resultat.getString("nom");
				String adresse = resultat.getString("adresse");
				String telephone = resultat.getString("telephone");
				String civilite = resultat.getString("civilite");
				String motdepasse = resultat.getString("motdepasse");
				int id = resultat.getInt("id");

				utilisateur.setEmail(email);
				utilisateur.setPrenom(prenom);
				utilisateur.setCivilite(civilite);
				utilisateur.setNom(nom);
				utilisateur.setTelephone(telephone);
				utilisateur.setAdresse(adresse);
				utilisateur.setMotdepasse(motdepasse);
				utilisateur.setId(id);
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
		return utilisateur;
	}
	
	public boolean supprimerEtudiant(int _id) {

		loadDatabase();
		Statement statement=null;
		try{
			statement = (Statement) connexion.createStatement();
			statement.executeUpdate("DELETE FROM etudiant where id=" + _id + ";");				

		} catch(SQLException e) {
			System.out.println(e);
		} finally{

			try{
				if(statement!= null)
					statement.close();
				if(connexion!= null)
					connexion.close();
			} catch(SQLException ignore) { System.out.println(ignore); }
		}
		return true;
	}
	
	public boolean supprimerEnseignant(int _id) {

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();
			statement.executeUpdate("DELETE FROM enseignant where id=" + _id + ";");				

		} catch(SQLException e) {
			System.out.println(e);
		} finally{

			try{
				if(resultat!= null)
					resultat.close();
				if(statement!= null)
					statement.close();
				if(connexion!= null)
					connexion.close();
			} catch(SQLException ignore) { System.out.println(ignore); }
		}
		return true;
	}
	
	public boolean supprimerAdministrateur(int _id) {

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();
			statement.executeUpdate("DELETE FROM administrateur where id=" + _id + ";");				

		} catch(SQLException e) {
			System.out.println(e);
		} finally{

			try{
				if(resultat!= null)
					resultat.close();
				if(statement!= null)
					statement.close();
				if(connexion!= null)
					connexion.close();
			} catch(SQLException ignore) { System.out.println(ignore); }
		}
		return true;
	}
}

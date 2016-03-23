package tp_portail;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.*;

public class EnseignementDB {
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
			connexion= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/cours_j2ee", "root", "root");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertionNew(Enseignement enseignement) {
		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement= (Statement) connexion.createStatement();
			
					resultat = statement.executeQuery("SELECT * FROM module WHERE id = " + enseignement.getId() + ";");
					if(resultat.next()){
						statement.executeUpdate("UPDATE module SET "
								+ "matiere = '" + enseignement.getMatiere()
								+ "' , description = '" + enseignement.getDescription()
								+ "' , classe = '" + enseignement.getClasse()
								+ "' , nb_heures = '" + enseignement.getNb_heures()
								+ "' , enseignant = '" + enseignement.getEnseignant()				
								+ "' WHERE "
								+ " id = " + enseignement.getId()
								+ ";");

					}
					else {
						statement.executeUpdate("INSERT INTO module (matiere, classe, description, nb_heures, enseignant) "
								+ "VALUES ('" + enseignement.getMatiere()								
								+ "' , '" + enseignement.getClasse()
								+ "' , '" + enseignement.getDescription()
								+ "' , '" + enseignement.getNb_heures()	
								+ "' , '" + enseignement.getEnseignant()	
								+ "');");
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
	
	public List<Enseignement> recupererEnseignements() {
		List<Enseignement> enseignements = new ArrayList<Enseignement>();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM module;");

			while(resultat.next()){
				String matiere = resultat.getString("matiere");
				String classe = resultat.getString("classe");
				String description = resultat.getString("description");
				int nb_heures = resultat.getInt("nb_heures");
				int enseignant = resultat.getInt("enseignant");
				int id = resultat.getInt("id");

				Enseignement enseignement = new Enseignement();
				enseignement.setMatiere(matiere);
				enseignement.setClasse(classe);
				enseignement.setDescription(description);
				enseignement.setNb_heures(nb_heures);
				enseignement.setEnseignant(enseignant);
				enseignement.setId(id);
				
				enseignements.add(enseignement);
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
		return enseignements;
	}
	
	public Enseignement recupererEnseignement(int _id) {
		Enseignement enseignement = new Enseignement();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM module where id=" + _id + ";");

			if(resultat.next()){
				String matiere = resultat.getString("matiere");
				String classe = resultat.getString("classe");
				String description = resultat.getString("description");
				int nb_heures = resultat.getInt("nb_heures");
				int enseignant = resultat.getInt("enseignant");
				int id = resultat.getInt("id");

				enseignement.setMatiere(matiere);
				enseignement.setClasse(classe);
				enseignement.setDescription(description);
				enseignement.setNb_heures(nb_heures);
				enseignement.setEnseignant(enseignant);
				enseignement.setId(id);
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
		return enseignement;
	}
	
	public boolean supprimerEnseignement(int _id) {
		loadDatabase();
		Statement statement=null;
		try{
			statement = (Statement) connexion.createStatement();
			statement.executeUpdate("DELETE FROM module where id=" + _id + ";");				

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
	
}

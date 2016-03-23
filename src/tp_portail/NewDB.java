package tp_portail;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.*;

public class NewDB {
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

	public void insertionNew(New aNew) {
		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement= (Statement) connexion.createStatement();
			
					resultat = statement.executeQuery("SELECT * FROM new WHERE id = " + aNew.getId() + ";");
					if(resultat.next()){
						statement.executeUpdate("UPDATE new SET "
								+ "titre = '" + aNew.getTitre()
								+ "' , description = '" + aNew.getDescription()
								+ "' , date_expiration = '" + aNew.getDate_expiration()
								+ "' , image = '" + aNew.getImage()
								+ "' , actif = '" + aNew.getActif()				
								+ "' WHERE "
								+ " id = " + aNew.getId()
								+ ";");

					}
					else {
						statement.executeUpdate("INSERT INTO new (titre, description, date_expiration, image, actif) "
								+ "VALUES ('" + aNew.getTitre()
								+ "', '" + aNew.getDescription()
								+ "' , '" + aNew.getDate_expiration()
								+ "' , '" + aNew.getImage()
								+ "' , '" + aNew.getActif()	
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
	
	public List<New> recupererNews() {
		List<New> news = new ArrayList<New>();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM new;");

			while(resultat.next()){
				String titre = resultat.getString("titre");
				String description = resultat.getString("description");
				String date_expiration = resultat.getString("date_expiration");
				String image = resultat.getString("image");
				String actif = resultat.getString("actif");
				int id = resultat.getInt("id");

				New newBean = new New();
				newBean.setTitre(titre);
				newBean.setDescription(description);
				newBean.setDate_expiration(date_expiration);
				newBean.setImage(image);
				newBean.setActif(Integer.parseInt(actif));
				newBean.setId(id);
				news.add(newBean);
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
		return news;
	}
	
	public New recupererNew(int _id) {
		New newBean = new New();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM new where id=" + _id + ";");

			if(resultat.next()){
				String titre = resultat.getString("titre");
				String description = resultat.getString("description");
				String date_expiration = resultat.getString("date_expiration");
				String image = resultat.getString("image");
				String actif = resultat.getString("actif");
				int id = resultat.getInt("id");

				newBean.setTitre(titre);
				newBean.setDescription(description);
				newBean.setDate_expiration(date_expiration);
				newBean.setImage(image);
				newBean.setActif(Integer.parseInt(actif));
				newBean.setId(id);
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
		return newBean;
	}
	
	public boolean supprimerNew(int _id) {
		loadDatabase();
		Statement statement=null;
		try{
			statement = (Statement) connexion.createStatement();
			statement.executeUpdate("DELETE FROM new where id=" + _id + ";");				

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

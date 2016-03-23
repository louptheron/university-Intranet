package tp_portail;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.*;

public class NoteDB {
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

	public void insertionNote(Note note) {
		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement= (Statement) connexion.createStatement();
			
					resultat = statement.executeQuery("SELECT * FROM note WHERE id = " + note.getId() + ";");
					if(resultat.next()){
						statement.executeUpdate("UPDATE note SET "
								+ "etudiant = '" + note.getEtudiant()
								+ "' , module = '" + note.getModule()
								+ "' , note = '" + note.getNote()			
								+ "' WHERE "
								+ " id = " + note.getId()
								+ ";");

					}
					else {
						System.out.println("ok");
						statement.executeUpdate("INSERT INTO note (etudiant, module, note) "
								+ "VALUES ('" + note.getEtudiant()								
								+ "' , '" + note.getModule()
								+ "' , '" + note.getNote()
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
	
	public List<Note> recupererNotes() {
		List<Note> notes = new ArrayList<Note>();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM note;");

			while(resultat.next()){
				int etudiant = resultat.getInt("etudiant");
				int module = resultat.getInt("module");
				int note = resultat.getInt("note");
				int id = resultat.getInt("id");

				Note noteBean = new Note();
				noteBean.setEtudiant(etudiant);
				noteBean.setModule(module);
				noteBean.setNote(note);
				noteBean.setId(id);
				
				notes.add(noteBean);
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
		return notes;
	}
	
	public Note recupererNote(int _id) {
		Note noteBean = new Note();

		loadDatabase();
		Statement statement=null;
		ResultSet resultat=null;
		try{
			statement = (Statement) connexion.createStatement();

			resultat = statement.executeQuery("SELECT * FROM note where id=" + _id + ";");

			if(resultat.next()){
				int etudiant = resultat.getInt("etudiant");
				int module = resultat.getInt("module");
				int note = resultat.getInt("note");
				int id = resultat.getInt("id");

				noteBean.setEtudiant(etudiant);
				noteBean.setModule(module);
				noteBean.setNote(note);
				noteBean.setId(id);
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
		return noteBean;
	}
	
	public boolean supprimerNote(int _id) {
		loadDatabase();
		Statement statement=null;
		try{
			statement = (Statement) connexion.createStatement();
			statement.executeUpdate("DELETE FROM note where id=" + _id + ";");				

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

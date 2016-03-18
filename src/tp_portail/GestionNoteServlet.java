package tp_portail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gestionNotes")
public class GestionNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String resultat;

	public String getResultat() {
		return resultat;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoteDB noteDB = new NoteDB();
		UtilisateurDB utilisateursDB = new UtilisateurDB();
		EnseignementDB enseignementDB = new EnseignementDB();
		String supprimer = request.getParameter("supprimer");
		String modifier = request.getParameter("modifier");

		if(supprimer != null){
			int id = Integer.parseInt(supprimer);
			request.setAttribute("supprime", noteDB.supprimerNote(id));
		}
		
		if(modifier != null){
			int id = Integer.parseInt(modifier);
			request.setAttribute("note", noteDB.recupererNote(id));
		}
		
		request.setAttribute("etudiants", utilisateursDB.recupererEtudiants());	
		request.setAttribute("modules", enseignementDB.recupererEnseignements());	
		request.setAttribute("notes", noteDB.recupererNotes());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionNotes.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, String> noteSaved = new HashMap<String, String>();
		Map<String, String> erreurs = new HashMap<String, String>();

		String etudiant = request.getParameter("etudiant");
		String module = request.getParameter("module");
		String note = request.getParameter("note");
		String id = request.getParameter("id");
		
		noteSaved.put( "etudiant", etudiant );
		noteSaved.put( "module", module );
		noteSaved.put( "note", note );
		noteSaved.put( "id", id );
		request.setAttribute("note", noteSaved);

		Note noteBean = new Note();
						
		if(!id.isEmpty()){
			noteBean.setId(Integer.parseInt(id));
		}

		try{ 
			validation(etudiant, 1);
		}
		catch( Exception e ) { 
			erreurs.put( "etudiant", e.getMessage() );
		}
		noteBean.setEtudiant(Integer.parseInt(etudiant));
		
		try{ 
			validation(module, 1);
		}
		catch( Exception e ) { 
			erreurs.put( "module", e.getMessage() );
		}
		noteBean.setModule(Integer.parseInt(module));
		
		try{ 
			validation(note, 1);
		}
		catch( Exception e ) { 
			erreurs.put( "note", e.getMessage() );
		}
		noteBean.setNote(Integer.parseInt(note));
		
		if( erreurs.isEmpty() ) {
			NoteDB noteDB = new NoteDB();
			noteDB.insertionNote(noteBean);
			resultat= "insertion ok.";
		} else{ resultat= "insertion echec";}
				
		request.setAttribute("resultat", getResultat());
		request.setAttribute("erreurs", erreurs);
		
		doGet(request, response);
	}
	
	private void validation(String input, int len) throws Exception {
		if(input == null || input.trim().equals("") || input.length()<len)
		{ throw new Exception( "champ invalide");}
	}
	

}

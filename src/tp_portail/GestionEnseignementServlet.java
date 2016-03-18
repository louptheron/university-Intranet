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


@WebServlet("/gestionEnseignements")
public class GestionEnseignementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String resultat;

	public String getResultat() {
		return resultat;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionEnseignementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnseignementDB enseignementDB = new EnseignementDB();
		UtilisateurDB utilisateursDB = new UtilisateurDB();
		String supprimer = request.getParameter("supprimer");
		String modifier = request.getParameter("modifier");

		if(supprimer != null){
			int id = Integer.parseInt(supprimer);
			request.setAttribute("supprime", enseignementDB.supprimerEnseignement(id));
		}
		
		if(modifier != null){
			int id = Integer.parseInt(modifier);
			request.setAttribute("enseignement", enseignementDB.recupererEnseignement(id));
		}
		
		request.setAttribute("enseignants", utilisateursDB.recupererEnseignants());	
		request.setAttribute("enseignements", enseignementDB.recupererEnseignements());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionEnseignements.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, String> enseignenemt = new HashMap<String, String>();
		Map<String, String> erreurs = new HashMap<String, String>();

		String matiere = request.getParameter("matiere");
		String classe = request.getParameter("classe");
		String description = request.getParameter("description");
		String nb_heures = request.getParameter("nb_heures");
		String enseignant = request.getParameter("enseignant");
		String id = request.getParameter("id");
		
		enseignenemt.put( "matiere", matiere );
		enseignenemt.put( "description", description );
		enseignenemt.put( "classe", classe );
		enseignenemt.put( "nb_heures", nb_heures );
		enseignenemt.put( "enseignant", enseignant );
		enseignenemt.put( "id", id );

		Enseignement enseignementBean = new Enseignement();
						
		if(!id.isEmpty()){
			enseignementBean.setId(Integer.parseInt(id));
		}

		try{ 
			validation(matiere, 2);
		}
		catch( Exception e ) { 
			erreurs.put( "matiere", e.getMessage() );
		}
		enseignementBean.setMatiere(matiere);
		
		try{ 
			validation(description, 2);
		}
		catch( Exception e ) { 
			erreurs.put( "description", e.getMessage() );
		}
		enseignementBean.setDescription(description);
		
		try{ 
			validation(matiere, 2);
		}
		catch( Exception e ) { 
			erreurs.put( "matiere", e.getMessage() );
		}
		enseignementBean.setMatiere(matiere);
		
		try{ 
			validation(classe, 1);
		}
		catch( Exception e ) { 
			erreurs.put( "classe", e.getMessage() );
		}
		enseignementBean.setClasse(classe);

		try{ 
			validation(nb_heures, 1);
		}
		catch( Exception e ) { 
			erreurs.put( "nb_heures", e.getMessage() );
		}
		enseignementBean.setNb_heures(Integer.parseInt(nb_heures));
		
		try{ 
			validation(enseignant, 1);
		}
		catch( Exception e ) { 
			erreurs.put( "enseignant", e.getMessage() );
		}
		enseignementBean.setEnseignant(Integer.parseInt(enseignant));
		
		if( erreurs.isEmpty() ) {
			EnseignementDB enseignementDB = new EnseignementDB();
			enseignementDB.insertionNew(enseignementBean);
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

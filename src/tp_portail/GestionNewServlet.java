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


@WebServlet("/gestionNews")
public class GestionNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String resultat;

	public String getResultat() {
		return resultat;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewDB newDB = new NewDB();
		String supprimer = request.getParameter("supprimer");
		String modifier = request.getParameter("modifier");


		if(supprimer != null){
			int id = Integer.parseInt(supprimer);
			request.setAttribute("supprime", newDB.supprimerNew(id));
		}
		
		if(modifier != null){
			int id = Integer.parseInt(modifier);
			request.setAttribute("aNew", newDB.recupererNew(id));
		}
		
		request.setAttribute("news", newDB.recupererNews());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionNews.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, String> aNew = new HashMap<String, String>();
		Map<String, String> erreurs = new HashMap<String, String>();

		String titre = request.getParameter("titre");
		String description= request.getParameter("description");
		String image = request.getParameter("image");
		String date_expiration = request.getParameter("date_expiration");
		String actif = request.getParameter("actif");
		String id = request.getParameter("id");

		aNew.put( "titre", titre );
		aNew.put( "description", description );
		aNew.put( "image", image );
		aNew.put( "date_expiration", date_expiration );
		aNew.put( "actif", actif );
		aNew.put( "id", id );

		New newBean = new New();
						
		if(!request.getParameter("id").isEmpty()){
			newBean.setId(Integer.parseInt(request.getParameter("id")));
		}

		try{ 
			validation(titre, 2);
		}
		catch( Exception e ) { 
			erreurs.put( "titre", e.getMessage() );

		}
		newBean.setTitre(titre);
		
		try{ 
			validation(description, 2);
		}
		catch( Exception e ) { 
			erreurs.put( "description", e.getMessage() );
		}
		newBean.setDescription(description);
		
		try{ 
			validation(image, 2);
		}
		catch( Exception e ) { 
			erreurs.put( "image", e.getMessage() );
		}
		newBean.setImage(image);
		
		try{ 
			validation(date_expiration, 2);
		}
		catch( Exception e ) { 
			erreurs.put( "date_expiration", e.getMessage() );
		}
		newBean.setDate_expiration(date_expiration);

		try{ 
			validation(actif, 1);
		}
		catch( Exception e ) { 
			erreurs.put( "actif", e.getMessage() );
		}
		newBean.setActif(Integer.parseInt(actif));

		
		if( erreurs.isEmpty() ) {
			NewDB newDB = new NewDB();
			newDB.insertionNew(newBean);
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

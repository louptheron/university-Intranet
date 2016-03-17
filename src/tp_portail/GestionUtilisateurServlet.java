package tp_portail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gestionUtilisateurs")
public class GestionUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionUtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurDB utilisateurs = new UtilisateurDB();
		String supprimer = request.getParameter("supprimer");
		String req_type = request.getParameter("type");

		if(supprimer != null){
			int id = Integer.parseInt(supprimer);
			if(req_type.equals("etudiant")){
				request.setAttribute("supprime", utilisateurs.supprimerEtudiant(id));
			}
			else if (req_type.equals("enseignant")){
				request.setAttribute("supprime", utilisateurs.supprimerEnseignant(id));
			}
			else if (req_type.equals("administateur")){
				request.setAttribute("supprime", utilisateurs.supprimerAdministrateur(id));
			}
		}
			
		UtilisateurDB utilisateursDB = new UtilisateurDB();
		request.setAttribute("etudiants", utilisateursDB.recupererEtudiants());	
		request.setAttribute("enseignants", utilisateursDB.recupererEnseignants());	
		request.setAttribute("administrateurs", utilisateursDB.recupererAdministrateurs());		



		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionUtilisateurs.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

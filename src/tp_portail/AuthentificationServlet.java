package tp_portail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthentificationServlet
 */
@WebServlet("/authentification")
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthentificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Authentification auth = new Authentification();
		auth.authentificationTest( request );
		if(auth.getResultat().equals("ok")){
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", auth.getUtilisteur());
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			request.setAttribute("profil", auth.getUtilisteur().getTypeUtilisateur());
			request.setAttribute("utilisateur", auth.getUtilisteur());
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}
		else{
			request.setAttribute( "resultVerif", auth);
			this.getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp").forward(request, response);
		}
	}
}

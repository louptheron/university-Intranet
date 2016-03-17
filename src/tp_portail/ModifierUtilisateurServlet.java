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


@WebServlet("/modifierUtilisateur")
public class ModifierUtilisateurServlet extends HttpServlet {
	private String resultat;

	public String getResultat() {
		return resultat;
	}
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierUtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurDB utilisateursDB = new UtilisateurDB();
		String req_id = request.getParameter("id");
		String req_type = request.getParameter("type");

		System.out.println(req_type);
		System.out.println(req_id);

		if(req_id != null && req_type != null){
			int id = Integer.parseInt(req_id);
			if(req_type.equals("etudiant")){
				request.setAttribute("utilisateur", utilisateursDB.recupererEtudiant(id));
				request.setAttribute("typeUtilisateur", "etudiant");

			}
			else if (req_type.equals("enseignant")){
				request.setAttribute("utilisateur", utilisateursDB.recupererEnseignant(id));
				request.setAttribute("typeUtilisateur", "enseignant");	
			}
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierUtilisateur.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Map<String, String> utilisateur= new HashMap<String, String>();
		 Map<String, String> erreurs= new HashMap<String, String>();

		String email = request.getParameter("email");
		String prenom= request.getParameter("prenom");
		String civilite = request.getParameter("civilite");
		String nom = request.getParameter("nom");
		String classe = request.getParameter("classe");
		String adresse = request.getParameter("adresse");
		String motdepasse = request.getParameter("motdepasse");
		String confirmationMotdepasse = request.getParameter("confirmationMotdepasse");
		String telephone = request.getParameter("telephone");
		String typeUtilisateur = request.getParameter("typeUtilisateur");

		utilisateur.put( "adresse", adresse );
		utilisateur.put( "prenom", prenom );
		utilisateur.put( "telephone", telephone );
		utilisateur.put( "nom", nom );
		utilisateur.put( "email", email );
		utilisateur.put( "classe", classe );
		utilisateur.put( "motdepasse", motdepasse );
		utilisateur.put( "confirmationMotdepasse", confirmationMotdepasse );
		utilisateur.put( "typeUtilisateur", typeUtilisateur );
		request.setAttribute("utilisateur", utilisateur);

		Utilisateur utilisateurBean = new Utilisateur();
				
		utilisateurBean.setCivilite(civilite);
		utilisateurBean.setTypeUtilisateur(typeUtilisateur);

		
		if(!request.getParameter("id").isEmpty()){
			utilisateurBean.setId(Integer.parseInt(request.getParameter("id")));
		}

		if(!adresse.isEmpty()){

			try{ 
				validation(adresse, 10);
			}
			catch( Exception e ) { 
				erreurs.put( "adresse", e.getMessage() );
			}
			utilisateurBean.setAdresse(adresse);
		}
		
		if(!telephone.isEmpty()){
			try{ 
				validation(telephone, 8);
			}
			catch( Exception e ) { 
				erreurs.put( "telephone", e.getMessage() );

			}
			utilisateurBean.setTelephone(telephone);
		}
		
		try{ 
			validation(nom, 2);
		}
		catch( Exception e ) { 
			erreurs.put( "nom", e.getMessage() );

		}
		utilisateurBean.setNom(nom);
		
		try{ 
			isValidEmailAddress(email);
		}
		catch( Exception e ) { 
			erreurs.put( "email", e.getMessage() );
		}
		utilisateurBean.setEmail(email);
		
		try{ 
			validation(prenom, 2);
		}
		catch( Exception e ) { 
			erreurs.put( "prenom", e.getMessage() );
		}
		utilisateurBean.setPrenom(prenom);
		
		if(typeUtilisateur.equals("etudiant")){
			try{ 
				validation(classe, 1);
			}
			catch( Exception e ) { 
				erreurs.put( "classe", e.getMessage() );
			}
			utilisateurBean.setClasse(classe);
		}
		
		try{ 
			validation(motdepasse, 8);
		}
		catch( Exception e ) { 
			erreurs.put( "motdepasse", e.getMessage() );
		}
		utilisateurBean.setMotdepasse(motdepasse);
		
		try{ 
			validation(confirmationMotdepasse, 8);
		}
		catch( Exception e ) { 
			erreurs.put( "confirmationMotdepasse", e.getMessage() );
		}
		
		try{ 
			validationMdp(motdepasse, confirmationMotdepasse);
		}
		catch( Exception e ) { 
			erreurs.put( "conf", e.getMessage() );
		}
		utilisateurBean.setMotdepasse(motdepasse);

		
		if( erreurs.isEmpty() ) {
			UtilisateurDB utilisateurDB = new UtilisateurDB();
			utilisateurDB.insertionUtilisateur(utilisateurBean);
			resultat= "insertion ok.";
		} else{ resultat= "insertion echec";}
		

				// TODO Auto-generated method stub
		
		request.setAttribute("resultat", getResultat());
		request.setAttribute("erreurs", erreurs);
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierUtilisateur.jsp").forward(request, response);

	}
	
	private void validation(String input, int len) throws Exception {
		if(input == null || input.trim().equals("") || input.length()<len)
		{ throw new Exception( "champ invalide");}
	}
	
	private void isValidEmailAddress(String email) throws Exception{
		String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Matcher matcher;
		
		matcher = Pattern.compile(EMAIL_PATTERN).matcher(email);
		if(matcher.matches()){
			throw new Exception( "email invalide");
		}
	}
	
	private void validationMdp( String mdp, String confirmation) throws Exception {
		if(mdp == null|| mdp.trim().equals("") ||confirmation== null|| confirmation.trim().equals("") ) {
			throw new Exception( "Merci de saisir et confirmer votre mot de passe.");}
		else{
			if( ! mdp.equals( confirmation) ) {
				throw new Exception( "Les mots de passes, merci de les saisir à nouveau.");
			} else if( mdp.length() < 7 ) {
				throw new Exception( "Le mot de passe doit contenir 8 charactères");
			}
		}
	}

}

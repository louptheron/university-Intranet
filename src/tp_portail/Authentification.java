package tp_portail;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Authentification {
	private String resultat;
	private Utilisateur utilisateur;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public Map<String, String> getErreurs(){
		return erreurs;
	}
	public String getResultat() {
		return resultat;
	}
	public Utilisateur getUtilisteur() {
		return utilisateur;
	}
	
	public void authentificationTest( HttpServletRequest request ){
		String login= request.getParameter("login");
		String motdepasse = request.getParameter("motPasse");
		String typeUtilisateur = request.getParameter("profil");
		UtilisateurDB utilisateurDB = new UtilisateurDB ();
		
		
		try{ 
			validationLogin(login);
		}
		catch( Exception e ) { 
			erreurs.put( "login", e.getMessage() );
		}
		try{ 
			validationMotPasse(motdepasse);
		}
		catch( Exception e ) { 
			erreurs.put( "motPasse", e.getMessage() );
		}
		if( erreurs.isEmpty() ) {
			if(typeUtilisateur != null )
				utilisateur= utilisateurDB.connectionUtilisateur(login, typeUtilisateur);
			else
				utilisateur=null;
			try{ 
				verificationLogin(getUtilisteur());
			}
			catch( Exception e ) { 
				erreurs.put( "verif", e.getMessage() );
			}
			if( erreurs.isEmpty() ){
				try{ 
					verificationMDP(motdepasse);
				}
				catch( Exception e ) { 
					erreurs.put( "verif", e.getMessage() );
				}
				if( erreurs.isEmpty() ){
					resultat = "ok";
				}else {resultat = "Échec.";}
			}else {resultat = "Échec.";}
		} else{resultat = "Échec.";}
	}
	
	//Fonction pour la vérification du login saisi
		private void validationLogin(String login) throws Exception {
			if (login == null || login.trim().equals("")){ 
				throw new Exception( "Le champ login est requis" );
			}
		}
		//Fonction de Vérification du mot de passe saisie
		private void validationMotPasse( String motPasse ) throws Exception {
			if(motPasse == null || motPasse.trim().equals("")) {
				throw new Exception( "Le champ mot de passe est requis" ); 
			}
		}
		
		private void verificationLogin( Utilisateur utilisateur ) throws Exception {
			if( utilisateur==null ) {
				throw new Exception( "Aucun compte associé à ce login. Veuillez contacter votre administrateur" ); 
			}
		}
		
		private void verificationMDP( String motdepasse) throws Exception {
			if(! motdepasse.equals( utilisateur.getMotdepasse())){
				throw new Exception( "Votre mot de passe est incorrect" );
			}
		}
}


package tp_portail;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class GestionUtilisateur {
	private String resultat;
	private Map<String, String> erreurs= new HashMap<String, String>();
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	public String getResultat() {
		return resultat;
	}
	public void creerUtilisateur(HttpServletRequest request) {

		String email = request.getParameter("email");
		String prenom= request.getParameter("prenom");
		String civilite = request.getParameter("civilite");
		String nom = request.getParameter("nom");
		String classe = request.getParameter("classe");
		String adresse = request.getParameter("adresse");
		String motdepasse = request.getParameter("motdepasse");
		String confirmationMotdepasse = request.getParameter("confirmationMotdepasse");
		String telephone = request.getParameter("telephone");
		
		Utilisateur utilisateurBean = new Utilisateur();
				
		utilisateurBean.setCivilite(civilite);
		utilisateurBean.setAdresse(adresse);
		utilisateurBean.setTelephone(telephone);

		try{ 
			validation(email);
		}
		catch( Exception e ) { 
			erreurs.put( "nom", e.getMessage() );
		}
		utilisateurBean.setNom(nom);
		
		try{ 
			validation(email);
		}
		catch( Exception e ) { 
			erreurs.put( "email", e.getMessage() );
		}
		utilisateurBean.setEmail(email);
		
		try{ 
			validation(prenom);
		}
		catch( Exception e ) { 
			erreurs.put( "prenom", e.getMessage() );
		}
		utilisateurBean.setPrenom(prenom);
		
		try{ 
			validation(classe);
		}
		catch( Exception e ) { 
			erreurs.put( "classe", e.getMessage() );
		}
		utilisateurBean.setClasse(classe);
		
		try{ 
			validation(motdepasse);
		}
		catch( Exception e ) { 
			erreurs.put( "motdepasse", e.getMessage() );
		}
		utilisateurBean.setMotdepasse(motdepasse);
		
		try{ 
			validation(confirmationMotdepasse);
		}
		catch( Exception e ) { 
			erreurs.put( "confirmationMotdepasse", e.getMessage() );
		}
		
		try{ 
			validationMdp(motdepasse, confirmationMotdepasse);
		}
		catch( Exception e ) { 
			erreurs.put( "motdepasse et confirmation", e.getMessage() );
		}
		utilisateurBean.setMotdepasse(motdepasse);

		
		if( erreurs.isEmpty() ) {
			resultat= "insertion ok.";
		} else{ resultat= "insertion echec";}
		
		UtilisateurDB utilisateurDB = new UtilisateurDB();
		utilisateurDB.insertionUtilisateur(utilisateurBean);
	}

	private void validation(String input) throws Exception {
		if(input == null|| input.trim().equals(""))
		{ throw new Exception( "champ invalide");}
	}
	
	private void validationMdp( String mdp, String confirmation) throws Exception {
		if(mdp == null|| mdp.trim().equals("") ||confirmation== null|| confirmation.trim().equals("") ) {
			throw new Exception( "Merci de saisir et confirmer votre num�ro de carte bancaire.");}
		else{
			if( ! mdp.equals( confirmation) ) {
				throw new Exception( "Les num�ros de carte sont diff�rents, merci de les saisir � nouveau.");
			} else if( mdp.length() < 10 ) {
				throw new Exception( "Le numerode carte doit contenir 10 chiffres");
			}
		}
	}
	
}
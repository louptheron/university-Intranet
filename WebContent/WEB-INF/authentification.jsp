<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page d'authentification</title>
</head>
<body>
<h1>Authentification</h1>

<c:out value="${resultVerif.resultat}" />
<c:out value="${resultVerif.erreurs['verif']}" />

<form method="post" action="authentification">
<p>
Login : <input type="text" name="login"><c:out value="${resultVerif.erreurs['login']}" /><br>
Mot de passe : <input type="password" name="motPasse"><c:out value="${resultVerif.erreurs['motPasse']}" />
</p>
<p>
Votre profil:<br>
<input type="radio" name="profil" value="administrateur"> Administrateur<br>
<input type="radio" name="profil" value="etudiant" > Etudiant<br>
<input type="radio" name="profil" value="enseignant" > Enseignant<br>
</p>
<p><input type="submit" name="Submit" value="valider"></p>
</form>
</body>
</html>
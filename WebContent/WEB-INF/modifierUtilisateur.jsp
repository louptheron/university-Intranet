	<%@ include file="header.jsp" %>

<h2>Ajouter/Modifier un utilisateur</h2>

<c:out value="${resultat}" /><br>
<c:out value="${erreurs.conf}" /><br>

<form action="modifierUtilisateur" method="post">
  Nom : 
  <input required type="text" name="nom" value="<c:out value="${utilisateur.nom}" />"><c:out value="${erreurs.nom}" />
  
  <br>
  Prenom : 
  <input required type="text" name="prenom" value="<c:out value="${utilisateur.prenom}" />"> <c:out value="${erreurs.prenom}" /><br>
  Adresse : 
  <input type="text" name="adresse" value="<c:out value="${utilisateur.adresse}" />"><c:out value="${erreurs.adresse}" />
  
  <br>
  civilite : 
<select name="civilite"> <c:out value="${erreurs.civilite}" />
 	 <option value="Mr" <c:if test="${utilisateur.civilite == 'Mr'}" >selected</c:if>>Mr</option>
 	 <option value="Miss" <c:if test="${utilisateur.civilite == 'Miss'}" >selected</c:if>>Miss</option>
 	 <option value="Mrs" <c:if test="${utilisateur.civilite == 'Mrs'}" >selected</c:if>>Mrs</option>
	</select>   <br>  
  <br>
  classe (seulement si etudiant): <c:out value="${erreurs.classe}" />
<select name="classe">
 	 <option value="3CB" <c:if test="${utilisateur.classe == '3CB'}" >selected</c:if>>3CB</option>
 	 <option value="3CI" <c:if test="${utilisateur.classe == '3CI'}" >selected</c:if>>3CI</option>
 	 <option value="3CTC" <c:if test="${utilisateur.classe == '3CTC'}" >selected</c:if>>3CTC</option>
	</select>   <br>
  email : 
  <input required type="email" name="email" value="<c:out value="${utilisateur.email}" />"> <c:out value="${erreurs.email}" />
     <br>
  telephone : 
  <input type="text" name="telephone" value="<c:out value="${utilisateur.telephone}" />">  <c:out value="${erreurs.telephone}" />
    <br>
  mot de passe : 
  <input type="password" name="motdepasse" value="<c:out value="${utilisateur.motdepasse}" />"> <c:out value="${erreurs.motdepasse}" />
     <br>
  Confirmation  : 
  <input type="password" name="confirmationMotdepasse" value="<c:out value="${utilisateur.motdepasse}" />"> <c:out value="${erreurs.confirmationMotdepasse}" />
     <br>
    <input type="hidden" name="id" value="<c:out value="${utilisateur.id}" />">
  type utilisateur : 
   <select name="typeUtilisateur">
 	 <option value="etudiant" <c:if test="${typeUtilisateur == 'etudiant'}" >selected</c:if>>etudiant</option>
 	 <option value="enseignant" <c:if test="${typeUtilisateur == 'enseignant'}" >selected</c:if>>enseignant</option>
 	 <option value="administrateur" <c:if test="${typeUtilisateur == 'administrateur'}" >selected</c:if>>administrateur</option>
	</select>
    <br><br>
  <input type="submit" value="Submit">
</form> 

<a href="gestionUtilisateurs">retour à la gestion des utilisateurs</a>
	<%@ include file="footer.jsp" %>

</body>
</html>
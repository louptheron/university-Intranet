	<%@ include file="header.jsp" %>

<h2>Gestion des utilisateurs</h2>
<table>
	<tr>
		<th>Etudiants</th>	
		<th>Enseignants</th>	
		<th>Administrateurs</th>	
	</tr>
	<tr>
		<th>
			<ul>
				<c:forEach var="etudiant" items="${etudiants}">
					<li><c:out value="${etudiant.nom}" /> (<a href='modifierUtilisateur?type=etudiant&id=<c:out value="${etudiant.id}" />'>modifier</a> | <a href='gestionUtilisateurs?type=etudiant&supprimer=<c:out value="${etudiant.id}" />'>supprimer</a>)</li>
				</c:forEach>
			</ul>
		</th>
		<th>
			<ul>
				<c:forEach var="enseignant" items="${enseignants}">
					<li><c:out value="${enseignant.nom}" /> (<a href='modifierUtilisateur?type=enseignant&id=<c:out value="${enseignant.id}" />'>modifier</a> | <a href='gestionUtilisateurs?type=enseignant&supprimer=<c:out value="${enseignant.id}" />'>supprimer</a>)</li>
				</c:forEach>
			</ul>
		</th>
		<th>
			<ul>
				<c:forEach var="administrateur" items="${administrateurs}">
					<li><c:out value="${administrateur.nom}" /> (<a href='modifierUtilisateur?type=administrateur&id=<c:out value="${administrateur.id}" />'>modifier</a> | <a href='gestionUtilisateurs?type=administrateur&supprimer=<c:out value="${administrateur.id}" />'>supprimer</a>)</li>
				</c:forEach>
			</ul>
		</th>
	</tr>
</table>
<a href="modifierUtilisateur">Ajouter un utilisateur</a>


	<%@ include file="footer.jsp" %>

</body>
</html>
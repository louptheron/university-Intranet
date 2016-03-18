
<%@ include file="header.jsp"%>

<h2>Gestion des Enseignements</h2>

<div id="left">

	<c:out value="${resultat}" />
	<br>

	<form action="gestionEnseignements" method="post">
		Matiere : <input required type="text" name="matiere"
			value="<c:out value="${enseignement.matiere}" />">
		<c:out value="${erreurs.matiere}" />
		<br> Classe : <select name="classe">
		<option value="3CB" <c:if test="${enseignement.classe == '3CB'}" >selected</c:if>>3CB</option>
 		 <option value="3CI" <c:if test="${enseignement.classe == '3CI'}" >selected</c:if>>3CI</option>
 		 <option value="3CTC" <c:if test="${enseignement.classe == '3CTC'}" >selected</c:if>>3CTC</option>
		</select> <br>
		
		<br> Description : <input required type="text" name="description"
			value="<c:out value="${enseignement.description}" />">
		<c:out value="${erreurs.description}" />
		<br> Nombre d'heures : <input type="text"
			name="nb_heures"
			value="<c:out value="${enseignement.nb_heures}" />">
		<c:out value="${erreurs.nb_heures}" />
		<br> Enseignant : 
		<select name="enseignant">
		<c:forEach var="enseignant" items="${enseignants}">
			<option value="<c:out value="${enseignant.id}" />" <c:if test="${enseignement.enseignant == enseignant.id}" >selected</c:if>><c:out value="${enseignant.nom}" /></option>
		</c:forEach>
		</select> <br>
		<br> <input type="hidden" name="id"
			value="<c:out value="${enseignement.id}" />"> 
			<input type="submit" value="Submit">
	</form>
</div>

<div id="right">
	<div class="box">
		<h2>Enseignements</h2>

		<table>
			<tr>
				<th>Matiere</th>
				<th>CLasse</th>
				<th>Action</th>
			
				
			</tr>
			<tr>
				<th>
					<ul>
						<c:forEach var="enseignement" items="${enseignements}">
							<li><c:out value="${enseignement.matiere}" /> </li>
						</c:forEach>
					</ul>
				</th>
				<th>
					<ul>
						<c:forEach var="enseignement" items="${enseignements}">
							<li><c:out value="${enseignement.classe}" /></li>
						</c:forEach>
					</ul>
				</th>
				<th>
					<ul>
						<c:forEach var="enseignement" items="${enseignements}">
							<li><a href='gestionEnseignements?modifier=<c:out value="${enseignement.id}" />'>modifier</a>
								| <a href='gestionEnseignements?supprimer=<c:out value="${enseignement.id}" />'>supprimer</a></li>
						</c:forEach>
					</ul>
				</th>
			</tr>
		</table>
<a href="gestionEnseignements">Ajouter un enseignement</a>

	</div>
</div>

<%@ include file="footer.jsp"%>
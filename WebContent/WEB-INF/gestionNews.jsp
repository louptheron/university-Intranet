
<%@ include file="header.jsp"%>

<h2>Gestion des News</h2>

<div id="left">

	<c:out value="${resultat}" />
	<br>

	<form action="gestionNews" method="post">
		Titre : <input required type="text" name="titre"
			value="<c:out value="${aNew.titre}" />">
		<c:out value="${erreurs.titre}" />

		<br> Description : <input required type="text" name="description"
			value="<c:out value="${aNew.description}" />">
		<c:out value="${erreurs.description}" />
		<br> Image : <input type="text" name="image"
			value="<c:out value="${aNew.image}" />">
		<c:out value="${erreurs.image}" />

		<br> Date d'expiration : <input type="text"
			name="date_expiration"
			value="<c:out value="${aNew.date_expiration}" />">
		<c:out value="${erreurs.date_expiration}" />
		<br> Actif : <select name="actif">
			<option value="1" <c:if test="${aNew.actif == '1'}" >selected</c:if>>Oui</option>
			<option value="0" <c:if test="${aNew.actif == '0'}" >selected</c:if>>Non</option>
		</select> <br>
		<br> <input type="hidden" name="id"
			value="<c:out value="${aNew.id}" />"> <input type="submit"
			value="Submit">
	</form>
</div>

<div id="right">
	<div class="box">
		<h2>News</h2>

		<table>
			<tr>
				<th>News</th>
			</tr>
			<tr>
				<th>
					<ul>
						<c:forEach var="aNew" items="${news}">
							<li><c:out value="${aNew.titre}" /> (<a
								href='gestionNews?modifier=<c:out value="${aNew.id}" />'>modifier</a>
								| <a href='gestionNews?supprimer=<c:out value="${aNew.id}" />'>supprimer</a>)</li>
						</c:forEach>
					</ul>
				</th>
			</tr>
		</table>
<a href="gestionNews">Ajouter une new</a>

	</div>
</div>

<%@ include file="footer.jsp"%>

<%@ include file="header.jsp"%>

<h2>Gestion des Notes</h2>

<div id="left">

	<c:out value="${resultat}" />
	<br>

	<form action="gestionNotes" method="post">
		<br> Etudiant : 
		<select name="etudiant">
		<c:forEach var="etudiant" items="${etudiants}">
			<option value="<c:out value="${etudiant.id}" />" <c:if test="${note.etudiant == etudiant.id}" >selected</c:if>><c:out value="${etudiant.nom}" /></option>
		</c:forEach>
		</select> <br>
		<c:out value="${erreurs.etudiant}" />
		
		<br> Module : 
		<select name="module">
		<c:forEach var="module" items="${modules}">
			<option value="<c:out value="${module.id}" />" <c:if test="${note.module == module.id}" >selected</c:if>><c:out value="${module.matiere}" /> - <c:out value="${module.classe}" /></option>
		</c:forEach>
		</select> <br>
		<c:out value="${erreurs.module}" />
		
		Note : <input required type="text" name="note"
			value="<c:out value="${note.note}" />">
		<c:out value="${erreurs.note}" />
		<br> <input type="hidden" name="id"
			value="<c:out value="${note.id}" />"> 
			
			<input type="submit" value="Submit">
	</form>
</div>

<div id="right">
	<div class="box">
		<h2>Notes</h2>
		<table>
			<tr>
				<th>Etudiant</th>
				<th>Module</th>
				<th>Note</th>
			</tr>
			<tr>
				<th>
					<ul>
						<c:forEach var="note" items="${notes}">
							<c:forEach var="etudiant" items="${etudiants}">
								<c:if test="${note.etudiant == etudiant.id}"><li><c:out value="${etudiant.nom}" /></li></c:if>
							</c:forEach>
						</c:forEach>
					</ul>
				</th>
				<th>
					<ul>
						<c:forEach var="note" items="${notes}">
							<c:forEach var="module" items="${modules}">
								<c:if test="${note.module == module.id}"><li><c:out value="${module.matiere}" /> - <c:out value="${module.classe}" /></li></c:if>
							</c:forEach>
						</c:forEach>
					</ul>
				</th>
				<th>
					<ul>
						<c:forEach var="note" items="${notes}">
								<li><c:out value="${note.note}" /></li>
						</c:forEach>
					</ul>
				</th>
			</tr>
		</table>
<a href="gestionNotes">Ajouter une note</a>

	</div>
</div>

<%@ include file="footer.jsp"%>
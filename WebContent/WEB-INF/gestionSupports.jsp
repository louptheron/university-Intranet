
<%@ include file="header.jsp"%>

<h2>Supports de Cours</h2>

		<h3><c:out value="${upload}" /></h3></br>


<form action="gestionSupports" method="post" enctype="multipart/form-data">
Fichier :
	<br/>
	<input type="file" size="50" name="file">
	
	<br> Module : 
		<select name="path">
		<c:forEach var="module" items="${modules}">
			<option value="<c:out value="${module.classe}" />/<c:out value="${module.matiere}" />" ><c:out value="${module.matiere}" /> - <c:out value="${module.classe}" /></option>
		</c:forEach>
		</select> <br>
		<c:out value="${erreurs.module}" />
	<br/>
	
	<input type="submit" value="Upload">
	</form>

<%@ include file="footer.jsp"%>
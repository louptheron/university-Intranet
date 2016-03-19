
<%@ include file="header.jsp"%>

<h2>News</h2>

<table>
			<tr>
				<th>Titre</th>
				<th>Description</th>
				<th>Image</th>
				
			</tr>
			<c:forEach var="aNew" items="${news}">
				<tr>
					<th><c:out value="${aNew.titre}" /></th>
					<th><c:out value="${aNew.description}" /></th>
					<th><c:out value="${aNew.image}" /></th>
				</tr>
			</c:forEach>
		</table>

<%@ include file="footer.jsp"%>
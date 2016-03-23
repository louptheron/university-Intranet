
<%@ include file="header.jsp"%>

<h2>Accueil</h2>

<div id="left">

</div>

<div id="right">
	<div class="box">
		<h2>News</h2>
		<c:if test="${!empty sessionScope.utilisateur}">
			<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
			<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.utilisateur.email}</p>
        </c:if>
	</div>
</div>

<%@ include file="footer.jsp"%>
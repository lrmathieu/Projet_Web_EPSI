<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acceuil</title>
</head>
<body>

	<nav>
		<ul>
			<li><a href="<c:url value="/createAccount"/>">Créer un nouveau compte</a></li>
		</ul>
	</nav>
	
	<section>
		<br><br>
		<h2>Liste des comptes</h2>
		<table border="1">
			<tr>
				<th>Nom du compte</th>
				<th>Numéro de compte</th>
				<th>Solde actuel</th>
				<th colspan="2">Action</th>
			</tr>
			<c:choose>
				<c:when test="${ empty accountsList }">
					<tr>
						<td colspan="5">Aucune opération</td>
					</tr>
				</c:when>
				<c:when test="${ not empty accountsList }">
					<c:forEach items="${ accountsList }" var="a">
						<tr>
							<td>${ a.name }<br>
							<td>${ a.number }</td>
							<td>${ a.balanceAmount }</td>
							<td><a
								href="<c:url value="/account?accountNumber=${a.number}"/>">Consulter</a>
							</td>
							<td><a href="<c:url value="/home"/>">Supprimer</a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>

		</table>
	</section>

</body>
</html>
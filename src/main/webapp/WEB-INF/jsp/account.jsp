<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon compte</title>
</head>
<body>
	<h2>Details du compte</h2>

	<section>
		<c:out value="${account.name}"/><br/><br/>
		
	</section>
	<table border="1">
		<tr>
			<th>Date</th>
			<th>Libellé</th>
			<th>Type de transaction</th>
			<th>Montant</th>
		</tr>
		<c:choose>
			<c:when test="${ empty transactionsListByAccount }">
				<tr>
					<td colspan="4">Aucune opération</td>
				</tr>
			</c:when>
			<c:when test="${ not empty transactionsListByAccount }">
				<c:forEach items="${ transactionsListByAccount }" var="t">
					<tr>
						<td></td>
						<td>${ t.label }<br>
						<td>${ t.transactionType }</td>
						<td>${ t.value }</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		<tr>
			<td colspan="3">Total :</td>
			<td><c:out value="" />&nbsp; <fmt:formatNumber type="currency"
					value="${account.balanceAmount.valueWithFractionDigits}"
					currencySymbol="${account.balanceAmount.currency.symbol}" /></td>
		</tr>
	</table>
	<nav>
		<ul>
			<li><a href="<c:url value="/home"/>">Retour à l'acceuil</a></li>
			<li><a href="<c:url value="/transactions"/>">Saisir une transaction</a></li>
		</ul>
	</nav>

</body>
</html>
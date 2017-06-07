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
			<tr>
			<td></td>
			<td><c:out value="${transaction.label}"/></td>
			<td><c:out value="${transaction.transactionType}"/></td>
			<td><c:out value="${transaction.recipe}"/></td>
		</tr>
		<tr>
			<td colspan="3">Total :</td>
			<td><c:out value=""/>&nbsp; 
		<fmt:formatNumber type="currency" 
		value="${account.balanceAmount.valueWithFractionDigits}" 
		currencySymbol="${account.balanceAmount.currency.symbol}"/></td>
		</tr>
	</table>
	<nav>
		<ul>
			<li><a href="<c:url value="/"/>">Créer un nouveau compte</a></li>
			<li><a href="<c:url value="/transactions"/>">Saisir une recette</a></li>
			<li><a href="<c:url value="/depenses"/>">Saisir une depense</a></li>
		</ul>
	</nav>

</body>
</html>
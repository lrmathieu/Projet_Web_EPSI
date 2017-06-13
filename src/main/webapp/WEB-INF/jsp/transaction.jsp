<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout d'une transaction</title>
</head>
<body>
<header>
	<h2> Ajouter une transaction sur le compte ${param['accountNumber']}</h2>
</header>
<%-- 
<c:choose>
	<c:when test="${error eq 'invalid.amount.format'}">
		Le solde initial doit être un nombre&nbsp;!
	</c:when>
	<c:when test="${error eq 'account.already.exists'}">
		Le compte existe déjà&nbsp;!
	</c:when>
</c:choose> --%>

<form action="<c:url value="/transactions"/>" method="POST">
	<label>Libellé</label>
	<input name="libbele" value="<c:out value="${param['libbele']}"/>"><br><br>
	
	<label>Type de transaction</label>
	<input name="transactionType" value="<c:out value="${param['transactionType']}"/>"><br><br>
	
	<!-- <label>Date</label>
	<input type="date" name="transactionDate"><br><br> -->
	<label>Montant de la transaction</label>
	<%-- <input name="recipe" value="<c:out value="${param['recipe']}"/>"><br><br> --%>
	<input name="transactionBalanceInteger" value="<c:out value="${param['transactionBalanceInteger']}"/>">,
	<input name="transationBalanceFraction" size="2" maxlength="2"> €<br/><br/>
	<%-- value="<c:out value="${param['transationBalanceFraction']}" --%>
	
	<input name="accountNumber" type="hidden" value="<c:out value="${param['accountNumber']}"/>"><br><br>
	
	<button type="submit">Ajouter</button>
</form>

</body>
</html>
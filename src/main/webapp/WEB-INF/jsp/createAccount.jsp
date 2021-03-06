<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création d'un compte</title>
</head>
<body>
<header>
<h2>Créer un compte</h2>
</header>
<c:choose>
	<c:when test="${error eq 'invalid.amount.format'}">
		Le solde initial doit être un nombre&nbsp;!
	</c:when>
	<c:when test="${error eq 'account.already.exists'}">
		Le compte existe déjà&nbsp;!
	</c:when>
</c:choose>

<form action="<c:url value="/createAccount"/>" method="POST">
	<label>Nom du compte</label>
	<input name="accountName" value="<c:out value="${param['accountName']}"/>"><br><br>
	<label>Numéro du compte</label>
	<input name="accountNumber" value="<c:out value="${param['accountNumber']}"/>"><br><br>
	<label>Solde initial</label>
	<input name="accountBalanceInteger" value="<c:out value="${param['accountBalanceInteger']}"/>">,
	<input name="accountBalanceFraction" size="2" maxlength="2" value="<c:out value="${param['accountBalanceFraction']}"/>"><br><br>
	<button type="submit">Créer</button>
</form>

</body>
</html>
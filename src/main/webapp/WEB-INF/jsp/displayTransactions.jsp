<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1">
		<tr>
			<th>Date</th>
			<th>Libellé</th>
			<th>Type de transaction</th>
			<th>Montant en EUR</th>
		</tr>
		<c:choose>
			<c:when test="${ empty transactionsList }">
				<tr>
					<td colspan="4">Aucune opération</td>
				</tr>
			</c:when>
			<c:when test="${ not empty transactionsList }">
				<c:forEach items="${ transactionsList }" var="t">
					<tr>
						<td></td>
						<td>${ t.label }<br>
						<td>${ t.transactionType }</td>
						<td>${ t.value }</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
<%-- 		<tr>
			<td colspan="3">Total :</td>
			<td><c:out value="" />&nbsp; <fmt:formatNumber type="currency"
					value="${account.balanceAmount.valueWithFractionDigits}"
					currencySymbol="${account.balanceAmount.currency.symbol}" /></td>
		</tr> --%>
	</table>

</body>
</html>
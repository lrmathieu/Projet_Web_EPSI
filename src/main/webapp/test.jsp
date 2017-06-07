<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		      <title>JSTL sql:query Tag</title>
	</head>
	<body>
	
<%-- 	<form action="<c:url value="/test"/>" method="POST">
		<label>Nom du compte</label>
		<label>Numéro du compte</label>
		<input name="accountNumber"/>"><br><br>
		<label>Solde initial</label>
		<button type="submit">Créer</button>
	</form>
	 --%>
	 <sql:setDataSource var = "accountDataSource" dataSource = "accountDataSource" driver = "com.mysql.jdbc.Driver"
	         url = "jdbc:mysql://localhost:3306/mypersonnalbankweb"
	         user = "root"  password = "root"/>
	
	         <sql:query dataSource = "${accountDataSource}" var = "result" sql="SELECT * FROM account"/>
<%-- 	         </sql:query> "${accountDataSource}" --%> 
	 
	      <table border = "1">
	         <tr>
	            <th>Numero de compte</th>
	            <th>Nom du compte</th>
	            <th>Solde initial</th>
	         </tr>
	         
	         <c:forEach var = "row" items = "${result.rows}">
	            <tr>
	               <td> <c:out value = "${row.number}"/></td>
	               <td> <c:out value = "${row.name}"/></td>
	               <td> <c:out value = "${row.value}"/></td>
	            </tr>
	         </c:forEach>
	      </table>
	
	</body>
</html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>

	<nav>
		<ul>
			<li><a href="<c:url value="/"/>">Consulter un compte</a></li>
			<li><a href="<c:url value="/accounts"/>">Créer un nouveau compte</a></li>
		</ul>
	</nav>

</body>
</html>
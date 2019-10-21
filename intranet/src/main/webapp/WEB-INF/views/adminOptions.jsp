<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Administrator Options</title>
<style TYPE="text/css">
a.button {
	-webkit-appearance: button;
	-moz-appearance: button;
	appearance: button;
	text-decoration: none;
	color: initial;
}

body {
	position: fixed;
	top: 50%;
	left: 50%;
	margin-top: -200px;
	margin-left: -200px;
	background: none repeat scroll 0 0 #111;
	color: #EEE;
}

div {
	background: none repeat scroll 0 0 #333333;
	height: 200px;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h1>Administrator Options</h1>
	<div>
		<section>
			<table>
				<tr>
					<th>Username</th>
					<th>First Name</th>
					<th>Last name</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.username}</td>
						<td>${user.fname}</td>
						<td>${user.lname}</td>
						<td><a HREF="/intranet/edit/${user.username}" CLASS="button">Edit</a></td>
						<td><a HREF="/intranet/remove/${user.username}"
							CLASS="button"> Delete </a></td>
					</tr>
				</c:forEach>
			</table>
		</section>
		<br>
		<center>
			<a href="/InsuranceOrganisation/newUser" CLASS="button">Add Employee</a>
			<a href="/InsuranceOrganisation/updateDocumetionNL" CLASS="button">Documetion update</a>
			<a href="/InsuranceOrganisation/logout" CLASS="button">Log Out</a>
		</center>
	</div>
</body>
</html>


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Admin</title>
<style TYPE="text/css">
body {
	position: fixed;
	top: 50%;
	left: 50%;
	margin-top: -200px;
	margin-left: -150px;
	background: none repeat scroll 0 0 #111;
	color: #EEE;
}

div {
	background: none repeat scroll 0 0 #333333;
}
</style>
</head>
<body>
	<br>
	<div>
		<h3>Administrator Login</h3>
		<form:form method="POST" action="/InsuranceOrganisation/checkAdmin">
			<table>
				<tr>
					<td><form:label path="AdminName">Username:</form:label></td>
					<td><form:input path="AdminName" /></td>
				</tr>
				<tr>
					<td><form:label path="AdminPassword">Password:</form:label></td>
					<td><form:input type="password" path="AdminPassword" /></td>
				</tr>
				<tr>
					<td COLSPAN="2"><input TYPE="submit" VALUE="Login"
						STYLE="float: right" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
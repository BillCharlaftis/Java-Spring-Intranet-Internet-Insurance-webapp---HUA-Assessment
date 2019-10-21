<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Home</title>
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
	<h1>Intranet Application</h1>
	<br>
	<div>
		<h3>User Login</h3>
		<form:form method="POST" action="/InsuranceOrganisation/checkUser">
			<table>
				<tr>
					<td><form:label path="username">Username:</form:label></td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td><form:label path="userpass">Password:</form:label></td>
					<td><form:input type="password" path="userpass" /></td>
				</tr>
				<tr>
					<td COLSPAN="2"><br> <input TYPE="submit" VALUE="Login"
						STYLE="float: right;" /></td>
				</tr>
			</table>
		</form:form>
		<br>
	</div>
</body>
</html>
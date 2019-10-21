<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Authentication</title>
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
	margin-left: -150px;
	background: none repeat scroll 0 0 #111;
	color: #EEE;
}

div {
	background: none repeat scroll 0 0 #333333;
	height: 150px;
}
</style>
</head>
<body>
	<h2>Representative Authentication</h2>
	<div>
		<br>
		<form:form method="POST" action="/InsuranceOrganisation/checkCompany">
			<table>
				<tr>
					<td><form:label path="adt">ADT:</form:label></td>
					<td><form:input path="adt" /></td>
				</tr>
				<tr>
					<td><form:label path="aam">AAM:</form:label></td>
					<td><form:input path="aam" /></td>
				</tr>
				<tr>
					<td COLSPAN="2">
						<center>
							<input TYPE="submit" VALUE="Authenticate"
								STYLE="text-align: center;" />
						</center>
					</td>
				</tr>
			</table>
		</form:form>
		<center>
			<a CLASS="button" HREF="/InsuranceOrganisation/index">Cancel</a>
		</center>
	</div>
</body>
</html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Authentication</title>
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
	height: 100px;
}
</style>
</head>
<body>
	<h2>Give Request ID</h2>
	<br>
	<div>
		<form:form method="POST" action="/InsuranceOrganisation/reqIDafm">
			<table>
				<tr>
					<td><form:label path="requestID" style="float:right;">Request ID:</form:label></td>
					<td><form:input path="requestID" /></td>
				</tr>
				<tr>
					<td COLSPAN="2"><input TYPE="submit" VALUE="Search"
						STYLE="float: right;" /></td>
				</tr>
			</table>
		</form:form>

		<form ACTION="/InsuranceOrganisation/index">
			<input TYPE="submit" VALUE="Cancel" STYLE="float: right;">
		</form>
	</div>
</body>
</html>
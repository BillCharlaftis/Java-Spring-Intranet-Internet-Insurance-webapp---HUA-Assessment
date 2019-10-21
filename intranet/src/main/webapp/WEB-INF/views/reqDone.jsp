<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Certificate Duration</title>
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
	height: 150px;
}
</style>
</head>
<body>
	<h2>Certificate Duration</h2>
	<br>
	<div>
		<form:form method="POST" action="/InsuranceOrganisation/finish">
			<table>
				<tr>
					<td><form:label path="duration">Duration (in months):</form:label></td>
					<td><form:input path="duration" /></td>
				</tr>
				<tr>
					<td COLSPAN="2"><input TYPE="submit" VALUE="Continue"
						STYLE="float: right" /></td>
				</tr>
			</table>
		</form:form>

		<form ACTION="/InsuranceOrganisation/completeRequest">
			<center>
				<input TYPE="submit" VALUE="Back">
			</center>
		</form>

		<form ACTION="/InsuranceOrganisation/index">
			<center>
				<input TYPE="submit" VALUE="Cancel">
			</center>
		</form>
	</div>
</body>
</html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Add Receiver</title>
<style TYPE="text/css">
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}

body {
	position: fixed;
	top: 40%;
	left: 65%;
	margin-top: -200px;
	margin-left: -320px;
	background: none repeat scroll 0 0 #111;
	color: #EEE;
}

div {
	background: none repeat scroll 0 0 #333333;
	height: 143x;
	left: 50%;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<center>
		<h2>NewsLetter form</h2>
	</center>
	<div>
		<form:form method="POST" action="/InsuranceOrganisation/addNLRreciver">
			<table>
				<tr>
					<td><form:label path="fname">First Name:</form:label></td>
					<td><form:input path="fname" /></td>
				</tr>
				<tr>
					<td><form:label path="lname">Last Name:</form:label></td>
					<td><form:input path="lname" /></td>
				</tr>
				<tr>
					<td><form:label path="mail">Mail:</form:label></td>
					<td><form:input path="mail" /></td>
				</tr>
				<tr>
					<td COLSPAN="2"><br>
						<center>
							<input TYPE="submit" VALUE="Submit" />
						</center> <br></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Add User</title>
<style TYPE="text/css">
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}

a.button {
	-webkit-appearance: button;
	-moz-appearance: button;
	appearance: button;
	text-decoration: none;
	color: initial;
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
	height: 423px;
	left: 50%;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<center>
		<h2>Representative Information</h2>
	</center>
	<div>
		<form:form method="POST" modelAttribute="newRepresentative"
			action="/InsuranceOrganisation/addRepresentative">
			<table>
				<tr>
					<td><form:label path="Fname">First Name</form:label></td>
					<td><form:input path="Fname" /></td>
					<td><form:errors path="Fname" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="Lname">Last Name</form:label></td>
					<td><form:input path="Lname" /></td>
					<td><form:errors path="Lname" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="AAM">AAM</form:label></td>
					<td><form:input path="AAM" /></td>
					<td><form:errors path="AAM" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="Sex">Sex</form:label></td>
					<td><form:input path="Sex" /></td>
					<td><form:errors path="Sex" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="Mail">Mail</form:label></td>
					<td><form:input path="Mail" /></td>
					<td><form:errors path="Mail" cssClass="error" /></td>
				</tr>
				<tr>
					<td COLSPAN="2"><br>
						<center>
							<input TYPE="submit" VALUE="Submit" />
							<a ID="1" HREF="/InsuranceOrganisation/index" CLASS="button">Cancel</a>
						</center> <br></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Edit User</title>
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

.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>
<body>
	<h2>User Information</h2>
	<div>
		<form:form method="POST" modelAttribute="newUser"
			action="/InsuranceOrganisation/update">
			<table>
				<tr>
					<td><form:label path="username">User Name</form:label></td>
					<td><form:input path="username" /></td>
					<td><form:errors path="fname" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="userpass">Password</form:label></td>
					<td><form:input path="userpass" /></td>
					<td><form:errors path="userpass" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="fname">First Name</form:label></td>
					<td><form:input path="fname" /></td>
					<td><form:errors path="fname" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="lname">Last Name</form:label></td>
					<td><form:input path="lname" /></td>
					<td><form:errors path="lname" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="phoneNumber">Phone</form:label></td>
					<td><form:input path="phoneNumber" /></td>
					<td><form:errors path="phoneNumber" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="address">Address</form:label></td>
					<td><form:input path="address" /></td>
					<td><form:errors path="address" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="sex">Sex</form:label></td>
					<td><form:input path="sex" /></td>
					<td><form:errors path="sex" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="mail">Mail</form:label></td>
					<td><form:input path="mail" /></td>
					<td><form:errors path="mail" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="role">Role</form:label></td>
					<td><form:input path="role" /></td>
					<td><form:errors path="role" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="canCheck">Can Check</form:label></td>
					<td><form:input path="canCheck" /></td>
					<td><form:errors path="canCheck" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="canPrint">Can Print</form:label></td>
					<td><form:input path="canPrint" /></td>
					<td><form:errors path="canPrint" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="canRecord">Can Record</form:label></td>
					<td><form:input path="canRecord" /></td>
					<td><form:errors path="canRecord" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="canEdit">Can Edit</form:label></td>
					<td><form:input path="canEdit" /></td>
					<td><form:errors path="canEdit" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="canFileCheck">canFileCheck</form:label></td>
					<td><form:input path="canFileCheck" /></td>
					<td><form:errors path="canFileCheck" cssClass="error" /></td>
				</tr>
				<tr>
					<td COLSPAN="2"><input TYPE="submit" VALUE="Submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
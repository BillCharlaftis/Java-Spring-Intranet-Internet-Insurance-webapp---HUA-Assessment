<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Success</title>
<style TYPE="text/css">
body {
	background: none repeat scroll 0 0 #111;
	color: #EEE;
}
</style>
</head>
<body>
	<br>
	<center><h3>You sign up succeessfully!!! Your User Name is company's AAM and your password is: ${newRepresentative}</h3></center>

	<form METHOD="GET" ACTION="/InsuranceOrganisation/index">
		<br>
		<center>
			<input TYPE="submit" VALUE="OK">
		</center>
	</form>

</body>
</html>
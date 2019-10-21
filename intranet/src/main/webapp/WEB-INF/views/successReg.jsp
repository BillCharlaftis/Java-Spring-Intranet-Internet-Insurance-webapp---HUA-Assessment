<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Success</title>
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
</style>
</head>
<body>
	<br>
	<h3>Your request has been recorded with ID: ${register}</h3>

	<form METHOD="GET" ACTION="/InsuranceOrganisation/index">
		<br>
		<center>
			<input TYPE="submit" VALUE="OK">
		</center>
	</form>

</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Main Menu</title>
<style TYPE="text/css">
a.button {
	-webkit-appearance: button;
	-moz-appearance: button;
	appearance: button;
	visibility: hidden;
	text-decoration: none;
	color: initial;
}

body {
	position: fixed;
	top: 50%;
	left: 50%;
	margin-top: -200px;
	margin-left: -320px;
	background: none repeat scroll 0 0 #111;
	color: #EEE;
}

div {
	background: none repeat scroll 0 0 #333333;
	height: 120px;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<div>
		<section>
			<table>
				<tr>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Phone Number</th>
					<th>Address</th>
					<th>Mail</th>
					<th>Sex</th>
					<th>Role</th>
				</tr>
				<c:forEach items="${user}" var="user">
					<tr>
						<td>${user.username}</td>
						<td>${user.fname}</td>
						<td>${user.lname}</td>
						<td>${user.phoneNumber}</td>
						<td>${user.address}</td>
						<td>${user.mail}</td>
						<td>${user.sex}</td>
						<td>${user.role}</td>
					</tr>
				</c:forEach>
			</table>
		</section>
		<br>
		<center>
			<a ID="1" HREF="/InsuranceOrganisation/req" CLASS="button">Register Request</a> <a
				ID="2" HREF="/InsuranceOrganisation/print" CLASS="button">Print Certificate</a>
		</center>
		<center>
			<a ID="3" HREF="/InsuranceOrganisation/allRequests" CLASS="button">Get
				Requests</a>
		</center>
		<a ID="4" HREF="/InsuranceOrganisation/logout" CLASS="button"
			STYLE="visibility: visible; float: right">Logout</a> <br>
	</div>
	<script TYPE="text/javascript">
		var user = "${user}";
		if (user == "[support]") {
			document.getElementById("1").style.visibility = "visible";
			document.getElementById("2").style.visibility = "visible";
		}

		if (user == "[issue]") {
			document.getElementById("3").style.visibility = "visible";
		}
	</script>
</body>
</html>
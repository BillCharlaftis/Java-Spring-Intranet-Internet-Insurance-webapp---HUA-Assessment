<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Check Company Menu</title>
<style TYPE="text/css">
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
	height: 150px;
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
					<th>AAM</th>
					<th>AFM</th>
					<th>Brand Name</th>
					<th>ADT</th>
					<th>Representative Name</th>
					<th>Phone Number</th>
					<th>Address</th>
				</tr>
				<c:forEach items="${company}" var="company" begin="0" end="0">
					<tr>
						<td>${company.aam}</td>
						<td>${company.afm}</td>
						<td>${company.brandName}</td>
						<td>${company.adt}</td>
						<td>${company.repName}</td>
						<td>${company.phoneNumber}</td>
						<td>${company.address}</td>
					</tr>
				</c:forEach>
			</table>
		</section>

		<form METHOD="POST" ACTION="/InsuranceOrganisation/completeReq">
			<br>
			<center>
				<input TYPE="submit" VALUE="Continue">
			</center>
		</form>

		<br>

		<form METHOD="GET" ACTION="/InsuranceOrganisation/req">
			<center>
				<input TYPE="submit" VALUE="Back">
			</center>
		</form>
	</div>
</body>
</html>
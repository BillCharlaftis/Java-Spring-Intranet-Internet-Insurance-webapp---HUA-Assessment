<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>All Requests</title>
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
	top: 0%;
	left: 50%;
	margin-top: 20px;
	margin-left: -320px;
	background: #111;
	color: #EEE;
}

div {
	background: none repeat scroll 0 0 #333333;
	height: 475px;
	overflow: scroll;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<center>
		<h3>ALL REQUESTS</h3>
	</center>
	<form:form method="POST" action="/InsuranceOrganisation/getRequest">
		<center>
			<form:label path="requestId">Provide Request ID:</form:label>
			<center>
				<form:input path="requestId" />
				<input TYPE="submit" VALUE="Edit" />
			</center>
		</center>
		<br><center><a href="/InsuranceOrganisation/index" CLASS="button">Cancel</a></center>
	</form:form>
	<br>
	<div>
		<section>
			<table>
				<tr>
					<th>Request ID</th>
					<th>AAM</th>
					<th>BrandName</th>
					<th>RepName</th>
					<th>Duration</th>
					<th>isAccepted</th>
					<th>isDelivered</th>
				</tr>
				<c:forEach items="${requests}" var="request">
					<tr>
						<td>${request.requestId}</td>
						<td>${request.aam}</td>
						<td>${request.brandName}</td>
						<td>${request.repName}</td>
						<td>${request.duration}</td>
						<td>${request.returnValueAcc}</td>
						<td>${request.returnValueDel}</td>
					</tr>
				</c:forEach>
			</table>
		</section>
		<br>
	</div>
</body>
</html>
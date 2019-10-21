<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Edit Request</title>
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
	top: 50%;
	left: 50%;
	margin-top: -200px;
	margin-left: -320px;
	background: none repeat scroll 0 0 #111;
	color: #EEE;
}

div {
	background: none repeat scroll 0 0 #333333;
	height: 100px;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h3>Edit Request</h3>
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
				<c:forEach items="${request}" var="request">
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
			<br>
		</section>
		<a HREF="/InsuranceOrganisation/PdfFiles" CLASS="button">Check Company Files</a>
	</div>
</body>
</html>
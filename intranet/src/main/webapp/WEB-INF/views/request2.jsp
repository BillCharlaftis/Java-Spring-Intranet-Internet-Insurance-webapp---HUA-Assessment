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
	background: #111;
	color: #EEE;
}

div {
	background: none repeat scroll 0 0 #333333;
	height: -200px;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h3 ALIGN="center">Print Pdf:</h3>

	<section>
	<c:forEach items="${pdfs}" var="path">
		<br>
		<center>
			<a ID="1" HREF="/InsuranceOrganisation/accept" CLASS="button">Accept Request</a> <a
				ID="2" HREF="/InsuranceOrganisation/decline/${path.requestID}" CLASS="button">Decline Request</a>
		</center>

		
			<br>
			<div>
				<object DATA="/InsuranceOrganisation/getpdf/${path.id}" TYPE="application/pdf"
					WIDTH="100%" HEIGHT="80%">
					<a HREF="/InsuranceOrganisation/getpdf/${path.id}" CLASS="button">pdf</a>
				</object>
			</div>
			<br>
		</c:forEach>
	</section>
</body>
</html>
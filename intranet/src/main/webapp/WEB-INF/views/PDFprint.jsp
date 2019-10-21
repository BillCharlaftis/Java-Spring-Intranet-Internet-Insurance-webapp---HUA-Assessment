<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
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
		<br>
		<div>
			<object DATA="/InsuranceOrganisation/PreviewPdf" TYPE="application/pdf"
				WIDTH="100%" HEIGHT="80%">
				<center><h4>Your browser does not support pdf. You can use Chrome or click the button below in order to download the file. </h4>
				<a HREF="/InsuranceOrganisation/PreviewPdf" CLASS="button">Download PDF</a></center>
			</object>
		</div>
		<br>
		<center>
			<a ID="1" HREF="/InsuranceOrganisation/index" CLASS="button">Back</a>
			<a ID="2" HREF="/InsuranceOrganisation/newCustomer" CLASS="button">Register</a>
		</center>

	</section>
</body>
</html>
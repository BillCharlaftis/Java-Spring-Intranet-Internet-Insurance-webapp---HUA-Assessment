<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>
<head>
<title>Print Request</title>
<style TYPE="text/css">

body {
	background: none repeat scroll 0 0 #111;
	color: #919191;
}

div {
	background: none repeat scroll 0 0 #333333;
}

.all-centered {
	position: fixed; /* or absolute */
	top: 40%;
	left: 45%;
}

h1 {
	font-family: "Lucida Sans Unicode", "Lucida Sans", Geneva, Verdana,
		sans-serif;
	float: center;
	color: #919191;
}

.clearfix:after {
	display: block;
	clear: both;
}

/*----- Menu Outline -----*/
.menu-wrap {
	width: 100%;
	box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.2);
	background: #3e3436;
}

.menu {
	width: 1000px;
	margin: 0px auto;
}

.menu li {
	margin: 0px;
	list-style: none;
	font-family: 'Ek Mukta';
}

.menu a {
	transition: all linear 0.15s;
	color: #919191;
}

.menu li:hover>a, .menu .current-item>a {
	text-decoration: none;
	color: #be5b70;
}

.menu .arrow {
	font-size: 11px;
	line-height: 0%;
}

/*----- Top Level -----*/
.menu>ul>li {
	float: left;
	display: inline-block;
	position: relative;
	font-size: 19px;
}

.menu>ul>li>a {
	padding: 10px 40px;
	display: inline-block;
	text-shadow: 0px 1px 0px rgba(0, 0, 0, 0.4);
}

.menu>ul>li:hover>a, .menu>ul>.current-item>a {
	background: #2e2728;
}

/*----- Bottom Level -----*/
.menu li:hover .sub-menu {
	z-index: 1;
	opacity: 1;
}

.sub-menu {
	width: 160%;
	padding: 5px 0px;
	position: absolute;
	top: 100%;
	left: 0px;
	z-index: -1;
	opacity: 0;
	transition: opacity linear 0.15s;
	box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.2);
	background: #2e2728;
}

.sub-menu li {
	display: block;
	font-size: 16px;
}

.sub-menu li a {
	padding: 10px 30px;
	display: block;
}

.sub-menu li a:hover, .sub-menu .current-item a {
	background: #3e3436;
}

a.button {
	-webkit-appearance: button;
	-moz-appearance: button;
	appearance: button;
	text-decoration: none;
	color: initial;
}

</style>
</head>
<body>
	<center><h1>Harokopeion Insurance Organisation</h1></center>
	<div class="menu-wrap">
		<nav class="menu">
			<ul class="clearfix">
				<li><a
					href="${pageContext.request.contextPath}">Home</a></li>
				<li><a href="#">Informational Material <span class="arrow">&#9660;</span></a>

					<ul class="sub-menu">
						<li><a href="/internet/departments">Customer Service
								Departments</a></li>
						<li><a href="/internet/legislation">Insurance Legislation</a></li>
						<li><a href="/internet/documentation">Documentation</a></li>
						<li><a href="/internet/newsletter">Newsletter</a></li>
					</ul></li>

				<li class="current-item"><a href="#">Online Services <span class="arrow">&#9660;</span></a>

					<ul class="sub-menu">
						<li><a href="/internet/request/new">Submit Certificate
								Request</a></li>
						<li class="current-item"><a href="/internet/getPdf">Search Request and
								Download PDF</a></li>
					</ul></li>
					<li><a href="/internet/user">Log in</a>
					<li><a href="/internet/customerLogout">Logout</a>
			</ul>
		</nav>
	</div>
	<section>
		<br>
			<object DATA="/internet/PreviewPdf" TYPE="application/pdf"
				WIDTH="100%" HEIGHT="80%">
				<center><h4>Your browser does not support pdf. You can use Chrome or click the button below in order to download the file. </h4>
				<a HREF="/InsuranceOrganisation/PreviewPdf" CLASS="button">Download PDF</a></center>
			</object>
		<br>
		<center>
			<a ID="1" HREF="/internet/" CLASS="button">Back</a>
		</center>

	</section>
</body>
</html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Get Request</title>
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
	top: 30%;
	left: 45%;
}

h1 {
	font-family: "Lucida Sans Unicode", "Lucida Sans", Geneva, Verdana,
		sans-serif;
	float: center;
	color: #919191;
	float: center;
}

h2 {
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
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<center><h2>Search Request</h2></center>
	<br>
	<div class="all-centered">
		<form:form method="POST" action="/internet/printPdf">
			<table>
				<tr>
					<td><form:label path="requestId">Request Id:</form:label></td>
					<td><form:input path="requestId" /></td>
				</tr>
				<tr>
					<td COLSPAN="2"><br> <input TYPE="submit" VALUE="Search"
						STYLE="float: right;" /></td>
				</tr>
			</table>
		</form:form>
		<br>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-9">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Brand</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-9">
			<ul class="nav nav-justified">
				<li class="active"><a href="#">Home</a></li>
				
				
				
				<li>
				<button class="btn btn-default dropdown-toggle" type="button"
							id="dropdownMenu1" data-toggle="dropdown">
							Dropdown <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu"
							aria-labelledby="dropdownMenu1">
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="#">Action</a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="#">Another action</a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="#">Something else here</a></li>
							<li role="presentation" class="divider"></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="#">Separated link</a></li>
						</ul><a href="#">Link</a></li>
				<li><a href="#">Link</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
<html>
<head>
<title>Insert Data Form</title>
</head>




<div class="zero-clipboard" align="center">
	<h2>Insert Data Form</h2>
	<table class="table" style="width: 600px;"center">
		<!--- begin html form; 
put action page in the "action" attribute of the form tag --->
		<form action="insert_action.cfm" method="post">
			<tr>
				<td><span class="glyphicon glyphicon-user"></span> Codice
					Documento:</td>
				<td><input type="text" class="form-control" name="Emp_ID"
					style="width: 100px;" 4" maxlength="4"></td>
			</tr>
			<tr>
				<td><span class="glyphicon glyphicon-user"></span> Descrizione:</td>
				<td><input type="Text" class="form-control" name="FirstName"
					size="35" maxlength="50"></td>
			</tr>
			<tr>
				<td><span class="glyphicon glyphicon-user"></span> Giorni di
					avviso scadenza:</td>
				<td><input type="Text" class="form-control" name="LastName"
					size="35" maxlength="50"></td>
			</tr>
			<tr>
				<td>Department Number:</td>
				<td><div>
						<button class="btn btn-default dropdown-toggle" type="button"
							id="dropdownMenu1" data-toggle="dropdown">
							Dropdown <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu"
							aria-labelledby="dropdownMenu1">
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="#">Action</a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="#">Another action</a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="#">Something else here</a></li>
							<li role="presentation" class="divider"></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="#">Separated link</a></li>
						</ul>
					</div>
			</tr>
			<tr>
				<td>Start Date:</td>
				<td><select class="form-control">
						<option value="volvo">Volvo</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
				</select>
			</tr>
			<tr>
				<td>Salary:</td>
				<td><input type="Text" class="form-control" name="Salary"
					size="10" maxlength="10"></td>
			</tr>
			<tr>
				<td>Contractor:</td>
				<td><input type="checkbox" name="Contract" value="Yes" checked>Yes</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="Submit" value="Submit">&nbsp;<input
					type="Reset" value="Clear Form"></td>
			</tr>
		</form>
		<!--- end html form --->
	</table>
	<div>
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dropdownMenu1" data-toggle="dropdown">
			Dropdown <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="#">Action</a></li>
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="#">Another action</a></li>
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="#">Something else here</a></li>
			<li role="presentation" class="divider"></li>
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="#">Separated link</a></li>
		</ul>
	</div>
</div>


</html>

<div class="container">
	<h1>Welcome to Jsp!</h1>
	<hr>
	<form action="MyServlet">

		<input type="button" class="btn btn-default btn-lg"> <span
			class="glyphicon glyphicon-star"> </span> Star
		</button>
		<input type="submit" value="Send" />
	</form>
	<form action="">
		<fieldset>
			<div class="form-group">

				<input class="form-control" id="exampleInputEmail1"
					placeholder="Enter email" type="email">
			</div>
			<legend>Gestione Documenti:</legend>
			<label for="exampleInputEmail1">Email address</label> Name: <input
				type="text" size="30"> <br> E-mail: <input type="text"
				size="30"> <br> Date of birth: <input type="text"
				size="10">
		</fieldset>
	</form>
	<h1 class="alert-danger">
		<span class="glyphicon glyphicon-music"></span> Danger
	</h1>
	<div>
		<table class="table table-striped table-bordered">
			<tr>
				<th>Cazzo</th>
				<th>Merda</th>
				<th>Pipi</th>
			</tr>
			<tr>
				<td>10</td>
				<td>12</td>
				<td>07</td>
			</tr>
		</table>
	</div>
	<table class="table-bordered table-hover" style="width: 100%">
		<tr>
			<td>Jill</td>
			<td>Smith</td>
			<td>50</td>
		</tr>
		<tr>
			<td>Eve</td>
			<td>Jackson</td>
			<td>94</td>
		</tr>
	</table>
	<div class="col-md-9"">
		<form role="form">
			<div class="form-group">
				<label
					for="exampleInputEmail1″>Email address</label> <input
					type="email" class="form-control" id="exampleInputEmail1″
					placeholder="Enteremail">
			</div>
			<div class="form-group">
				<label
					for="exampleInputPassword1″>Password</label> <input
					type="password" class="form-control" id="exampleInputPassword1″
					placeholder="Password">
			</div>
			<div class="form-group">

				<label for="exampleInputFile">File input</label> <input type="file"
					id="exampleInputFile">
				<p class="help-block">Example block-level help text here.</p>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox"> Check me out
				</label>
			</div>
			<button type="submit" class="btnbtn-default">Submit</button>
		</form>
	</div>
</div>
</body>
</html>
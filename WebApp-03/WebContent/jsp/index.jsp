<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Progetto test</title>

<link href="../css/bootstrap.min.css" rel="stylesheet" />


<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js">
	
</script>

</head>
<body>
	<div class="starter-template">
		<h1>
			<a href="/WebApp-03/MyServlet">Ciao a Tutti</a>
		</h1>
		<div class="dropdown clearfix">
			<button class="btn btn-default dropdown-toggle" type="button"
				id="dropdownMenu2" data-toggle="dropdown">
				Dropdown <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2">
				<li role="presentation" class="dropdown-header">Dropdown header</li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="#">Action</a></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="#">Another action</a></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="#">Something else here</a></li>
				<li role="presentation" class="divider"></li>
				<li role="presentation" class="dropdown-header">Dropdown header</li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="#">Separated link</a></li>
			</ul>
		</div>


		<%
			String nomeBotton = "";
			nomeBotton = "peppino";
		%>

		<input type="submit" name="Clicca" value=<%=nomeBotton%>>

		<h3>Login:</h3>
		<form action="/WebApp-03/MyServlet" method="post">
			<table border="1">
				<tr>
					<td>Username:</td>
					<td><input type=text value="" name="user"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type=password value="" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="conferma"
						name=conf></td>
				</tr>
			</table>
		</form>

<div class="col-lg-3">
		<form class="bs-example bs-example-form" method="post"
			action="login.jsp">
			<center>
				<div class="input-group">
					<span class="input-group-addon">Utente</span> <input type="text"
						name="uname" class="form-control" placeholder="Username" value="" />
					</td>
				</div>
				<div class="input-group">
					<span class="input-group-addon">Password</span> <input type="password"
						name="pass" class="form-control" placeholder="Password" value="" />
					</td>
				</div>

				<input type="submit" value="Login" />
				</td> <input type="reset" value="Reset" />
				</td>
				<h4>
					Yet Not Registered!! <a href="reg.jsp">Register Here</a>
				</h4>

			</center>
		</form>
</div>




	</div>



</body>
</html>
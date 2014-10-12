<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>

<%@ page import="java.sql.*"%>
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
				<% 
       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", null);

       Statement st = con.createStatement();
       ResultSet rs = st.executeQuery("select * from UTENTI");
       try {
    	  
             while (rs.next()) {
             %>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="#"><%=rs.getString(1) + " " + rs.getString(2)  + " " + rs.getString(3)%></a></li>


				<% }
             } catch (Exception e) {
                e.printStackTrace();
             }
            %>




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

		<div class="container">
			<form class="form-signin" role="form" method="post"
				action="login.jsp">
				<h2 class="form-signin-heading">Autenticazione</h2>

				<input class="form-control" name="uname" placeholder="Username"
					required="" autofocus="" type="text"> <input
					class="form-control" name="pass" placeholder="Password" required=""
					autofocus="" type="text">

				<button class="btn btn-lg btn-primary btn-block" type="submit"
					value="Login">Sign in</button>

				<input type="submit" value="Login" />
				</td> <input type="reset" value="Reset" />
				</td>
				<h4>
					Yet Not Registered!! <a href="reg.jsp">Register Here</a>
				</h4>


			</form>
		</div>



		<select name="cmb" id="cmb" style="width: 178">
			<option value="">Select One</option>
			<% 
       Class.forName("com.mysql.jdbc.Driver");
       Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", null);

       Statement st2 = con2.createStatement();
       ResultSet rs2 = st2.executeQuery("select * from UTENTI");
       try {
    	  
             while (rs2.next()) {
             %>
			<option value="<%=rs2.getString("nome")%>"><%=rs2.getString("nome") + " " + rs2.getString(2)%></option>
			<% }
             } catch (Exception e) {
                e.printStackTrace();
             }
            %>
		</select>


	</div>



</body>
</html>
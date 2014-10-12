<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
	
<%@ page import ="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Progetto</title>
<link href="../css/bootstrap.min.css" rel="stylesheet" />


<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js">

</head>
<body>


	<table style="width: 100%">

		<% 
       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", null);

       Statement st = con.createStatement();
       ResultSet rs = st.executeQuery("select * from UTENTI");
       try {
    	  
             while (rs.next()) {
             %>
		<tr>
			<td><%=rs.getString(1)%></td>
			<td><%=rs.getString(2)%></td>
			<td><%=rs.getString(3)%></td>
		</tr>

		<% }
             } catch (Exception e) {
                e.printStackTrace();
             }
            %>

	</table>


</body>
</html>
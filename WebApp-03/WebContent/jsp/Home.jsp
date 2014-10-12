<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">






<title>Insert title here</title>

<h1>Saluti dalla JSP Home.jsp</h1>
<%
	String nome = "pippo";
%>
<h3>
	Salutiamo
	<%=nome%></h3>



</head>
<body>



	<h1><%=request.getAttribute("variabileTest")%></h1>
	<h1>Pagina di benvenuto</h1>

	<%
		if (request.getAttribute("user").equals("dio")) {
			if (request.getAttribute("pwd").equals("boia")) {
				out.println("Benvenuto utente: "
						+ request.getAttribute("user") + " con password: "
						+ request.getAttribute("pwd"));
			}
		} else

			out.println("hai cannato utente e password");
	%>



</body>
</html>
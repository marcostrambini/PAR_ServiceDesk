package mago;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher dispatcher;
//		request.setAttribute("variabileTest", "Questo valore viene settato nella Servlet");
//		
//		String user = request.getParameter("user");
//		String pwd = request.getParameter("pwd");
//		
//		request.setAttribute("user", user);
//		request.setAttribute("pwd", pwd);
//		
//		dispatcher = getServletContext().getRequestDispatcher("/jsp/Home.jsp");
//		dispatcher.forward(request, response);
		
		
		
		PrintWriter out = response.getWriter();
		out.println("Ciao dalla Servlet");
		out.println("<h1> Zio Billy </h1>");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		request.setAttribute("user", user);
		request.setAttribute("pwd", pwd);
		
		dispatcher = getServletContext().getRequestDispatcher("/jsp/Home.jsp");
		dispatcher.forward(request, response);
	}

}

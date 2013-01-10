package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/hello"}
    )
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("hello <b>world</b>");
        /*
        String url = System.getenv("CLEARDB_DATABASE_URL");
        if(url == null)
        	url = "[empty]";
        writer.println(url+"<br>");
        try
		{
			writer.println(Class.forName("org.gjt.mm.mysql.Driver").getName()+"<br>");
		} catch (ClassNotFoundException e)
		{
			writer.println("[class not found]");
		}*/
    }
    
}
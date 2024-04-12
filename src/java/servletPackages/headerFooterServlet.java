package servletPackages;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elrayes
 */
public class headerFooterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pen = response.getWriter();
        request.getRequestDispatcher("header.html").include(request, response);
        pen.println("This is the profile");
        request.getRequestDispatcher("footer.html").include(request, response);
    }

  
}

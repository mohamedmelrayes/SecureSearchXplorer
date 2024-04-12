package servletPackages;

import JDBC.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author elrayes
 */
public class isLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       Users login = new Users();
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        int x = login.isUserAuth(username, pass);

        if (x > 0) {

            Cookie c1 = new Cookie("userName", request.getParameter("username"));
            //  c1.setMaxAge(1 * 24 * 60 * 60);
            Cookie c2 = new Cookie("password", request.getParameter("password"));
            //      c2.setMaxAge(1 * 24 * 60 * 60);

            response.addCookie(c1);
            response.addCookie(c2);

            response.sendRedirect("profile");
        } else {
            request.setAttribute("errorMessage", "Invalid Login Credentials");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

}

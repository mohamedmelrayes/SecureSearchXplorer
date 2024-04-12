package servletPackages;

import JDBC.UserInfo;
import JDBC.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to retrieve and display all user information.
 */
public class getAllUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Users ob = new Users();
        List<UserInfo> userList =  ob.getAllUserInfo();

        Cookie[] cookies = request.getCookies();
        boolean loggedIn = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userName".equals(cookie.getName())) {
                    loggedIn = true;
                    break;
                }
            }
        }

        if (!loggedIn) {
            // Redirect to the login page
            response.sendRedirect("login.html");
            return;
        }

        
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>All Users</title>");
        out.println("<style>");
        out.println("    body {");
        out.println("        font-family: Arial, sans-serif;");
        out.println("        margin: 0;");
        out.println("        padding: 0;");
        out.println("        background-color: #f4f4f4;");
        out.println("    }");

        out.println("    header {");
        out.println("        background-color: #333;");
        out.println("        color: #fff;");
        out.println("        padding: 10px;");
        out.println("        text-align: center;");
        out.println("    }");

        out.println("    nav {");
        out.println("        background-color: #444;");
        out.println("        padding: 10px;");
        out.println("        text-align: center;");
        out.println("    }");

        out.println("    nav a {");
        out.println("        color: #fff;");
        out.println("        text-decoration: none;");
        out.println("        padding: 10px;");
        out.println("        margin: 0 10px;");
        out.println("        font-weight: bold;");
        out.println("    }");

        out.println("    section {");
        out.println("        padding: 20px;");
        out.println("        text-align: center;");
        out.println("    }");

        out.println("    footer {");
        out.println("        background-color: #333;");
        out.println("        color: #fff;");
        out.println("        padding: 10px;");
        out.println("        text-align: center;");
        out.println("    }");

        out.println("    table {");
        out.println("        width: 100%;");
        out.println("        border-collapse: collapse;");
        out.println("        margin-top: 20px;");
        out.println("    }");

        out.println("    th, td {");
        out.println("        border: 1px solid #ddd;");
        out.println("        padding: 8px;");
        out.println("        text-align: left;");
        out.println("    }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<header>");
        out.println("    <h1>All Users</h1>");
        out.println("</header>");

        out.println("<nav>");
        out.println("    <a href=\"index.html\">Home</a>");
        out.println("    <a href=\"login.html\">Login</a>");
        out.println("    <a href=\"registration.html\">New User</a>");
        out.println("    <a href=\"search.html\">Search</a>");
        out.println("</nav>");

        out.println("<section>");
        out.println("    <table>");
        out.println("        <tr><th>FirstName</th><th>Username</th><th>Password</th></tr>");

        for (UserInfo rs : userList) {
            String fname = rs.getFname();
            String username = rs.getUsername();
            String passwd = rs.getPassword();

            out.println("        <tr><td>" + fname + "</td><td>" + username + "</td><td>" + passwd + "</td></tr>");
        }

        out.println("    </table>");
        out.println("</section>");

        out.println("<footer>");
        out.println("    <p>&copy; 2024 Your Website Name. All rights reserved.</p>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }
}

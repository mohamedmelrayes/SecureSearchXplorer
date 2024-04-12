package servletPackages;

import JDBC.UserInfo;
import JDBC.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to display user profile information.
 */
public class profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>User Profile</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("    background-color: #f4f4f4;");
        out.println("}");

        out.println("header {");
        out.println("    background-color: #333;");
        out.println("    color: #fff;");
        out.println("    padding: 10px;");
        out.println("    text-align: center;");
        out.println("}");

        out.println("nav {");
        out.println("    background-color: #444;");
        out.println("    padding: 10px;");
        out.println("    text-align: center;");
        out.println("}");

        out.println("nav a {");
        out.println("    color: #fff;");
        out.println("    text-decoration: none;");
        out.println("    padding: 10px;");
        out.println("    margin: 0 10px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println("section {");
        out.println("    padding: 20px;");
        out.println("    text-align: center;");
        out.println("}");

        out.println("footer {");
        out.println("    background-color: #333;");
        out.println("    color: #fff;");
        out.println("    padding: 10px;");
        out.println("    text-align: center;");
        out.println("}");

        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<header>");
        out.println("<h1>User Profile</h1>");
        out.println("</header>");

        out.println("<nav>");
        out.println("    <a href=\"index.html\">Home</a>");
        out.println("    <a href=\"login.html\">Login</a>");
        out.println("    <a href=\"registration.html\">New User</a>");
        out.println("    <a href=\"search.html\">Search</a>");
        out.println("</nav>");

        out.println("<section>");
        out.println("<h2>Your Profile Information:</h2>");


        // Retrieve cookies
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                out.println("Cookie Name: " + cookie.getName() + ", Value: " + cookie.getValue());

                // Display user information
                if ("userName".equals(name)) {
                    Users user = new Users();
                    UserInfo data = user.getUserInfo(value);

                    if (data != null) {
                        out.println("<p><strong>" + name + ":</strong> " + value + "</p>");
                        out.println("<p><strong>First Name:</strong> " + data.getFname() + "</p>");
                        out.println("<p><strong>Last Name:</strong> " + data.getLname() + "</p>");
                    } else {
                        out.println("<p>No user information found in the database.</p>");
                    }
                }
            }
        } else {
            out.println("<p>No user information found in cookies.</p>");
        }

        out.println("</section>");

        out.println("<footer>");
        out.println("<p>&copy; 2024 Your Website Name. All rights reserved.</p>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");

    }
}

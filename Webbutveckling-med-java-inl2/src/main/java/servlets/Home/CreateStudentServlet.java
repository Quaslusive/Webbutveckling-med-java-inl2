package servlets.Home;

import models.MysqlConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CreateStudentServlet", urlPatterns = "/addstudents")
public class CreateStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String message = req.getParameter("message");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add a student</title>");
        out.println("<link rel=stylesheet type=text/css href=style.css />");
        out.println("</head>");
        out.println("<body>");

        out.println("<header>");
        out.println("<nav>");
        out.println("<ul>");
        out.println("<li><a href= home>| Home |</a></li>");
        out.println("<li><a href= courses>| Courses |</a></li>");
        out.println("<li><a href= students>| Students |</a></li>");
        out.println("<li><a href= attendance>| Attendance |</a></li>");
        out.println("<li><a href= enrollcourse>| Enroll Courses |</a></li>");
        out.println("<li><a href= addcourses>| Add Courses |</a></li>");
        out.println("<li><a href= addstudents>| Add Students |</a></li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</header>");

        out.println("<div class=top-titel fadeInAnimation >");
        out.println("<h1>Add a student</h1>");
        out.println("</div>");

        out.println("<main>");

        if (message != null) {
            out.println(message);
        }
        out.println("<div>");
        out.println("<table border=1>");
        out.println("<tr><th>Name</th><th>City</th><th>Hobbies</th></tr>");
        ArrayList<String> students = MysqlConnector.students();
        for (String studentInfo : students) {
            String[] cells = studentInfo.split(",");
            out.println("<tr><td>" + cells[1] + " " + cells[2] + "</td><td>" + cells[3] + "</td><td>" + cells[4] + "</td></tr>");
        }
        out.println("</table>");
        out.println("</div>");
        out.println("<span id=spacer></span>");

        out.println("<h2> Please add a student</h2>");
        out.println("<form action=/addstudents method=POST>");
        out.println("<label for=fname>First Name:</label>");
        out.println("<input pattern=[a-zA-Zäåö]* type=text name=fname placeholder=John><br><br>");
        out.println("<label for=fname>Last name:</label>");
        out.println("<input pattern=[a-zA-Z-äöå]* type=text name=lname placeholder= Smith ><br><br>");
        out.println("<label for=city>City:</label>");
        out.println("<input pattern=[a-zA-Z-äöå]* type=text name=city placeholder=Middelfart> <br><br>");
        out.println("<label for=hobbies>Hobbies:</label>");
        out.println("<input pattern=[a-zA-Z-äåö]* type=text name=hobbies placeholder=DnD ><br><br>");
        out.println("<input type=submit value=Add Student>");
        out.println("</form>");

        out.println("</main>");
        out.println("</body>");
        out.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String city = req.getParameter("city");
        String hobbies = req.getParameter("hobbies");

        if (!fname.isEmpty() || !lname.isEmpty()) {
            if (city.isEmpty()) {
                city = " ";
            }
            if (hobbies.isEmpty()) {
                hobbies = " ";
            }
            boolean alreadyExist = MysqlConnector.addStudent(fname, lname, city, hobbies);
            if (alreadyExist) {

                resp.sendRedirect("/addstudents?message=<p>Successfully added " + fname + " " + lname + " to database!</p>");

            } else {
                resp.sendRedirect("/addstudents?message=<p><mark>" + fname + " " + lname + " already exist in the database! Please try again.</mark></p>");

            }
        } else {
            resp.sendRedirect("/addstudents?message=<center>" +
                    "<h1><mark> Invalid Input! Please try again</mark></h1>" +
                    "<span id=spacer></span>" +
                    "<div class=error-image>" +
                    "<img src=DKke_error.gif alt=error>" +
                    "</div>" +
                    "</center>");
        }
    }
}
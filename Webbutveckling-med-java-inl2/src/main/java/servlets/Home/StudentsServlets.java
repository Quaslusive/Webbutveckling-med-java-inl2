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


@WebServlet(urlPatterns = "/students")
public class StudentsServlets extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = req.getParameter("message");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Info</title>");
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
        out.println("<h1>Students Info</h1>");
        out.println("</div>");

        out.println("<main>");

        if (message != null) {
            out.println(message);
        }
        out.println("<div>");
        out.println("<table border=1>");
        out.println("<tr><th>Student ID</th><th>Name</th><th>City</th><th>Hobbies</th></tr>");
        ArrayList<String> students = MysqlConnector.students();
        for (String studentInfo : students) {
            String[] cells = studentInfo.split(",");
            out.println("<tr><td>" + cells[0] + "</td><td>" + cells[1] + " " + cells[2] + "</td><td>" + cells[3] + "</td><td>" + cells[4] + "</td></tr>");
        }
        out.println("</table>");
        out.println("</div>");

        out.println("<br>");
        out.println("<span id=spacer></span>");
        out.println("<h2>Searching student course enrollment</h2>");
        out.println("<div>");
        out.println("<form action=/students method=POST>");
        out.println("<label for=fname>First Name:</label>");
        out.println("<input pattern=[a-zA-Z-äåö]* type=text name=fname placeholder=John required> <br><br>");
        out.println("<label for=fname>Last Name:</label>");
        out.println("<input pattern=[a-zA-Z-äåö]* type=text name=lname placeholder=Cardholder required> <br><br>");
        out.println("<center>");
        out.println("<input type=submit id=submit value=Search>");
        out.println("</center>");
        out.println("</form>");
        out.println("</div>");
        out.println("<br>");

        out.println("</main>");
        out.println("<br>");
        out.println("</body>");
        out.println("</html>");

        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");

        ArrayList<String> studentSearch = MysqlConnector.studentSearch(fname, lname);
        if (!studentSearch.isEmpty()) {

            StringBuilder result = new StringBuilder();
            for (String student : studentSearch) {
                String[] cells = student.split(",");
                result.append("<tr><td>").append(cells[2]).append("</td><td> ").append(cells[3]).append("</td><td>").append(cells[4]).append("</td></tr>");
            }

            resp.sendRedirect("/students?message=<center>" +
                    "<h1>Searching student: Success</h1>" +
                    "</center>" +
                    "<h2>Here are the courses that Mr/Miss/X " + fname + " " + lname + " is enrolled in.</h2>" +
                    "<br>" +
                    "<table border=1>" +
                    "<tr><th>Course Name</th><th>YHP</th><th>Info</th></tr>" +
                    result +
                    "</table>");

        } else {
            resp.sendRedirect("/students?message=<center>" +
                    "<h1><mark>" + fname + " " + lname + " was not found! Please try again</mark></h1>" +
                    "<span id=spacer></span>" +
                    "<div class=error-image>" +
                    "<img src=DKke_error.gif alt=error>" +
                    "</div>" +
                    "</center>");
        }
    }
}
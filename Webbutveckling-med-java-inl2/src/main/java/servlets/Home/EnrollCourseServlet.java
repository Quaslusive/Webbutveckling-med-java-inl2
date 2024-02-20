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

@WebServlet(name = "EnrollCourseServlet", urlPatterns = "/enrollcourse")
public class EnrollCourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = req.getParameter("message");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Enroll Student on a Course!</title>");
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
        out.println("<h1>Enroll Student on a Course!</h1>");
        out.println("</div>");

        out.println("<main>");

        if (message != null) {
            out.println(message);
        }
        out.println("<div>");
        out.println("<table border=1>");
        out.println("<br>");
        out.println("<center>");
        out.println("<h2>Students List</h2>");
        out.println("</center>");
        out.println("<tr><th>Student ID</th><th>Name</th><th>City</th><th>Hobbies</th></tr>");
        ArrayList<String> students = MysqlConnector.students();
        for (String studentInfo : students) {
            String[] cells = studentInfo.split(",");
            out.println("<tr><td>" + cells[0] + "</td><td>" + cells[1] + " " + cells[2] + "</td><td>" + cells[3] + "</td><td>" + cells[4] + "</td></tr>");
        }
        out.println("</table>");
        out.println("</div>");
        out.println("<span id=spacer></span>");

        out.println("<div>");
        out.println("<br>");
        out.println("<center>");
        out.println("<h2>Courses List</h2>");
        out.println("</center>");
        out.println("<table border=1>");
        out.println("<tr><th>Course ID</th><th>Name</th><th>YHP</th><th>Info</th></tr>");
        ArrayList<String> courses = MysqlConnector.courses();
        for (String coursesInfo : courses) {
            String[] cells = coursesInfo.split(",");
            out.println("<tr><td>" + cells[0] + "</td><td>" + cells[1] + "</td><td>" + cells[2] + "</td><td>" + cells[3] + "</td></tr>");
        }
        out.println("</table>");
        out.println("</div>");

        out.println("<span id=spacer></span>");
        out.println("<h2>Insert ONLY numbers that corresponds with ID # </h2>");
        out.println("<br>");
        out.println("<div>");
        out.println("<center>");
        out.println("<form action=/enrollcourse method=post >");
        out.println("<label for=studentID>Student ID:</label><br>");
        out.println("<input type=number min=1  max=100 name=studentID><br>");
        out.println("<label for=course ID:>Course ID:</label><br>");
        out.println("<input type=number min=1 max=50 name=courseID><br>");
        out.println("<input type=submit value=Add>");
        out.println("</form>");
        out.println("</center>");
        out.println("</div>");

        out.println("</main>");
        out.println("<br>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int studentID = Integer.parseInt(req.getParameter("studentID"));
        int courseID = Integer.parseInt(req.getParameter("courseID"));
        boolean alreadyExist = MysqlConnector.enrollStudent(studentID, courseID);


        if (alreadyExist) {
            MysqlConnector.enrollStudent(studentID, courseID);

            resp.sendRedirect("/enrollcourse?message=<center>" +
                    "<h2>Successfully enrolled student!</h2>" +
                    "</center>");

        } else {
            resp.sendRedirect("/enrollcourse?message=<p><mark>Student is already enrolled in that course! Please try again.</mark></p>");

        }
    }
}

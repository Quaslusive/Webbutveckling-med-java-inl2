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

@WebServlet(urlPatterns = "/courses")
public class CoursesServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Courses Info</title>");
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
        out.println("<h1>Courses List</h1>");
        out.println("</div>");

        out.println("<main>");
        out.println("<div>");
        out.println("<table border=1>");
        out.println("<tr><th>Course ID</th><th>Name</th><th>YHP</th><th>Info</th></tr>");
        ArrayList<String> courses = MysqlConnector.courses();
        for (String coursesInfo : courses) {
            String[] cells = coursesInfo.split(",");
            out.println("<tr><td>" + cells[0] + "</td><td>" + cells[1] + "</td><td>" + cells[2] + "</td><td>" + cells[3] + "</td></tr>");
        }
        out.println("</table>");
        out.println("</div>");

        out.println("</main>");
        out.println("</body>");
        out.println("</html>");

        out.close();

        System.out.println("Get request courses ");
    }
}

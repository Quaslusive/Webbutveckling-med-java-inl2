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

@WebServlet(name = "attendance", urlPatterns = "/attendance")
public class AttendanceServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Attendance Info</title>");
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
        out.println("<h1>Attendance List</h1>");
        out.println("</div>");

        out.println("<main>");
        out.println("<div>");
        out.println("<table border=1>");
        out.println("<tr><th>Name</th><th>Courses</th></tr>");
        ArrayList<String> attendance = MysqlConnector.attendance();
        for (String attendanceInfo : attendance) {
            String[] cells = attendanceInfo.split(",");
            out.println("<tr><td>" + cells[0] + " " + cells[1] + "</td><td>" + cells[2] + "</td></tr>");
        }
        out.println("</table>");
        out.println("</div>");
        out.println("</main>");
        out.println("</body>");
        out.println("</html>");

        System.out.println("Get request Närvaro ");
        // super.doGet(req, resp);
    }

}

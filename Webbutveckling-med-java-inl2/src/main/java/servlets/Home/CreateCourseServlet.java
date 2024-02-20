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


@WebServlet(name = "addcourses", urlPatterns = "/addcourses")
public class CreateCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String message = req.getParameter("message");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Add Courses</title>");
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
        out.println("<h1> Add Courses </h1>");
        out.println("</div>");

        out.println("<main>");

        if (message != null) {
            out.println(message);
        }
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
        out.println("<span id=spacer></span>");

        out.println("<h2> Please add a Course</h2>");
        out.println("<form action=/addcourses method=POST>");
        out.println("<label for=name>Course Name:</label>");
        out.println("<input pattern=[a-zA-Z-äåö]* type=text name=name ><br><br>");
        out.println("<label for=yhp>YHP:</label>");
        out.println("<input type=number min=0 max=666 name=yhp required><br><br>");
        out.println("<label for=info>Course Information (optional):</label>");
        out.println("<input pattern=[a-zA-Z-äåö]* type=text name=info><br><br>");
        out.println("<center>");
        out.println("<input type=submit value=Add Course>");
        out.println("</center>");
        out.println("</form>");

        out.println("</main>");
        out.println("</body>");
        out.println("</html>");

        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int yhp = Integer.parseInt(req.getParameter("yhp"));
        String info = req.getParameter("info");

        if (!name.isEmpty()) {
            if (info.isEmpty()) {
                info = " ";
            }
            boolean alreadyExist = MysqlConnector.addCourse(name, yhp, info);

            if (alreadyExist) {
                MysqlConnector.addCourse(name, yhp, info);
                resp.sendRedirect("/addcourses?message=<p>Successfully added " + name + " in database!</p>");

            } else {
                resp.sendRedirect("/addcourses?message=<p><mark>" + name + " already exist in database! Please try again.</mark></p>");

            }
        } else {
            resp.sendRedirect("/addcourses?message=<center>" +
                    "<h1><mark> Invalid Input! Please try again</mark></h1>" +
                    "<span id=spacer></span>" +
                    "<div class=error-image>" +
                    "<img src=DKke_error.gif alt=error>" +
                    "</div>" +
                    "</center>");
        }
    }
}
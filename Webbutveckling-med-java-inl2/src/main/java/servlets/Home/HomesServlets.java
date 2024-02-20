package servlets.Home;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "home", urlPatterns = "/home")
public class HomesServlets extends HttpServlet {
    /*
        static final String textStyle =
                "<meta charset= UTF-8 />" +
                "<meta name= viewport   content=  width=device-width, initial-scale=1.0 />" +
                "<link rel=stylesheet type=text/css href=style.css />" +
                "<link href=  https://fonts.googleapis.com/css2?family=Faster+One&family=Nabla&family=Slabo+27px&family=Tilt+Prism&display=swap " +
                "rel=  stylesheet >" +
                "<link href=  https://fonts.googleapis.com/css2?family=DotGothic16&family=Nabla&family=Slabo+27px&family=Tilt+Prism&display=swap " +
                "rel=  stylesheet >" +
                "<link href=  https://fonts.googleapis.com/css2?family=Chokokutai&family=DotGothic16&family=Nabla&family=Slabo+27px&family=Tilt+Prism&display=swap " +
                "rel=  stylesheet>" +
                "<link href=  https://fonts.googleapis.com/css2?family=Chokokutai&family=DotGothic16&family=Nabla&family=Notable&family=Slabo+27px&family=Tilt+Prism&display=swap" +
                "rel=  stylesheet>";

     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>The Academy of Vaporware</title>");
        //  out.println(textStyle);
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
        out.println("<h1>The Academy of <mark>Vaporware</mark></h1>");
        out.println("</div>");

        out.println("<main>");
        out.println("<h1> School Info </h1>");
        out.println("<span id=spacer></span>");

        out.println("<section>");
        out.println("<h2> Vår Historia <h2>");
        out.println("<p>");
        out.println("Lorem ipsum dolor sit amet consectetur adipisicing elit. Vitae velit consectetur corrupti inventore fugit.");
        out.println("earum porro consequuntur error veritatis, velit cupiditate hic itaque! Totam molestiae blanditiis nesciunt");
        out.println("deserunt voluptates.");
        out.println("Facere beatae impedit expedita nam illo accusamus, eveniet nihil vitae id? Veniam atque quae laboriosam ab in,");
        out.println("eserunt eaque necessitatibus eius libero earum? Accusamus, molestias error. Ullam quia quasi in.");
        out.println("</p>");
        out.println("</section>");

        out.println("<section>");
        out.println("<h2> Nu och Framtiden<h2>");
        out.println("<p>");
        out.println("Lorem ipsum dolor sit amet consectetur adipisicing elit. Vitae velit consectetur corrupti inventore fugit.");
        out.println("earum porro consequuntur error veritatis, velit cupiditate hic itaque! Totam molestiae blanditiis nesciunt");
        out.println("deserunt voluptates.");
        out.println("Facere beatae impedit expedita nam illo accusamus, eveniet nihil vitae id? Veniam atque quae laboriosam ab in,");
        out.println("eserunt eaque necessitatibus eius libero earum? Accusamus, molestias error. Ullam quia quasi in.");
        out.println("</p>");
        out.println("</section>");

        out.println("</main>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}

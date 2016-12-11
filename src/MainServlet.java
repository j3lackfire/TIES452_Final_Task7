import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Le Pham Minh Duc on 12/11/2016.
 */
@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String query = request.getParameter("sparql-query");

        try (PrintWriter writer = response.getWriter()) {

            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>Website title</title>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h1> SPARQL Query </h1>");
            writer.println("<p>" + RDFGetter.formatQuery(query) + "</p>");
            writer.println("<h2> ------------------------------ </h2>");
            writer.println("<h1> RESULT </h1>");
            RDFGetter.Initialize(query);
            writer.println("<p>" + RDFGetter.formatNamespace(RDFGetter.resultString) + "</p>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }

}

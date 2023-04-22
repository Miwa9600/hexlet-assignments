package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");

        String search = request.getParameter("search");

        List<String> companies = getCompanies().stream()
                .filter(company -> search == null || company.contains(search))
                .toList();

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Companies List</title></head>");
        out.println("<body>");
        if (companies.isEmpty()) {
            out.println("Companies not found");
        } else {
            out.println("<ul>");
            for (String company : companies) {
                out.println("<li>" + company + "</li>");
            }
            out.println("</ul>");
        }
        out.println("</body></html>");
        out.close();
    }
}

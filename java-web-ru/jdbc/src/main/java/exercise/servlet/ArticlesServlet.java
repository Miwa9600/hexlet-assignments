package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        int pageSize = 10;
        int pageNumber = 1;
        String pageString = request.getParameter("page");
        if (pageString != null) {
            pageNumber = Integer.parseInt(pageString);
        }
        int offset = (pageNumber - 1) * pageSize;

        List<Map<String, Object>> articles = new ArrayList<Map<String, Object>>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM articles ORDER BY id LIMIT ? OFFSET ?")) {
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> article = new HashMap<String, Object>();
                article.put("id", resultSet.getInt("id"));
                article.put("title", resultSet.getString("title"));
                article.put("body", resultSet.getString("body"));
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new ServletException("Unable to retrieve articles", e);
        }

        request.setAttribute("articles", articles);
        request.setAttribute("currentPage", pageNumber);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        int articleId = Integer.parseInt(getId(request));
        Map<String, Object> article = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM articles WHERE id = ?")) {
            statement.setInt(1, articleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                article = new HashMap<String, Object>();
                article.put("id", resultSet.getInt("id"));
                article.put("title", resultSet.getString("title"));
                article.put("body", resultSet.getString("body"));
            }
        } catch (SQLException e) {
            throw new ServletException("Unable to retrieve article with ID " + articleId, e);
        }

        if (article == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}

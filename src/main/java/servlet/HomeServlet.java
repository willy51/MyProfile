package servlet;
import dao.CourseDAO;
import entities.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        JakartaServletWebApplication application = JakartaServletWebApplication.buildApplication(req.getServletContext());
        WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(templateResolver);

        WebContext context = new WebContext(application.buildExchange(req, resp));

        List<Course> courses = CourseDAO.getParcours();
        courses.sort(Comparator.comparing(Course::getDateFin).reversed());
        context.setVariable("courses", courses);

        engine.process("Home", context, resp.getWriter());
    }
}

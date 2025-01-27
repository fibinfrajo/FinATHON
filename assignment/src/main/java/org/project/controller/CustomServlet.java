package org.project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.configuration.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@WebServlet(
        name = "AnnotationExample",
        description = "Example Servlet Using Annotations",
        urlPatterns = {"/AnnotationExample"}
)
public class CustomServlet extends HttpServlet {
    private ApplicationContext context;

    @Override
    public void init() throws ServletException {
        // Initialize Spring Application Context
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Example of using a Spring bean
        Controller service = context.getBean(Controller.class);
        resp.getWriter().write("Response from Custom Servlet: " );
    }
}

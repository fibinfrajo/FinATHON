package org.project;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.project.configuration.AppConfig;
import org.project.controller.Controller;
import org.project.controller.CustomServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        System.out.println("Hello, World!");
        // Create Tomcat instance
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Set the base directory
        tomcat.setBaseDir("temp");

        // Create a Spring context
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.example.config"); // Replace with your package

        // Register DispatcherServlet
        CustomServlet dispatcherServlet = new CustomServlet();
        tomcat.addServlet("", "dispatcherServlet", dispatcherServlet);
        tomcat.getHost().setAppBase("."); // Set app base to current directory
        tomcat.addContext("", null).addServletMappingDecoded("/", "dispatcherServlet");

        // Start Tomcat
        tomcat.start();
        tomcat.getServer().await();
    }
}


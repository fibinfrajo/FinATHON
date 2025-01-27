package org.project;

import org.project.config.SpringJdbcConfig;
import org.project.repository.UserDao;
import org.project.repository.UserModel;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, World!");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
        UserDao userDao = context.getBean(UserDao.class);

        // Insert employee
        UserModel user = new UserModel("Alice Johnson", "85000");
        System.out.println("User Updated!");

        // Retrieve employee
        UserModel user1 = userDao.getUserByName(user.getName());
        System.out.println("user Retrieved, password: " + user1.getPassword());


        context.close();
    }
}

package org.project;

import org.project.config.SpringJdbcConfig;
import org.project.repository.UserDao;
import org.project.repository.UserModel;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    //Used for checking DB-connection
    public static void main(String[] args) {

        System.out.println("Hello, World!");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
        UserDao userDao = context.getBean(UserDao.class);

        UserModel user = new UserModel("Alice Johnson", "85000");

//        UserModel user1 = userDao.getUserByName(user.getName());
//        System.out.println("user Retrieved, password: " + user1.getPassword());


        context.close();
    }
}

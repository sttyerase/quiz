package com.dbmi.demos.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;

@SpringBootApplication
public class QuizApplication {

    public static void main(String[] args) {
        SpringApplication quizApp = new SpringApplication();
        java.util.Set<ApplicationContextInitializer<?>> quizSet = quizApp.getInitializers();
        for (ApplicationContextInitializer<?> anI: quizSet){
            System.out.println("  CONTEXT INIT: " + anI.toString());
        } // FOR
        SpringApplication.run(QuizApplication.class, args);
    } // MAIN(STRING[])

} // CLASS

package com.dbmi.demos.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;

@SpringBootApplication
public class QuizApplication {

    private static final Logger myLogger = LoggerFactory.getLogger(QuizApplication.class);

    public static void main(String[] args) {
        SpringApplication quizApp = new SpringApplication();
        java.util.Set<ApplicationContextInitializer<?>> quizSet = quizApp.getInitializers();
        for (ApplicationContextInitializer<?> anI: quizSet){
            myLogger.debug("  CONTEXT INIT: " + anI.toString());
        } // FOR
        SpringApplication.run(QuizApplication.class, args);
    } // MAIN(STRING[])

} // CLASS

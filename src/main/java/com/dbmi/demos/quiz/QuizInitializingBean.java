package com.dbmi.demos.quiz;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.logging.Logger;

@Component
public class QuizInitializingBean implements InitializingBean {
    private Logger myLogger = Logger.getLogger("quizapplication.log");

    @Value("${quiz.quizpath}")
    private String quizpath;

    @Autowired
    ServletContext myServletContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        myLogger.info("QUIZINITIALIZINGBEAN: " + this.toString());
    } // AFTERPROPERTIESSET()

} // CLASS

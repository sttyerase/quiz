package com.dbmi.demos.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

@Component
public class QuizInitializingBean implements InitializingBean {
    private final Logger myLogger = LoggerFactory.getLogger(QuizInitializingBean.class);

    @Autowired
    ServletContext myServletContext;

    @Override
    public void afterPropertiesSet() {
        myLogger.info("QUIZINITIALIZINGBEAN: " + this);
    } // AFTERPROPERTIESSET()

} // CLASS

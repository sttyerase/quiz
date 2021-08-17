package com.dbmi.demos.quiz;

import com.dbmi.demos.quiz.model.QuestionList;
import com.dbmi.demos.quiz.model.Quiz;
import com.dbmi.demos.quiz.model.QuizParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.logging.Logger;

@Component
public class QuizApplicationRunner implements ApplicationRunner {

    private final Logger myLogger = Logger.getLogger("quizapplication.log");

    @Value("${quiz.quizpath}")
    private String quizpath;

    @Autowired
    ServletContext myServletContext;

    @Autowired
    private ResourceLoader myResourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        myLogger.info("QUIZAPPLICATIONRUNNER: loading quiz list from path " + quizpath);
        myLogger.info("QUIZAPPLICATIONRUNNER: servlet context name:       " + myServletContext.getServletContextName());
        // CREATE FILE RESOURCE TO ACCESS QUIZ DOCUMENTS
        final Resource fileResource = myResourceLoader.getResource("classpath:" + quizpath);
        File quizdir = fileResource.getFile();
        myLogger.info("QUIZAPPLICATIONRUNNER: quiz directory location:    " + quizdir);
        String[] quizfiles = quizdir.list((d, s) -> s.toLowerCase().endsWith(".quiz"));
        myLogger.info("QUIZAPPLICATIONRUNNER: number of quiz files:       " + quizfiles.length);
        Quiz myNewQuiz = new Quiz(quizpath.toString(),quizfiles[0]);
        myNewQuiz.fillQuestionList();
    } // RUN(APPLICATIONARGUMENTS)

} // CLASS

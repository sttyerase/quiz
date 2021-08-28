package com.dbmi.demos.quiz;

import com.dbmi.demos.quiz.model.Quiz;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;

@Component
public class QuizApplicationRunner implements ApplicationRunner {

    private final Logger myLogger = Logger.getLogger("quizapplication.log");

    @Value("${quiz.quizpath}")
    private String quizpath;

    private final ResourceLoader myResourceLoader = new DefaultResourceLoader(this.getClass().getClassLoader());
    private Vector<Quiz> theQuizes = new Vector<>(200);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        myLogger.info("QUIZAPPLICATIONRUNNER: loading quiz list from path " + quizpath);
        myLogger.info("QUIZAPPLICATIONRUNNER: classpath: " + this.getClass().getResource("Quiz01.quiz"));
        // CREATE FILE RESOURCE TO ACCESS QUIZ DOCUMENTS
        final Resource fileResource = myResourceLoader.getResource("classpath:" + quizpath);
        myLogger.info("QUIZAPPLICATIONRUNNER: file name: " + fileResource.getFilename() + " existence = " + fileResource.exists());
        File quizdir = fileResource.getFile();
        myLogger.info("QUIZAPPLICATIONRUNNER: quiz directory location:    " + quizdir);
        String[] quizfiles = quizdir.list((d, s) -> s.toLowerCase().endsWith(".quiz"));
        myLogger.info("QUIZAPPLICATIONRUNNER: number of quiz files:       " + quizfiles.length);
        for(String indx : quizfiles){
            Quiz myNewQuiz = new Quiz(quizdir + "/" + indx);
            myNewQuiz.fillQuestionList();
            theQuizes.add(myNewQuiz);
        } // FOR
    } // RUN(APPLICATIONARGUMENTS)

} // CLASS

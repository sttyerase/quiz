package com.dbmi.demos.quiz;

import com.dbmi.demos.quiz.model.Quiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Vector;

@Component
public class QuizApplicationRunner implements ApplicationRunner {

    @Value("${quiz.quizpath}")
    private String quizpath;

    private final Logger myLogger = LoggerFactory.getLogger(ApplicationRunner.class);
    private final ResourceLoader myResourceLoader = new DefaultResourceLoader(this.getClass().getClassLoader());
    private final Vector<Quiz>   theQuizes        = new Vector<>(200);

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("application");  // basename = name of file that contains the properties, e.g., application.properties
        return messageSource;
    } // MESSAGESOURCE()

    @Bean
    Vector<Quiz> getTheQuizes(){
        return theQuizes;
    } // GETTHEQUIZES()

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // MANAGE LOGGING LEVEL SETTING TO ALLOW CHANGE FROM THE COMMAND LINE
        myLogger.debug("QUIZAPPLICATIONRUNNER: QuizController.log log level " + myLogger.getName());
        myLogger.debug("QUIZAPPLICATIONRUNNER: loading quiz list from path " + quizpath);
        // LOOK FOR PATH TO QUIZ DOCUMENTS
        myLogger.debug("QUIZAPPLICATIONRUNNER: classpath: " + System.getProperty("java.class.path"));
        // CREATE FILE RESOURCE TO ACCESS QUIZ DOCUMENTS
        final Resource fileResource = myResourceLoader.getResource("classpath:" + quizpath);
        myLogger.debug("QUIZAPPLICATIONRUNNER: file name: " + fileResource.getFilename() + " existence = " + fileResource.exists());
        File quizdir = fileResource.getFile();
        myLogger.debug("QUIZAPPLICATIONRUNNER: quiz directory location:    " + quizdir);
        String[] quizfiles = quizdir.list((d, s) -> s.toLowerCase().endsWith(".quiz"));
        if(quizfiles != null) {
            myLogger.info("QUIZAPPLICATIONRUNNER: number of quiz files to process: " + quizfiles.length);
            // PARSE QUIZ FILES. CREATE QUIZ OBJECTS.
            for(String indx : quizfiles){
                Quiz myNewQuiz = new Quiz(quizdir + "/" + indx);
                myLogger.info("    new quiz name: " + myNewQuiz.getQuizName());
                theQuizes.add(myNewQuiz);
            } // FOR
        } else {
            myLogger.info("QUIZAPPLICATIONRUNNER: NO QUIZ FILES TO PROCESS!!  Exiting now.");
            System.exit(19); // TODO: Do you really want to do this?
        } // IF-ELSE
    } // RUN(APPLICATIONARGUMENTS)

} // CLASS

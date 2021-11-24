package com.dbmi.demos.quiz.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class QuizParser {

    private final Quiz   theQuiz;
    private final Logger myLogger  = LoggerFactory.getLogger(QuizParser.class);

    public QuizParser(Quiz aQuiz) {
        this.theQuiz = aQuiz;
    } // CONSTRUCTOR(QUIZ)

    public void parse(String aURI) throws QuizException {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            saxParserFactory.setValidating(true);
            SAXParser myParser = saxParserFactory.newSAXParser();
            myParser.parse(new File(aURI),new QuizParseHandler(theQuiz));
        } catch (ParserConfigurationException pce) {
            throw new QuizException("encountered configuration exception while creating parser: " + pce);
        } catch (SAXException se) {
            throw new QuizException("encountered SAXException while creating parser: " + se);
        } catch (IOException ioe) {
            throw new QuizException("IO exception while creating parser: " + ioe);
        } // TRY-CATCH
        myLogger.debug("QUIZPARSER:   parsing complete:     " + theQuiz.getQuizName());
    } // PARSECONFIG(PROBE)
} // CLASS

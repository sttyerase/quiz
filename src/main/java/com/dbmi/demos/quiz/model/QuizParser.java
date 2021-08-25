package com.dbmi.demos.quiz.model;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class QuizParser {

    private Quiz theQuiz = null;

    public QuizParser(Quiz aQuiz) {
        this.theQuiz = aQuiz;
    } // CONSTRUCTOR(QUIZ)

    public void parse(String aURI) throws QuizException {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            SAXParser myParser = saxParserFactory.newSAXParser();
            myParser.parse(new File(aURI),new QuizParseHandler(theQuiz));
        } catch (ParserConfigurationException pce) {
            throw new QuizException("encountered configuration exception while creating parser: " + pce);
        } catch (SAXException se) {
            throw new QuizException("encountered SAXException while creating parser: " + se);
        } catch (IOException ioe) {
            throw new QuizException("IO exception while creating parser: " + ioe);
        } // TRY-CATCH
    } // PARSECONFIG(PROBE)
} // CLASS

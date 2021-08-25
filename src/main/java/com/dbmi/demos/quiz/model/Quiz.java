package com.dbmi.demos.quiz.model;

import org.xml.sax.SAXException;

import java.util.logging.Logger;

/**
 * @author Daniel B. Moore
 */
public class Quiz{
   /** List of questions for this Quiz. */
   private final String theDocument;
   private final QuestionList myQuestionList;
   private final Logger myLogger;

   public Quiz(String aQuizDoc){
      myQuestionList = new QuestionList(200);
      this.theDocument = aQuizDoc;
      myLogger = Logger.getLogger("quizapplication.log");
   } // CONSTRUCTOR(STRING)

   /** This method calls the XML parser and builds Question
    *  elements to place into the QuestionList.
    */
   public void fillQuestionList() throws QuizException{
      myLogger.info("QUIZ document:         " + theDocument);
      QuizParser myParser = new QuizParser(this);
      try {
         myParser.parse(theDocument);
      }catch(QuizException qe) {
         throw new QuizException("Caught parsing error: " + qe);
      } // TRY-CATCH
   } // FILLQUESTIONLIST()

   public QuestionList getQuestionList() {
      return myQuestionList;
   } // GETQUESTIONLIST()

} // CLASS

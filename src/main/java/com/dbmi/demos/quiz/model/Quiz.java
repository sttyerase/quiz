package com.dbmi.demos.quiz.model;

import org.xml.sax.SAXException;

/**
 * @author Daniel B. Moore
 */
public class Quiz{
   /** List of questions for this Quiz. */
   private final String theDocument;
   private final String quizPath;
   private final QuestionList myQuestionList;

   public Quiz(String quizPath, String theDocument){
      myQuestionList = new QuestionList(200);
      this.quizPath = quizPath;
      this.theDocument = theDocument;
   } // CONSTRUCTOR(STRING)

   /** This method calls the XML parser and builds Question
    *  elements to place into the QuestionList.
    */
   public void fillQuestionList() throws QuizException{
      QuizParser myParser = new QuizParser(this);
      try {
         myParser.parse(quizPath + "/" + theDocument);
      }catch(SAXException se) {
         throw new QuizException("Caught parsing error: " + se);
      }catch(java.io.IOException ioe) {
         throw new QuizException("Caught IO error in parser: " + ioe);
      } // TRY-CATCH
   } // FILLQUESTIONLIST()

   public QuestionList getQuestionList() {
      return myQuestionList;
   } // GETQUESTIONLIST()

} // CLASS

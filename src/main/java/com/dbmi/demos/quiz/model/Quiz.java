/**
 * @author Daniel B. Moore
 */

package com.dbmi.demos.quiz.model;

import org.xml.sax.SAXException;

/**
 * @author Daniel B. Moore
 */
public class Quiz{
   /** List of questions for this Quiz. */
   private String theDocument = "";
   private String quizPath = "";
   private String quizName = "";

   public Quiz(String quizPath,String theDocument){
      super();
      this.quizPath = quizPath;
      this.theDocument = theDocument;
      quizName = theDocument.substring(0,theDocument.lastIndexOf(".quiz"));
   } // CONSTRUCTOR(STRING)

   /** This method calls the XML parser and builds Question
    *  elements to place into the QuestionList.
    */
   public void fillQuestionList(QuestionList qList) throws QuizException{
      QuizParser myParser = new QuizParser(this, qList);
      try {
         myParser.parse(quizPath + theDocument);
      }catch(SAXException se) {
         throw new QuizException("Caught parsing error: " + se);
      }catch(java.io.IOException ioe) {
         throw new QuizException("Caught IO error in parser: " + ioe);
      } // TRY-CATCH
   } // CREATEQUESTIONLIST

   public String getDocName(){
      return theDocument;
   } // GETDOCNAME()

   public String getQuizName(){
      return quizName;
   } // GETQUIZNAME()

   /** set the document name to String. **/
   public void setDocName(String theDocument){
      this.theDocument = theDocument;
   } // SETDOCNAME(STRING)

   /** set the quiz name to String */
   public void setQuizName(String quizName){
      this.quizName = quizName;
   } // SETQUIZNAME(STRING)

} // CLASS

package com.dbmi.demos.quiz.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel B. Moore
 */
public class Quiz{
   /** List of questions for this Quiz. */
   private final String       theDocument;
   private final QuestionList myQuestionList;
   private final Logger myLogger = LoggerFactory.getLogger(Quiz.class);
   private       String       quizName = "";

   public Quiz(String aQuizDoc){
      myQuestionList = new QuestionList(200);
      this.theDocument = aQuizDoc;
   } // CONSTRUCTOR(STRING)

   /** This method calls the XML parser and builds Question
    *  elements to place into the QuestionList.
    */
   public void createQuiz() throws QuizException{
      myLogger.info("QUIZ document:         " + theDocument);
      QuizParser myParser = new QuizParser(this);
      try {
         myParser.parse(theDocument);
      }catch(QuizException qe) {
         throw new QuizException("Caught parsing error: " + qe);
      } // TRY-CATCH
      myLogger.debug("QUIZ:: name: " + this.getQuizName() + " :: num questions:   " + myQuestionList.getNumberOfQuestions());
   } // FILLQUESTIONLIST()

   public QuestionList getQuestionList() {
      return myQuestionList;
   } // GETQUESTIONLIST()

   public String getQuizName() {
      return quizName;
   } // GETQUIZNAME()

   public void setQuizName(String quizName) {
      this.quizName = quizName;
   } // SETQUIZNAME(STRING)

} // CLASS

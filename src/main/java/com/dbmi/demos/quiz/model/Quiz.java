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
   private       String       quizName = "";
   private final Logger       myLogger = LoggerFactory.getLogger(Quiz.class);
   private final ScoreKeeper  scoreKeeper = new ScoreKeeper();

   /**
    * Constructor to build a quiz
    * @param aQuizDoc XML document that conforms to the Quiz schema (Quiz.xsd)
    */
   public Quiz(String aQuizDoc) throws QuizException {
      myQuestionList = new QuestionList(200);
      this.theDocument = aQuizDoc;
      try {
         this.createQuiz();
      } catch (QuizException qe) {
         throw new QuizException("Exception creating quiz: " + qe);
      } // TRY-CATCH
   } // CONSTRUCTOR(STRING)

   /** This method calls the XML parser to build Question
    *  objects from the document content.
    */
   public void createQuiz() throws QuizException {
      myLogger.info("QUIZ document:         " + theDocument);
      QuizParser myParser = new QuizParser(this);
      try {
         myParser.parse(theDocument);
      }catch(QuizException qe) {
         throw new QuizException("Caught parsing error: " + qe);
      } // TRY-CATCH
      myLogger.debug("QUIZ:: name: " + this.getQuizName() + " :: num questions:   " + myQuestionList.getNumberOfQuestions());
   } // FILLQUESTIONLIST()

   // GETTERS AND SETTERS
   public QuestionList getQuestionList() {
      return myQuestionList;
   } // GETQUESTIONLIST()

   public String getQuizName() {
      return quizName;
   } // GETQUIZNAME()

   public void setQuizName(String quizName) {
      this.quizName = quizName;
   } // SETQUIZNAME(STRING)

   public ScoreKeeper getScoreKeeper() {
      return scoreKeeper;
   } // GETSCOREKEEPER()

} // CLASS

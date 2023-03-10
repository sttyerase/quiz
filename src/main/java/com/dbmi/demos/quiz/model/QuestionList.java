package com.dbmi.demos.quiz.model;

import java.util.*;

/*
 * @author Daniel B. Moore <p>
 */
public class QuestionList {
   private int currentQuestionNumber = 0;
   private final LinkedList<Question> theQuestions;

   public QuestionList(){
      super();
      theQuestions = new LinkedList<>();
   } // CONSTRUCTOR(NUM)

   public int getCurrentQuestionNumber(){
      return currentQuestionNumber;
   } // GETCURRENTQUESTIONNUMBER()

   public void setCurrentQuestionNumber(int number){
      this.currentQuestionNumber = number;
   } // SETCURRENTQUESTIONNUMBER(INT)

   public LinkedList<Question> getTheQuestions() {
      return theQuestions;
   } // GETTHEQUESTIONLIST()

   public boolean notDone(){
      return this.currentQuestionNumber < theQuestions.size();
   } // NOTDONE()
   
   public void initQuestionList(){
      Question aQ;
      Iterator<Question> myIt = theQuestions.iterator();
      while(myIt.hasNext()) {
         aQ = myIt.next();
         aQ.setAnswered(false);
         aQ.setAnsweredCorrectly(false);
      } // WHILE
      this.setCurrentQuestionNumber(0);
   } // INITQUESTIONLIST()

   public int getNumberOfQuestions(){
      return theQuestions.size();
   } // GETNUMBEROFQUESTIONS()

} // CLASS

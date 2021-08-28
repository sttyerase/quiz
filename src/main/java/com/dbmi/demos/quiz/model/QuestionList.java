package com.dbmi.demos.quiz.model;

import org.springframework.context.annotation.Bean;

import java.util.*;

/*
 * @author Daniel B. Moore <p>
 */
public class QuestionList {
   private static final long serialVersionUID = 387094158321L;
   private int currentQuestionNumber = 0;
   private Vector<Question> theQuestions;

   public QuestionList(int num){
      super();
      theQuestions = new Vector<>(num);
   } // CONSTRUCTOR(NUM)

   public int getCurrentQuestionNumber(){
      return currentQuestionNumber;
   } // GETCURRENTQUESTIONNUMBER()

   public void setCurrentQuestionNumber(int number){
      this.currentQuestionNumber = number;
   } // SETCURRENTQUESTIONNUMBER(INT)

   public Vector<Question> getTheQuestions() {
      return theQuestions;
   } // GETTHEQUESTIONLIST()

   public void setTheQuestions(Vector<Question> theQuestions) {
      this.theQuestions = theQuestions;
   } // SETTHEQUESTIONLIST(VECTOR)

   public boolean notDone(){
      return this.currentQuestionNumber < theQuestions.size();
   } // NOTDONE()
   
   public void initQuestionList(){
      Question aQ;
      Enumeration<Question> e = theQuestions.elements();
      while(e.hasMoreElements()){
         aQ = e.nextElement();
         aQ.setAnswered(false);
         aQ.setAnsweredCorrectly(false);
      } // WHILE
         
   } // INITQUESTIONLIST()

   public int getNumberOfQuestions(){
      return theQuestions.size();
   } // GETNUMBEROFQUESTIONS()

} // CLASS

package com.dbmi.demos.quiz.model;

import java.beans.JavaBean;
import java.util.Vector;

/*
 * @author Daniel B. Moore <p>
 * <p>A class that models the components of a quiz question, containing the
 * question, the possible answers and an identifier of the correct answer. </p>
 */
@JavaBean
public class Question
{
   /** The text of the question */
   private String questionText = "";
   /** A list of possible choices. */
   private final Vector<String> choiceList = new Vector<>(100);
   /** The index of the correct answer*/
   private int theAnswer = 0;
   /** The explanation of the correct answer */
   private String explanation = null;
   /** Is this question answered? */
   private boolean answered = false;
   /** Was it answered correctly? */
   private boolean answeredCorrectly = false;

   /** The ordinal number of the current question in the quiz. **/
   private int questionNumber;

   /**
    *  @return a string with the text of the question.
    */
   public String getQuestionText(){
      return questionText;
   } // GETQUESTIONTEXT()

   public Vector<String> getChoiceList(){
      return choiceList;
   }

   public String[] getChoiceListArray() {
      return choiceList.toArray(String[]::new);
   }

   public String getChoiceAt(int i){
      return choiceList.elementAt(i);
   }

   /*
    * @return the String of the correct answer.
    */
   public String getCorrectAnswer(){
      return choiceList.elementAt(theAnswer);
   }

   public boolean getAnswered(){
      return answered;
   }

   public boolean getAnsweredCorrectly(){
      return answeredCorrectly;
   }

   public int getCorrectAnswerNumber(){
      return theAnswer;
   }

   public String getExplanation(){
      return explanation;
   }

   public String getChoiceElementAt(int i){
      return choiceList.elementAt(i);
   }

   public void addChoiceElement(String aChoice){
      choiceList.addElement(aChoice);
   }

   public void insertChoiceElementAt(String aChoice,int i){
      choiceList.insertElementAt(aChoice,i);
   } // INSERTCHOICEELEMENTAT(STRING,INT)

   public void setQuestionText(String text){
      this.questionText = text;
   } // SETQUESTIONTEXT(STRING)

   public void setAnswered(boolean torf){
      answered = torf;
   } // SETANSWERED(BOOLEAN)

   public void setAnsweredCorrectly(boolean yesno){
      answeredCorrectly = yesno;
   } // SETANSWEREDCORRECTLY(BOOLEAN)

   public void setExplanation(String expl){
      explanation = expl;
   } // SETEXPLANATION(STRING)

   public void setCorrectAnswerNumber(int aNum){
      this.theAnswer = aNum;
   } // SETCORRECTANSWERNUMBER

   public void trimChoiceToSize(){
      choiceList.trimToSize();
   } // TRIMCHOICETOSIZE

   public java.util.Enumeration<String> choiceElements(){
      return choiceList.elements();
   } // CHOICEELEMENTS()

   public int getQuestionNumber() {
      return questionNumber;
   }

   public void setQuestionNumber(int questionNumber) {
      this.questionNumber = questionNumber;
   }

} // CLASS

package com.dbmi.demos.quiz.model;

/*
 * @author Daniel B. Moore <p>
 */
public class ScoreKeeper
{
   private int totalAnswered;
   private int totalCorrect;
   private int totalQuestions;

   public ScoreKeeper(){
      super();
      totalAnswered = 0;
      totalCorrect = 0;
      totalQuestions = 0;
   } // Constructor

   public int getTotalAnswered(){
      return totalAnswered;
   }

   public int getTotalCorrect(){
      return totalCorrect;
   }
   
   public int getTotalQuestions(){
      return totalQuestions;
   }
   
   public void setTotalAnswered(int tot){
      totalAnswered = tot;
   }
   
   public void setTotalCorrect(int tot){
      totalCorrect = tot;
   }
   
   public void setTotalQuestions(int tot){
      totalQuestions = tot;
   } // SETTOTALQUESTIONS(INT)

   public void scoreQuiz(QuestionList qList){
      Question thisQ;
      totalAnswered = 0;
      totalCorrect = 0;
      totalQuestions = qList.getTheQuestions().size();
      java.util.Iterator<Question> myE = qList.getTheQuestions().iterator();
      while(myE.hasNext()){
         thisQ = myE.next();
         if(thisQ.getAnswered())totalAnswered++;
         if(thisQ.getAnsweredCorrectly())totalCorrect++;
      } // WHILE(MYE)
   } // SCOREQUIZ(QUESTIONLIST)

   public double getPercentCorrect(){
      return((double) totalCorrect/(double)totalAnswered) * 100;
   } // GETPERCENTCORRECT()
   
   public void init(){
      this.setTotalAnswered(0);
      this.setTotalCorrect(0);
   } // INIT()

} // CLASS

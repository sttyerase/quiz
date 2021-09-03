package com.dbmi.demos.quiz.model;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

@SuppressWarnings("unused")
public class QuizParseHandler extends DefaultHandler {
   private Quiz thisQuiz;
   private Question thisQ;
   private final QuestionList qList;
   private String charactersTempString = "";
   private int indx = 0;

   /** constructs a QuizParseHandler, passing the Quiz and QuestionList handles.*/
   public QuizParseHandler(Quiz aQuiz){
      this.setThisQuiz(aQuiz);
      this.qList = aQuiz.getQuestionList();
   } // CONSTRUCTOR

   /** Implements logic executed when a start of element is detected.
    *  Overrides startElement() in HandlerBase which does nothing.
    *  //@exception org.xml.sax.SaxException
    */
   public void startElement(String uri,String localName,String qName, Attributes attrs){
      switch(localName){
         case "question":
            thisQ  = new Question();
            break;
         case "questionText":
            charactersTempString = "";
            break;
         case "choiceList":
            setIndx(0);
            int va = Integer.parseInt(attrs.getValue("validAnswer"));
            thisQ.setCorrectAnswerNumber(va-1);
            break;
         case "choice":
            break;
         case "explanation":
            break;
         case "quiz":
            thisQuiz.setQuizName(attrs.getValue("name"));
            break;
      } // SWITCH
   } // STARTELEMENT()

   /** Return a String of parsed characters
    * //@exception org.xml.sax.SAXException
    */
   public void characters(char[] ch, int start, int length){
      charactersTempString = new String(ch,start,length);
   } // CHARACTERS()

   /** Implements logic executed when and end of element is detected.
    *  Overrides endElement() in HandlerBase which does nothing.
    *  //@exception org.xml.sax.SaxException
    */
   public void endElement(String uri,String localName,String qName){
      switch (localName){
         case "question":
            qList.getTheQuestions().addElement(thisQ);
            break;
         case "questionText":
            thisQ.setQuestionText(charactersTempString.replace('\n', ' '));
         case "choiceList":
            thisQ.trimChoiceToSize();
            break;
         case "choice":
            setIndx(getIndx() + 1);
            thisQ.addChoiceElement(charactersTempString.replace('\n', ' '));
            break;
         case "explanation":
            thisQ.setExplanation(charactersTempString.replace('\n', ' '));
            break;
         case "quiz":
            break;
         default:
            System.out.println("No match for: " + localName);
      } // SWITCH
   } // ENDELEMENT()

   /** Implements logic executed when an end of document is detected.
    *  Overrides endElement() in HandlerBase which does nothing.
    *  Trim the Question List to its exact number of elements
    * //@exception org.xml.sax.SAXException
    */
   public void endDocument(){
      qList.getTheQuestions().trimToSize();
   } // ENDDOCUMENT()

   public Quiz getThisQuiz() {
      return thisQuiz;
   } // GETTHISQUIZ()

   public void setThisQuiz(Quiz thisQuiz) {
      this.thisQuiz = thisQuiz;
   } // SETTHISQUIZ(QUIZ)

   public int getIndx() {
      return indx;
   } // GETINDX()

   public void setIndx(int indx) {
      this.indx = indx;
   } // SETINDX(INT)

} // CLASS

package com.dbmi.demos.quiz.model;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

@SuppressWarnings("unused")
public class QuizParseHandler extends DefaultHandler {
   private final Quiz theQuiz;
   private Question question;
   private final QuestionList qList;
   private String charactersTempString = "";
   private int indx = 0;

   /** constructs a QuizParseHandler, passing the Quiz and QuestionList handles.*/
   public QuizParseHandler(Quiz aQuiz){
      this.theQuiz = aQuiz;
      this.qList = theQuiz.getQuestionList();
   } // CONSTRUCTOR

   /** Implements logic executed when a start of element is detected.
    *  Overrides startElement() in HandlerBase which does nothing.
    *  //@exception org.xml.sax.SaxException
    */
   public void startElement(String uri,String localName,String qName, Attributes attrs){
      switch(localName){
         case "question":
            question = new Question();
            break;
         case "questionText":
            charactersTempString = "";
            break;
         case "choiceList":
            setIndx(0);
            int va = Integer.parseInt(attrs.getValue("validAnswer"));
            question.setCorrectAnswerNumber(va);
            charactersTempString = "";
            break;
         case "choice":
            break;
         case "explanation":
            charactersTempString = "";
            break;
         case "quiz":
            theQuiz.setQuizName(attrs.getValue("name"));
            break;
      } // SWITCH
   } // STARTELEMENT()

   /** Return a String of parsed characters
    * //@exception org.xml.sax.SAXException
    */
   public void characters(char[] ch, int start, int length){
      charactersTempString = new String(ch,start,length).strip();
   } // CHARACTERS()

   /** Implements logic executed when and end of element is detected.
    *  Overrides endElement() in HandlerBase which does nothing.
    *  //@exception org.xml.sax.SaxException
    */
   public void endElement(String uri,String localName,String qName){
      switch (localName){
         case "question":
            qList.getTheQuestions().addElement(question);
            break;
         case "questionText":
            question.setQuestionText(charactersTempString.replace('\n', ' '));
            break;
         case "choiceList":
            question.trimChoiceToSize();
            break;
         case "choice":
            setIndx(getIndx() + 1);
            question.addChoiceElement(charactersTempString.replace('\n', ' '));
            break;
         case "explanation":
            question.setExplanation(charactersTempString.replace('\n', ' '));
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

   public int getIndx() {
      return indx;
   } // GETINDX()

   public void setIndx(int indx) {
      this.indx = indx;
   } // SETINDX(INT)

} // CLASS

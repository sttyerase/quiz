package com.dbmi.demos.quiz;

import com.dbmi.demos.quiz.model.Question;
import com.dbmi.demos.quiz.model.QuestionList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Vector;

import org.springframework.ui.Model;

import com.dbmi.demos.quiz.model.Quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class QuizController implements ErrorController {
    private final Logger myLogger = LoggerFactory.getLogger(QuizController.class);

    private Vector<Quiz> myQuizes;

    // MAPPINGS
    @RequestMapping("/")
    public String home(Model aModel, HttpServletRequest request) {
        myLogger.debug("Requested root page.");
        return this.welcome(aModel, request);
    } // HOME

    @RequestMapping("/welcome")
    public String welcome(Model aModel, HttpServletRequest request) {
        myLogger.debug("Requested welcome page.");
        request.getSession().invalidate();
        request.getSession(true);
        aModel.addAttribute("myQuizes", myQuizes);
        aModel.addAttribute("today", new Date().toString());
        return "welcome";
    } // WELCOME(MODEL)

    @PostMapping("/initiatequiz")  // INITIATE THE QUIZ AND PASS THROUGH TO FIRST QUESTION /DISPLAYQUESTION
    public String initiatequiz(Model aModel, HttpServletRequest request) {
        // SESSION MANAGEMENT
        myLogger.debug("INITIATEQUIZ: page requested.");
        HttpSession mySession = request.getSession(false);
        if (mySession == null) {
            myLogger.error("INITIATEQUIZ: no session passed with request. Exiting now.");
            return this.error(aModel,request,"INITIATEQUIZ: no session passed with request. Exiting now.");
        } // IF(MYSESSION)
        int quiznum = Integer.parseInt(request.getParameter("welcomeselect"));
        Quiz theQuiz = myQuizes.elementAt(quiznum);
        theQuiz.getQuestionList().initQuestionList();
        theQuiz.getScoreKeeper().init();
        mySession.setAttribute("thequiz", theQuiz);
        return this.displayquestion(aModel,request);
    } // INITIATEQUIZ(MODEL,HTTPSERVLETREQUEST,HTTPSERVLETRESPONSE)

    @RequestMapping("/displayquestion")
    public String displayquestion(Model aModel, HttpServletRequest request) {
        myLogger.debug("DISPLAYQUESTION: requested.");
        Quiz theQuiz = (Quiz) request.getSession().getAttribute("thequiz");
        // IF THE QUIZ IS DONE, RETURN THE RESULTS
        QuestionList theQuestionList = theQuiz.getQuestionList();
        int currentQuestionNumber = theQuestionList.getCurrentQuestionNumber();
        theQuestionList.setCurrentQuestionNumber(currentQuestionNumber);
        // ADD COMPONENTS TO THE MODEL
        aModel.addAttribute("thequiz",theQuiz);
        aModel.addAttribute("today", new Date().toString());
        return "quizquestion";  // HTML TEMPLATE THAT DISPLAYS QUESTION DATA
    } // QUIZQUESTION(MODEL,HTTPSERVLETREQUEST,HTTPSERVLETRESPONSE)

    @PostMapping("/displayanswer")
    public String theanswer(Model aModel, HttpServletRequest request) {
        String returnResponse;  // THE RESPONSE RETURNED TO THE PAGE
        myLogger.debug("DISPLAYANSWER: requested.");
        Quiz theQuiz = (Quiz) request.getSession().getAttribute("thequiz");
        QuestionList questionList = theQuiz.getQuestionList();
        Question currentQuestion = questionList.getTheQuestions().elementAt(questionList.getCurrentQuestionNumber());
        int theResponse = Integer.parseInt(request.getParameter("questionchoices"));
        int correctAnswer = questionList.getTheQuestions().elementAt(theQuiz.getQuestionList().getCurrentQuestionNumber()).getCorrectAnswerNumber();
        // UPDATE THE QUESTION INSTANCE WITH RESULTS -- SCOREKEEPER WILL SUMMARIZE RESULTS AT THE END
        currentQuestion.setAnswered(true);
        if(theResponse == correctAnswer) {
            returnResponse = "You have chosen the correct answer. \uD83D\uDE0C";
            currentQuestion.setAnsweredCorrectly(true);
        } else {
            returnResponse = "You missed this one. \uD83D\uDE41";
        } // IF-ELSE()
        aModel.addAttribute("correctness",returnResponse);
        aModel.addAttribute("thequiz",theQuiz);
        aModel.addAttribute("today", new Date().toString());
        return "theanswer"; // HTML TEMPLATE THAT DISPLAYS THE ANSWER
    } // THEANSWER(MODEL,HTTPSERVLETREQUEST,HTTPSERVLETRESPONSE)

    @RequestMapping("/nextquestion")
    public String nextquestion(Model aModel, HttpServletRequest request) {
        Quiz theQuiz = (Quiz) request.getSession().getAttribute("thequiz");
        theQuiz.getQuestionList().setCurrentQuestionNumber(theQuiz.getQuestionList().getCurrentQuestionNumber() + 1);
        if (theQuiz.getQuestionList().notDone()) {
            return this.displayquestion(aModel,request);
        } // IF(NOT DONE)
        return this.quizresults(aModel,request);
    } // NEXTQUESTION(MODEL,HTTPSERVLETREQUEST)

    @RequestMapping("/displayresults")
    public String quizresults(Model aModel, HttpServletRequest request) {
        myLogger.debug("DISPLAYRESULTS: requested.");
        Quiz theQuiz = (Quiz) request.getSession().getAttribute("thequiz");
        theQuiz.getScoreKeeper().scoreQuiz(theQuiz.getQuestionList());
        aModel.addAttribute("thequiz",theQuiz);
        aModel.addAttribute("today", new Date().toString());
        return "quizresults";
    } // QUIZRESULTS(MODEL,HTTPSERVLETREQUEST,HTTPSERVLETRESPONSE)

    @RequestMapping("/error")
    public String error(Model aModel, HttpServletRequest request, String errorMessage) {
        myLogger.debug("ERROR PAGE requested.");
        Quiz theQuiz = (Quiz) request.getSession().getAttribute("thequiz");
        aModel.addAttribute("thequiz",theQuiz);
        aModel.addAttribute("err", errorMessage);
        aModel.addAttribute("today", new Date().toString());
        return "error";
    } // ERROR(MODEL,HTTPSERVLETREQUEST,HTTPSERVLETRESPONSE)

    @Autowired
    public void setMyQuizes(Vector<Quiz> myQuizes) {
        this.myQuizes = myQuizes;
    }

} // CLASS

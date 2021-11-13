package com.dbmi.demos.quiz;

import com.dbmi.demos.quiz.model.Question;
import com.dbmi.demos.quiz.model.QuestionList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class QuizController {
    private final Logger myLogger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private Vector<Quiz> myQuizes;

    // MAPPINGS
    @RequestMapping("/")
    public String home(Model aModel, HttpServletRequest request) {
        myLogger.info("Requested root page.");
        return this.welcome(aModel, request);
    } // HOME

    @RequestMapping("/welcome")
    public String welcome(Model aModel, HttpServletRequest request) {
        myLogger.info("Requested welcome page.");
        request.getSession().invalidate();
        HttpSession mySession = request.getSession(true);
        mySession.setAttribute("questionnumber", 0);
        aModel.addAttribute("myQuizes", myQuizes);
        aModel.addAttribute("today", new Date().toString());
        return "welcome";
    } // WELCOME(MODEL)

    @PostMapping("/initiatequiz")
    public String initiatequiz(Model aModel, HttpServletRequest request) {
        // SESSION MANAGEMENT
        myLogger.info("INITIATEQUIZ: page requested.");
        HttpSession mySession = request.getSession(false);
        if (mySession == null) {
            myLogger.info("INITIATEQUIZ: no session passed with request. Exiting now.");
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
        myLogger.info("DISPLAYQUESTION: requested.");
        Quiz theQuiz = (Quiz) request.getSession().getAttribute("thequiz");
        // IF THE QUIZ IS DONE, RETURN THE RESULTS
        if (theQuiz.getQuestionList().notDone()) {
            /* CONTINUE ON */
        } else {
            return this.quizresults(aModel, request);
        } // IF(QL.NOTDONE)
        QuestionList theQuestionList = theQuiz.getQuestionList();
        int currentQuestionNumber = theQuestionList.getCurrentQuestionNumber();
        theQuestionList.setCurrentQuestionNumber(currentQuestionNumber);
        Question currentQuestion = theQuestionList.getTheQuestions().elementAt(currentQuestionNumber);
        int numberOfQuestions = theQuestionList.getNumberOfQuestions();
        // ADD COMPONENTS TO THE MODEL
        aModel.addAttribute("quizname", theQuiz.getQuizName());
        aModel.addAttribute("numberofquestions", numberOfQuestions);
        aModel.addAttribute("currentquestion", currentQuestion);
        aModel.addAttribute("userquestionnumber", currentQuestionNumber + 1);  // THE USER QUESTION NUMBER IS ARRAY ADDRESS + 1
        aModel.addAttribute("today", new Date().toString());
        return "quizquestion";
    } // QUIZQUESTION(MODEL,HTTPSERVLETREQUEST,HTTPSERVLETRESPONSE)

    @RequestMapping("/displayanswer")
    public String theanswer(Model aModel, HttpServletRequest request) {
        myLogger.info("DISPLAYANSWER: requested.");
        Quiz theQuiz = (Quiz) request.getSession().getAttribute("thequiz");
        aModel.addAttribute("thequiz",theQuiz);
        aModel.addAttribute("currentquestionnumber",theQuiz.getQuestionList().getCurrentQuestionNumber());
        aModel.addAttribute("today", new Date().toString());
        theQuiz.getQuestionList().setCurrentQuestionNumber(theQuiz.getQuestionList().getCurrentQuestionNumber() + 1);
        return "theanswer";
    } // THEANSWER(MODEL,HTTPSERVLETREQUEST,HTTPSERVLETRESPONSE)

    @RequestMapping("/displayresults")
    public String quizresults(Model aModel, HttpServletRequest request) {
        myLogger.info("DISPLAYRESULTS: requested.");
        Quiz theQuiz = (Quiz) request.getSession().getAttribute("thequiz");
        aModel.addAttribute("today", new Date().toString());
        return "quizresults";
    } // QUIZRESULTS(MODEL,HTTPSERVLETREQUEST,HTTPSERVLETRESPONSE)

    @RequestMapping("/newerror")
    public String error(Model aModel, HttpServletRequest request, String errorMessage) {
        myLogger.info("NEWERROR: requested.");
        Quiz theQuiz = (Quiz) request.getSession().getAttribute("thequiz");
        aModel.addAttribute("err", errorMessage);
        aModel.addAttribute("today", new Date().toString());
        return "error";
    } // ERROR(MODEL,HTTPSERVLETREQUEST,HTTPSERVLETRESPONSE)

} // CLASS

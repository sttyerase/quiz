package com.dbmi.demos.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import org.springframework.ui.Model;

import com.dbmi.demos.quiz.model.Quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class QuizController {
    private final Logger myLogger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private Vector<Quiz> myQuizes;
    private String sillyString = "silly string";

    // MAPPINGS

    @RequestMapping("/")
    public String home(Model aModel, HttpServletRequest request, HttpServletResponse response) {
        return this.welcome(aModel, request, response);
    } // HOME

    @RequestMapping("/welcome")
    public String welcome(Model aModel, HttpServletRequest request, HttpServletResponse response) {
        HttpSession mySession = request.getSession(true);
        aModel.addAttribute("myQuizes",myQuizes);
        aModel.addAttribute("today",new Date().toString());
        System.out.println("YEP!");
        return "welcome";
    } // WELCOME(MODEL)

    @PostMapping("/displayquestion")
    public String quizquestion(Model aModel, HttpServletRequest request, HttpServletResponse response) {
        int qnum = Integer.parseInt(request.getParameter("questionnumber"));
        HttpSession mySession = request.getSession();
        Enumeration<String> en = mySession.getAttributeNames();
        aModel.addAttribute("myQuizes",myQuizes);
        aModel.addAttribute("questionnumber",qnum + 1);
        aModel.addAttribute("today",new Date().toString());
        return "quizquestion";
    } // QUIZQUESTION(MODEL)

} // CLASS

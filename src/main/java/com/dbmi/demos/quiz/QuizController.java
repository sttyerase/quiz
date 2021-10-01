package com.dbmi.demos.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;
import java.util.Vector;
import org.springframework.ui.Model;

import com.dbmi.demos.quiz.model.Quiz;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    private final Logger myLogger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private Vector<Quiz> myQuizes;

    // MAPPINGS
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        HttpHeaders headers = new HttpHeaders();
        myLogger.debug("welcome page displayed");
        return new ResponseEntity<>("Welcome to the Quiz. Quizes available: " + myQuizes.size() + " ===> " + new Date(),headers, HttpStatus.OK);
    } // WELCOME()

    @RequestMapping("/hello")
    public String hello(Model aModel) {
        String rando = "I am some random string in the middle of your page.";
        aModel.addAttribute("rando", rando);
        aModel.addAttribute("myQuizes",myQuizes);
        aModel.addAttribute("today",new Date().toString());
        return "welcome";
    }

} // CLASS

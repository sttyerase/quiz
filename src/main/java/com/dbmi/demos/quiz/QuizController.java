package com.dbmi.demos.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/quiz")
public class QuizController {
    private final Logger myLogger = LoggerFactory.getLogger(QuizController.class);

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        HttpHeaders headers = new HttpHeaders();
        myLogger.debug("welcome page displayed");
        return new ResponseEntity<>("Welcome to the Quiz.",headers, HttpStatus.OK);
    } // WELCOME()

} // CLASS

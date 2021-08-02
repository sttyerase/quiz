package com.dbmi.demos.quiz;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    private Logger myLogger = null;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>("Welcome to the Quiz.",headers, HttpStatus.OK);
    } // WELCOME()

} // CLASS

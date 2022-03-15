package ru.kms96.englishdigger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView homePage() {

        ModelAndView resultView = new ModelAndView();
        resultView.setViewName("/home/index");
        resultView.setStatus(HttpStatus.OK);

        return resultView;
    }
}

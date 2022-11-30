package it.cgmconsulting.word.controller;

import it.cgmconsulting.word.word.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {
    @Autowired
    WordService wordService;

    @GetMapping("/")
    public String getWord() {
        return wordService.getWord();
    }

}

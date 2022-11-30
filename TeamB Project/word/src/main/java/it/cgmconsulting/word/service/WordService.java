package it.cgmconsulting.word.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class WordService {
    @Value("${asw.sentence.wordservice.words}")
    private String words;

    private Random random = new Random();

    public String getWord() {
        String[] wordArray = words.split(",");
        int randomIndex = random.nextInt(wordArray.length);
        return wordArray[randomIndex];
    }
}


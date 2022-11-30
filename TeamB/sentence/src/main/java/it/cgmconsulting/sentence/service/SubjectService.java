package it.cgmconsulting.sentence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SubjectService  {


    @Autowired
    WordClientWebClient wordClientWebClient;


    public Mono<String> getWord() {
        return wordClientWebClient.getWord("subject");

    }



}

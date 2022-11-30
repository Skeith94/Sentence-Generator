package it.cgmconsulting.sentence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VerbService  {

    @Autowired
    WordClientWebClient wordClientWebClient;

    public Mono<String> getWord() {

        return wordClientWebClient.getWord("verb");
    }
}

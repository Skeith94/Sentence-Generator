package it.cgmconsulting.sentence.service;

import it.cgmconsulting.sentence.WordClientWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SubjectService {

    @Autowired WordClientWebClient wordClient;

    public Mono<String> getWord() {
        return wordClient.getWord("subject");
    }
}

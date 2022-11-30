package it.cgmconsulting.sentence;

import it.cgmconsulting.sentence.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @Autowired SentenceService sentenceService;

    @GetMapping
    public Mono<ResponseEntity<?>> get(){
        return sentenceService.getSentence();
    }
}

package it.cgmconsulting.sentence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@Service
public class SentenceService {


    @Autowired SubjectService subjectService;
    @Autowired VerbService verbService;
    @Autowired ObjectService objectService;

    public  Mono<ResponseEntity<?>> getSentence() {
        /*Flux<String> mergedMonos = subjectService.getWord().map(s -> s.concat(" "))
                                    .concatWith(verbService.getWord().map(s -> s.concat(" "))
                                    .concatWith(objectService.getWord().map(s -> s.concat("."))));

        return mergedMonos.reduce(String::concat);*/

        Mono<Tuple3<String, String, String>> zipped = Mono.zip(subjectService.getWord(), verbService.getWord(), objectService.getWord());
        return zipped.map(t -> ResponseEntity.status(HttpStatus.OK).body(t.getT1() + " " + t.getT2() + " " + t.getT3() + "."));

    }
}

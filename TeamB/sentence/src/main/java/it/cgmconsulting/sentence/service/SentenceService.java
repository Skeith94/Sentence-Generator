package it.cgmconsulting.sentence.service;

import it.cgmconsulting.sentence.entity.GeneratedSentence;
import it.cgmconsulting.sentence.repository.GeneratedSentenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SentenceService {

   
    @Autowired SubjectService subjectService;
    @Autowired ObjectService objectService;

    @Autowired VerbService verbService;

    @Autowired GeneratedSentenceRepo generatedSentenceRepo;


    public Mono<String> getSentence() {

        Mono<Tuple3<String,String,String>> zipped=Mono.zip( subjectService.getWord(),objectService.getWord(),verbService.getWord());
        Mono<String>sentence=zipped.map(tuple->tuple.getT1()+" "+tuple.getT2()+" "+tuple.getT3()+".").doOnSuccess(s->generatedSentenceRepo.save(new GeneratedSentence(s)));
        return sentence;

    }


    public List<GeneratedSentence> getGenSentence(LocalDateTime dateStart, LocalDateTime dateEnd) {
        return generatedSentenceRepo.findByCreatedAtBetween(dateStart,dateEnd);
    }
}

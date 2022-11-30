package it.cgmconsulting.sentence;

import it.cgmconsulting.sentence.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
public class Controller {
    @Autowired
    SentenceService sentenceService;
    @GetMapping()
   public Mono<ResponseEntity<?>> hello(){
        return sentenceService.getSentence().map(s->ResponseEntity.status(HttpStatus.CREATED).body(s));
    }

    @GetMapping("/getGenSentence")
    public ResponseEntity<?> getGenSentence(@RequestParam(defaultValue = "2000/01/01")  String  dateStartRequest, @RequestParam(defaultValue = "2023/02/03")  String dateEndRequest){
        String[] dateParts = dateStartRequest.split("/");
        LocalDateTime dateStart;
        LocalDateTime dateEnd;
        try {
            dateStart = LocalDateTime.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]), 0, 0,0);
            dateParts = dateEndRequest.split("/");
            dateEnd = LocalDateTime.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]), 0, 0,0);
        }catch (Exception e){
            return new ResponseEntity<>("wrong date format yyyy/dd/gg",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sentenceService.getGenSentence(dateStart,dateEnd),HttpStatus.OK);
    }
}

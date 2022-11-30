package it.cgmconsulting.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {


    @GetMapping("fallback/object")
    public ResponseEntity<?> fallbackSubject(){
        return new ResponseEntity<>("Object service offline", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("fallback/subject")
    public ResponseEntity<?> fallbackObject(){
        return new ResponseEntity<>("Subject service offline", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("fallback/verb")
    public ResponseEntity<?> fallbackVerb(){
        return new ResponseEntity<>("Verb service offline", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("fallback/sentence")
    public ResponseEntity<?> fallbackSentence(){
        return new ResponseEntity<>("Sentence service offline", HttpStatus.BAD_REQUEST);
    }
}

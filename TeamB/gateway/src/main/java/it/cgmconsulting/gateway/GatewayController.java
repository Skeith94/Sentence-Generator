package it.cgmconsulting.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/fallback/subject")
    public ResponseEntity<?>fallbackSubject(){
        return new ResponseEntity<>("subject service offline", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fallback/verb")
    public ResponseEntity<?>fallbackVerb(){
        return new ResponseEntity<>("verb service offline", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fallback/sentence")
    public ResponseEntity<?>fallbackSentence(){
        return new ResponseEntity<>("sentence service offline", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fallback/object")
    public ResponseEntity<?>fallbackObject(){
        return new ResponseEntity<>("object service offline", HttpStatus.NOT_FOUND);
    }


}

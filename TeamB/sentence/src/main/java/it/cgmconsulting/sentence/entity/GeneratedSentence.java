package it.cgmconsulting.sentence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document @Getter @Setter @NoArgsConstructor
public class GeneratedSentence {

   private LocalDateTime createdAt=LocalDateTime.now();
   private String sentence;

    public GeneratedSentence(String sentence) {
        this.sentence = sentence;
    }


}

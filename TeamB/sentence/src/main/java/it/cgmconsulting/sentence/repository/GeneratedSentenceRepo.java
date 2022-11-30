package it.cgmconsulting.sentence.repository;

import it.cgmconsulting.sentence.entity.GeneratedSentence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GeneratedSentenceRepo extends MongoRepository<GeneratedSentence,Long> {

    List<GeneratedSentence> findByCreatedAtBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
}

package it.cgmconsulting.sentence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.List;


@Service
public class WordClientWebClient {

    @Qualifier("loadBalancedWebClient")
    @Autowired WebClient webClient;

    @Autowired ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    public Mono<String> getWord(String service) {

        //Accesso mediante WebClient load balanced
        String uri = "http://" + service;
        return webClientGet(uri).timeout(Duration.ofMillis(10_000))
                .transform( it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create(service);
                    return rcb.run(it, throwable -> Mono.just("[" + service + " offline]"));
                });
    }

    private Mono<String> webClientGet(String uri) {
        Mono<String> response = webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class);
        return response;
    }

}
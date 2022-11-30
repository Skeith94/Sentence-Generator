package it.cgmconsulting.sentence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@Service
public class WordClientWebClient {

    @Autowired DiscoveryClient discoveryClient;
    @Autowired LoadBalancerClient loadBalancer;

    @Qualifier("loadBalancedWebClient")
    @Autowired WebClient webClient;

    @Autowired ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    public Mono<String> getWord(String service) {
        //URI uri = getWordUri(service);

        //Accesso mediante WebClient load balanced
        String uri = "http://" + service;
        return webClientGet(uri).timeout(Duration.ofMillis(10_000))
                .transform( it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create(service);
                    return rcb.run(it, throwable -> Mono.just("[" + service + " offline]"));
                });
    }

    private URI getWordUri(String service) {
        URI uri = null;

        /*
        //Accesso mediante Discovery service
        List<ServiceInstance> list = discoveryClient.getInstances(service);
        if (list != null && list.size()>0) {
            //scegli il primo, oppure un elemento casuale della lista
            uri = list.get(0).getUri();
        }*/

        //Accesso mediante LoadBalancer service
        ServiceInstance instance = loadBalancer.choose(service);
        if (instance != null) {
            uri = instance.getUri();
        }
        return uri;
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


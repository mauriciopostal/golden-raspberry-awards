package com.mopsoftware.goldenraspberryawards.movie.producer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mopsoftware.goldenraspberryawards.movie.producer.ProducerAwardIntervalType.MAX;
import static com.mopsoftware.goldenraspberryawards.movie.producer.ProducerAwardIntervalType.MIN;

@RestController
@RequestMapping("/v1/producers")
@AllArgsConstructor
public class ProducerController {
    private ProducerService producerService;

    @GetMapping("/award")
    ProducerAwardResponse getAwardWinnerProducers() {
        var producerAwards = producerService.getAwardWinnerProducers();

        return ProducerAwardIntervalResponseMapper.convert(producerAwards);
    }
}

package com.mopsoftware.goldenraspberryawards.movie.producer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProducerService {
    private ProducerRepository producerRepository;

    public List<ProducerAwardIntervalDTO> getAwardWinnerProducers() {
        return producerRepository.findProducersWithInterval();
    }
}

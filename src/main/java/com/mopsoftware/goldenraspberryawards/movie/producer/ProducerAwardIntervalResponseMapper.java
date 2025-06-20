package com.mopsoftware.goldenraspberryawards.movie.producer;

import java.util.List;

import static com.mopsoftware.goldenraspberryawards.movie.producer.ProducerAwardIntervalType.MAX;
import static com.mopsoftware.goldenraspberryawards.movie.producer.ProducerAwardIntervalType.MIN;

public class ProducerAwardIntervalResponseMapper {
    public static ProducerAwardResponse convert(List<ProducerAwardIntervalDTO> producerAwards) {
        var min = producerAwards
                .stream()
                .filter(producerAwardIntervalDTO -> producerAwardIntervalDTO.getTypeEnum() == MIN)
                .map(ProducerAwardIntervalResponseMapper::convert)
                .toList();
        var max = producerAwards
                .stream()
                .filter(producerAwardIntervalDTO -> producerAwardIntervalDTO.getTypeEnum() == MAX)
                .map(ProducerAwardIntervalResponseMapper::convert)
                .toList();

        return new ProducerAwardResponse(min, max);
    }

    private static ProducerAwardIntervalResponse convert(ProducerAwardIntervalDTO producerAwardIntervalDTO) {
        return new ProducerAwardIntervalResponse(
                producerAwardIntervalDTO.name(),
                producerAwardIntervalDTO.winGap(),
                producerAwardIntervalDTO.previousWin(),
                producerAwardIntervalDTO.followingWin()
        );
    }
}


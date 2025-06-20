package com.mopsoftware.goldenraspberryawards.movie.producer;

import java.util.List;

public record ProducerAwardResponse (
        List<ProducerAwardIntervalResponse> min,
        List<ProducerAwardIntervalResponse> max
) {}

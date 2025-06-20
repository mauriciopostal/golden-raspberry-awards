package com.mopsoftware.goldenraspberryawards.movie.producer;

public record ProducerAwardIntervalResponse(
        String producer,
        int interval,
        int previousWin,
        int followingWin
) {}
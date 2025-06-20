package com.mopsoftware.goldenraspberryawards.movie.producer;

public record ProducerAwardIntervalDTO (
    String type,
    Long producerId,
    String name,
    int winGap,
    int previousWin,
    int followingWin){

    public ProducerAwardIntervalType getTypeEnum() {
        return ProducerAwardIntervalType.valueOf(type);
    }
}


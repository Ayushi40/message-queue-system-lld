package com.example.messagequeue.patterns.strategy;

import java.util.Random;

public class RandomPartitionStrategy implements PartitionStrategy {
    private final Random random= new Random();
    @Override
    public int getPartition(String topic, int numPartitions) {
        return random.nextInt(numPartitions);
    }
}

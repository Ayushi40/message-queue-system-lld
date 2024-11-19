package com.example.messagequeue.patterns.strategy;

public class RoundRobbinPartitionStrategy implements PartitionStrategy{
    private int counter =0;
    @Override
    public int getPartition(String topic, int numPartitions) {
        return counter++ % numPartitions;
    }
}

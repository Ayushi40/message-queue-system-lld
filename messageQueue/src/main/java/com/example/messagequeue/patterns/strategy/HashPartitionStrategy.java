package com.example.messagequeue.patterns.strategy;

public class HashPartitionStrategy implements PartitionStrategy{
    @Override
    public int getPartition(String topic, int numPartitions) {
        return Math.abs(topic.hashCode())% numPartitions;
    }
}

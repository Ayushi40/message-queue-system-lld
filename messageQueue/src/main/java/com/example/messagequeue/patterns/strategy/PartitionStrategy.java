package com.example.messagequeue.patterns.strategy;

public interface PartitionStrategy {
    int getPartition(String topic, int numPartitions);
}

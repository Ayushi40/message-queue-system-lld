package com.example.messagequeue.core;

import com.example.messagequeue.patterns.strategy.PartitionStrategy;

public class Producer {

    private final PartitionStrategy partitionStrategy;

    public Producer(PartitionStrategy partitionStrategy) {
        this.partitionStrategy = partitionStrategy;
    }

    public void sendMessage(String topic, String message,Broker broker){
        int partition = partitionStrategy.getPartition(topic, broker.getNumPartitions(topic));
        broker.publish(topic,new Message(message,partition));

    }
}

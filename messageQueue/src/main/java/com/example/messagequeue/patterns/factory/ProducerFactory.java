package com.example.messagequeue.patterns.factory;


import com.example.messagequeue.core.Producer;
import com.example.messagequeue.patterns.strategy.PartitionStrategy;

public interface ProducerFactory {
    Producer createProducer(PartitionStrategy strategy);
}

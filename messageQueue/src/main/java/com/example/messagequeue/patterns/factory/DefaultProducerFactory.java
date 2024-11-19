package com.example.messagequeue.patterns.factory;

import com.example.messagequeue.core.Producer;
import com.example.messagequeue.patterns.strategy.PartitionStrategy;
import org.springframework.stereotype.Component;

@Component
public class DefaultProducerFactory implements ProducerFactory{
    @Override
    public Producer createProducer(PartitionStrategy strategy) {
        return new Producer(strategy);
    }
}

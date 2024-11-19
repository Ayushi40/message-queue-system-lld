package com.example.messagequeue.patterns.factory;

import com.example.messagequeue.core.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultConsumerFactory implements ConsumerFactory{
    @Override
    public Consumer createConsumer(String id) {
        return new Consumer(id);
    }
}

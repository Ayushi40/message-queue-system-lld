package com.example.messagequeue.patterns.factory;

import com.example.messagequeue.core.Consumer;

public interface ConsumerFactory {
    Consumer createConsumer(String id);
}

package com.example.messagequeue.controller;

import com.example.messagequeue.core.Broker;
import com.example.messagequeue.core.Consumer;
import com.example.messagequeue.core.Message;
import com.example.messagequeue.core.Producer;
import com.example.messagequeue.patterns.factory.ConsumerFactory;
import com.example.messagequeue.patterns.factory.ProducerFactory;
import com.example.messagequeue.patterns.strategy.HashPartitionStrategy;
import com.example.messagequeue.patterns.strategy.PartitionStrategy;
import com.example.messagequeue.patterns.strategy.RandomPartitionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1")
public class MessageQueueController {

    private final Broker broker = Broker.getInstance();
    @Autowired
    private final ProducerFactory producerFactory;
    @Autowired
    private final ConsumerFactory consumerFactory;

    public MessageQueueController(ProducerFactory producerFactory, ConsumerFactory consumerFactory) {
        this.producerFactory = producerFactory;
        this.consumerFactory = consumerFactory;
    }

    // http://localhost:8080/api/v1/createTopic?name=myTopic&partitions=3
    @PostMapping("/createTopic")
    public String createTopic(@RequestParam String name, @RequestParam int partitions){
        broker.createTopic(name,partitions);
        return "Topic Created: "+name;
    }

    // Payload for HashPartitionStrategy
    //http://localhost:8080/api/v1/produce?topic=myTopic&message=HelloHash&strategy=hash

    // Payload for RoundRobinPartitionStrategy:
    // http://localhost:8080/api/v1/produce?topic=myTopic&message=HelloHash&strategy=roundrobin

    // Payload for RandomPartitionStrategy:
    // http://localhost:8080/api/v1/produce?topic=myTopic&message=HelloHash&strategy=random

    @PostMapping("/produce")
    public String produceMessage(@RequestParam String topic, @RequestParam String message,@RequestParam String strategy){
        PartitionStrategy partitionStrategy;

        switch (strategy.toLowerCase(Locale.ROOT)){
            case "roundrobin":
                partitionStrategy = new RandomPartitionStrategy();
                break;
            case "random":
                partitionStrategy = new RandomPartitionStrategy();
                break;
            default:
                partitionStrategy = new HashPartitionStrategy();
        }

        Producer producer = producerFactory.createProducer(partitionStrategy);
        producer.sendMessage(topic,message,broker);
        return "Message produced to topic: "+topic;

    }
    // http://localhost:8080/api/v1/subscribe?topic=myTopic&consumerId=consumer1
    // http://localhost:8080/api/v1/subscribe?topic=myTopic&consumerId=consumer2
    // http://localhost:8080/api/v1/subscribe?topic=myTopic&consumerId=consumer3

    @PostMapping("/subscribe")
    public String subscribeConsumer(@RequestParam String topic , @RequestParam String consumerId){
        Consumer consumer = consumerFactory.createConsumer(consumerId);
        broker.addObserver(topic ,consumer);
        return "Consumer "+ consumerId+" subscribed to topic: "+ topic;
    }
    // http://localhost:8080/api/v1/consume?consumerId=consumer3
    @GetMapping("/consume")
    public List<Message> consumeMessages(@RequestParam String consumerId){
        Consumer consumer = consumerFactory.createConsumer(consumerId);
        return consumer.getMessages();

    }
}

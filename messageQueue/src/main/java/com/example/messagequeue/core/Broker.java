package com.example.messagequeue.core;

import io.micrometer.observation.Observation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Broker {
    private static Broker instance;
    private final Map<String, List<Observer>> topicObservers = new HashMap<>();
    private final Map<String,Integer> topicPartitions= new HashMap<>();
    Observable o;

    private Broker(){

    }
    public static synchronized Broker getInstance(){
        if(instance ==null){
            instance = new Broker();
        }
        return instance;
    }

    public void createTopic(String topic,int partitions){
        topicPartitions.put(topic,partitions);
        topicObservers.put(topic,new ArrayList<>());
    }

    public void addObserver(String topic, Observer observer){
        topicObservers.getOrDefault(topic,new ArrayList<>()).add(observer);
    }
    public void publish(String topic, Message message){
        for(Observer observer: topicObservers.getOrDefault(topic, new ArrayList<>())){
            observer.update(o,message);
        }
    }
    public int getNumPartitions(String topic){
        return topicPartitions.getOrDefault(topic,1);

    }
}

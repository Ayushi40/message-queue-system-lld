package com.example.messagequeue.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

public class Consumer implements Observer {

    private final String id;
    private final Queue<Message> messageQueue = new LinkedList<>();

    public Consumer(String id) {
        this.id = id;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Message){
            Message message = (Message) arg;
            messageQueue.add(message);
            System.out.println("Consumer " + id + " received message: " + message);
        } else {
            System.out.println("Consumer " + id + " received an unknown type of message.");
        }
    }

    public List<Message> getMessages(){
        return new ArrayList<>(messageQueue);

    }
}

package com.example.messagequeue.core;

import lombok.Data;

@Data
public class Message {

    private String content;
    private int partition;

    public Message(String message, int partition) {
    }
}

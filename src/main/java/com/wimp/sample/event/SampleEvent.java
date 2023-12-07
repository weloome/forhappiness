package com.wimp.sample.event;

import org.springframework.context.ApplicationEvent;

public class SampleEvent extends ApplicationEvent {

    private String message;

    public SampleEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

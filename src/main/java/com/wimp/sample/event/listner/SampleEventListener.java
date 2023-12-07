package com.wimp.sample.event.listner;


import com.wimp.sample.event.SampleEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class SampleEventListener {

    @EventListener
    public void handleCustomEvent(SampleEvent sampleEvent){
        System.out.println("Event Listener Get : " + sampleEvent.getMessage());
    }
}

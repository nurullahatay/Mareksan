package com.natay.mareksan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MareksanApplication {

    public static void main(String[] args) {
        SpringApplication.run(MareksanApplication.class, args);
    }
}

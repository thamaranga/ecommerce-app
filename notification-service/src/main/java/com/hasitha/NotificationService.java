package com.hasitha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;

@SpringBootApplication
public class NotificationService {

    public static void main(String[] args) {
        SpringApplication.run(NotificationService.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlaceEvent orderPlaceEvent){
        //Sending notification email logic here
        System.out.println("Received a notification for the order | "+orderPlaceEvent.getOrderNumber());

    }
}

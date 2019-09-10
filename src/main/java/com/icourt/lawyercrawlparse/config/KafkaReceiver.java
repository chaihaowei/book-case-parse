package com.icourt.lawyercrawlparse.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class KafkaReceiver {

    @Autowired
    private ReceiverToDb receiverToDb;

//    @KafkaListener(topics = {"lawtime_topic"})
//    public void listen(ConsumerRecord<?, ?> record) {
//        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//        if (kafkaMessage.isPresent()) {
//            Object message = kafkaMessage.get();
//
//            log.info("record =" + record);
//            log.info("message =" + message);
//            if(Objects.nonNull(message)) {
//                receiverToDb.toDb(message.toString(), message.toString());
//            }
//        }
//    }
}
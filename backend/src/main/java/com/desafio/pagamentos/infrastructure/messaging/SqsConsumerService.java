package com.desafio.pagamentos.infrastructure.messaging;

import com.desafio.pagamentos.domain.model.Payment;
import com.desafio.pagamentos.domain.port.PaymentRepositoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.env.Environment;

import java.util.List;

@Component
public class SqsConsumerService {

    private final SqsClient sqs;
    private final String queueUrl;
    private final PaymentRepositoryPort repository;
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public SqsConsumerService(SqsClient sqs, Environment env, PaymentRepositoryPort repository) {
        this.sqs = sqs;
        this.queueUrl = env.getProperty("aws.sqs.queue-url");
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 5000) // A cada 5 segundos
    public void pollSqs() {
        try {
            ReceiveMessageRequest req = ReceiveMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .maxNumberOfMessages(10)
                    .waitTimeSeconds(1)
                    .build();
            ReceiveMessageResponse resp = sqs.receiveMessage(req);
            for (Message m : resp.messages()) {
                Payment p = mapper.readValue(m.body(), Payment.class);
                repository.save(p);
                sqs.deleteMessage(DeleteMessageRequest.builder().queueUrl(queueUrl).receiptHandle(m.receiptHandle()).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.desafio.pagamentos.infrastructure.messaging;

import com.desafio.pagamentos.domain.model.Payment;
import com.desafio.pagamentos.domain.port.PaymentEventPublisherPort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.env.Environment;


@Component
public class SqsPublisherAdapter implements PaymentEventPublisherPort {

    private final SqsClient sqs;
    private final String queueUrl;
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public SqsPublisherAdapter(SqsClient sqs, Environment env) {
        this.sqs = sqs;

        String queueName = env.getProperty("aws.sqs.queue-name");
        this.queueUrl = sqs.getQueueUrl(r -> r.queueName(queueName)).queueUrl();
    }

    @Override
    public void publish(Payment payment) {
        try {
            String body = mapper.writeValueAsString(payment);
            SendMessageRequest req = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(body)
                    .build();
            sqs.sendMessage(req);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

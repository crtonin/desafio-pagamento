#!/bin/bash

# Espera 5 segundos para garantir que o serviço SQS do LocalStack esteja 100% carregado
sleep 5 

echo "Criando fila SQS: payment-events"
docker exec backend-localstack-1 awslocal sqs create-queue --queue-name payment-event
echo "Fila SQS criada."

# Mantenha o container rodando
tail -f /dev/null
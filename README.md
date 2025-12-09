# desafio-pagamento
Implementação de uma solução web para envio e persistência de transações de pagamento, utilizando arquitetura hexagonal e serviços AWS.  
O backend foi desenvolvido em Java + Spring Boot, organizado seguindo a Arquitetura Hexagonal (Ports and Adapters).  

src/main/java/com/desafio/pagamento  
│  
├── application        ← casos de uso (lógica)  
├── dto  
│   ├── usecase  
│  
├── domain             ← entidades e portas (interfaces)  
│   ├── model  
│   ├── port  
│  
├── infrastructure     ← adapters (DB, fila, REST)  
│   ├── controller  
│   ├── repository  
│   ├── messaging  
│   ├── config  

**Infraestrutura usada**
Spring Boot  
Java 17+  
Maven  
Spring Web  
Spring Data JPA  
MySQL  
Docker Compose  

**Requisitos**  
AWS/Local Stack
Docker  
Java 17+  
MySQL  

**Para rodar o back-end**  
Acesse a pasta backend  
Execute o comando:  
```docker compose up -d```

**Para rodar o front-end**  
Acesse a pasta frontend  
Execute os comandos:  
```npm install```
```npm run dev```

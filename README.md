# Microservices

## Tools Required 
- SpringBoot - WebClient Spring framework
- Eureka - Service Registration and Discovery
- Swagger - API documentation
- Docker - Containerization
- Oauth2 KeyCloack - Authorization & IAM
- PostgreSQL, MongoDB - Database
- Resilienc4J - Circuitbreaker and Resiliency
- Sleuth & Zipkin - Distributed Tracing
- Kafka - Event-Driven Microservices Architecture
- Prometheus and Grafana - Monitoring
- AWS - Cloud
  

## Build Tools
- Java 17 - Programming
- Maven - Build
- Git - Version control
- Docker - Deployment

## Keynote

This application is built using Microservices architecture.

Rather than having a big monolithic application, application is broken down into smaller, independently running microservices.

List of microservices : 

| Microservice           | Language      | 
| -------------          | ------------- |  
| SalonServices Service  |   Java        |
| Roster Service         |   Java        |
| Appointment Service    |   Java        |

## Solution Architecture
![Screenshot 2023-11-17 104750](https://github.com/aditi55/Microservices/assets/67974030/4f6ab687-fc7d-452d-83f4-67c8adac21be)

## AWS Architecture
![Screenshot 2023-11-19 194433](https://github.com/aditi55/Microservices/assets/67974030/4e684ca1-58fa-445c-9f53-282bdea08107)

## Output

1. ### Implementation of Oauth2
   
   ![Screenshot 2023-10-29 131729](https://github.com/aditi55/Microservices/assets/67974030/4700ebdf-c70c-4d1b-8e9a-ed844e0ca3ed)

2. ### Distributed Tracing

   ![Screenshot 2023-10-29 132849](https://github.com/aditi55/Microservices/assets/67974030/28a9b935-f9dd-4f77-893a-a8962599983d)

3. ### Circuit breaker pattern with Resilienc4J

   ![Screenshot 2023-10-29 133547](https://github.com/aditi55/Microservices/assets/67974030/ba835de3-e0f0-4eb2-b6d2-9d261305365a)

   Retry mechanism - After every 5 sec – 1 retry out of 3 total retries

   ![Screenshot 2023-10-29 133942](https://github.com/aditi55/Microservices/assets/67974030/19445ef9-a255-481f-a1aa-2a9d6cba3871)

4. ### Containerization of services

    #### 1. Add Jib Plugin to pom.xml
    #### 2. Build and Push the Image:
    Run the following Maven command to build and push the container image to the specified Docker registry :
    
```
mvn clean compile jib:build
```  

   ```
   docker compose up -d
   ```
   
   ![Screenshot 2023-11-17 160905](https://github.com/aditi55/Microservices/assets/67974030/de076e0f-f1d6-4f91-868f-4602c0976435)



6. ###  Service discovery and registration using Eureka Server

   ![Screenshot 2023-10-29 134244](https://github.com/aditi55/Microservices/assets/67974030/449cddd5-4daa-4e88-93af-9650712fe58d)


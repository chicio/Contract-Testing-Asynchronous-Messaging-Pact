# Contract Testing Asynchronous Messaging with Pact and MockK

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/chicio/Contract-Testing-Asynchronous-Messaging-Pact/master/LICENSE.md)

A sample project for my blog post [Contract testing asynchronous messaging with Pact and MockK](https://www.fabrizioduroni.it/2021/11/05/contract-testing-asynchronous-messaging-pact-junit-mockk/), where I show how to create write contract tests for an asynchronous messaging architecture with [Pact](https://docs.pact.io "pact") and [MockK](https://mockk.io "mockk").

### Description

This is a quote from the post:

>...In the last weeks I worked with my colleague [Felice Giovinazzo](https://www.linkedin.com/in/felice-giovinazzo-17277b55/) 
on a new feature for the refund process (as may already know from some of
[my previous posts](/2020/12/23/rest-template-webclient-spring-boot/ "kotlin spring boot") in the last 2 years I 
worked mainly on backend applications). In this new feature we have a messaging-based communication between
microservices. The messaging system used is [RabbitMQ](https://www.rabbitmq.com "rabbitmq"). In 
[lastminute.com group](https://www.lastminute.com) we are already using [Pact](https://docs.pact.io "pact doc") to 
implement contract testing for the classic RESTful interaction between microservices...

Click on the link above to read the posts.

# How to 

If you wanna try the example in this repository you just have to:

* start the pact broker by launching the following commands:

```shell
cd pact-broker
docker-compose up
```

* execute the test of the `AccountService` consumer and publish the contract with the following maven command:

```shell
mvn pact:publish
``` 

* execute the test of the `RefundService` producer to verify the pact published

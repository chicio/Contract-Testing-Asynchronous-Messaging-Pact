# Contract Testing Asynchronous Messaging with Pact

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/chicio/Contract-Testing-Asynchronous-Messaging-Pact/master/LICENSE.md)

A sample project for my blog post [XXXX](https://www.fabrizioduroni.it/XXX.html), where I show how to create write contract tests for an asynchronous messaging architecture with [Pact](https://docs.pact.io "pact").

### Description

This is a quote from the post:

>...XXXXXX...

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

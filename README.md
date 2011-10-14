Abiquo Commons AMQP
===================

One library to use RabbitMQ in an unified way between Abiquo's modules. It includes producers, consumers and exchange/binding/queue configurations for each module.

Configuration properties
------------------------

* **abiquo.rabbitmq.host** where RabbitMQ is listening, by default *localhost*
* **abiquo.rabbitmq.port** where RabbitMQ is binded, by default *5672*
* **abiquo.rabbitmq.username** the username to use, by default *guest*
* **abiquo.rabbitmq.password** the password to use, by default *guest*
* **abiquo.rabbitmq.virtualHost** virtual host to use, by default */*

The following properties are used when the connection with RabbitMQ has been lost

* **abiquo.retry.retries** number of retries to perform (0 for infinite), by default *0* 
* **abiquo.retry.mstosleep** number of milliseconds to sleep beetwen retry attempts, by default *10000*


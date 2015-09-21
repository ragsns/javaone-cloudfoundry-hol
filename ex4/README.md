#Cloud Foundry on OpenStack Hands-On Labs

##Exercise 4: Connect to a service

Services are not part of the Cloud Foundry Elastic Runtime but they are an essential part of application deployment, like connecting to a MySQL database, a NoSQL database like MongoDB or Redis and so on.

In principle, they comprise of the following steps.

- Pick a service via the Marketplace
- Create the service
- Bind the application to the service (or connect to the service by an explicit binding in manifest.yml)

We started the application previously without connecting to a service. We will now connect to a service.

First, let's explore the marketplace with the following command.

```
cf marketplace
```

You should get a listing of the different offerings, plans and so on as below.

```
+------------+------+------------------------------------------+-------------------+------+--------+----------+---------+--------+------+
| Vendor     | Plan | Description                              | Details           | Free | Public | Provider | Version | Broker | Orgs |
+------------+------+------------------------------------------+-------------------+------+--------+----------+---------+--------+------+
| filesystem | free | Persistent filesystem service            | dummy description | yes  | yes    | core     | 1.0     |        |      |
| memcached  | free | Memcached in-memory object cache service | dummy description | yes  | yes    | core     | 1.4     |        |      |
| mongodb    | free | MongoDB NoSQL store                      | dummy description | yes  | yes    | core     | 2.4     |        |      |
| mysql      | free | MySQL database service                   | dummy description | yes  | yes    | core     | 5.5     |        |      |
| postgresql | free | PostgreSQL database service              | dummy description | yes  | yes    | core     | 9.1     |        |      |
| rabbitmq3  | free | RabbitMQ message queue                   | dummy description | yes  | yes    | core     | 3.1     |        |      |
| redis      | free | Redis key-value store service            | dummy description | yes  | yes    | core     | 2.8     |        |      |
+------------+------+------------------------------------------+-------------------+------+--------+----------+---------+--------+------+
```

We pick a RabbitMQ service to connect with the application as below. It is strongly recommended that you provide an unique name for the service.

```
cf create-service rabbitmq3 rabbitmq --plan free
```

You should see a response like below.

```
  Creating new service ... OK
```

If you run the command

```
cf services
```
You should see an output that looks something like below. The column Application that is blank indicates that no application is bound to it (yet).

```
+----------------------------+----------+-----------+----------+---------+------+--------------+
| Space                      | Name     | Service   | Provider | Version | Plan | Applications |
+----------------------------+----------+-----------+----------+---------+------+--------------+
| user101-org::user101-space | rabbitmq | rabbitmq3 | core     | 3.1     | free | node-env     |
|                            |          |           |          |         |      | pcfdemo      |
+----------------------------+----------+-----------+----------+---------+------+--------------+
```

Next, we bind the service to the application using the following command. If necessary, restage the application.

```
cf bind-service rabbitmq pcfdemo
```

You should see an output that looks something like below.

```
  Binding [rabbitmq] to pcfdemo ... 
Stopping Application [pcfdemo] ... OK
Starting Application [pcfdemo] ... 
OK
http://pcfdemo-adabb.15.125.77.39.xip.io/ deployed
```

If you browse to the URL above, you should see a message "Data being streamed from RabbitMQ," indicating that the rabbitmq service was bound to the application. You can rerun the `cf services` command to verify this.

If you click on any state you will notice that data is indeed being streamed via RabbitMQ.

Can you try to unbind the service from the application with an appropriate CLI command (hint: `unbind-service`)?

You can accomplish the same by adding the following line into manifest.yml somewhere, for example, after the `  instances: 1` line as below and then pushing the application again.


```
  instances: 1
  services: [rabbitmq]
```

Run the following command after connecting to a service.

```
cf env pcfdemo
```

This will output the connection credentials to the service via variables in the environment that the application uses to determine if connected to the RabbitMQ service and how to connect to it.

Now that we've connected to the service, we will scale it.














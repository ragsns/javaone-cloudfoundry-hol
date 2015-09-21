#Cloud Foundry on OpenStack Hands-On Labs

##Exercise 5: Scale the Application

Scaling an application is relatively straightforward. However, it's really upto the application to exploit scaling out by effectively using the multiple instances. A Queueing application for instance can scale out by adding more workers.

First, let's scale down the application to 1 instance by using the following command.

```
cf scale -i=1 pcfdemo
```

Let's scale the application out to 2 instances by using the following command.

```
cf scale -i=2 pcfdemo
```

You should see an output that looks something like below.

```
  Scaling Application instances up to 2 ...
Committing changes ...
```

The command

```
cf apps
```

Should yield the following output showing that there are 2 instances of the application running.
```
+-------------+---+-----+---------+------------------------------------------+----------+
| Application | # | Mem | Health  | URLs                                     | Services |
+-------------+---+-----+---------+------------------------------------------+----------+
| pcfdemo     | 2 | 300 | RUNNING | http://pcfdemo-adabb.15.125.77.39.xip.io | rabbitmq |
+-------------+---+-----+---------+------------------------------------------+----------+
```

If you browse to the URL and hit the refresh button a few times you'll notice the hosted instance cycling through 2 different values, such as

```
Instance hosted at  172.17.0.158:58486
Instance hosted at  172.17.0.159:51345
```

The instance index also cycles between 0..1.

This indicates the load on the application is balanced amongst the 2 instances thereby achieving scale out.

Let's scale back down to 1 instance.

```
cf scale -i=1 pcfdemo
```

Can you try to scale up and down the application instead of scaling it out (hint: you have to manipulate the memory size)?

Next we'll stop this application. We notice that the platform which is monitoring the health of the application automatically restarts it.


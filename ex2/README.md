#Cloud Foundry on OpenStack Hands-On Labs

##Exercise 2: Push your application

We will use the Pivotal Cloud Foundry field engineering [demo.](https://github.com/Pivotal-Field-Engineering/PCF-demo) that is also [locally available](../samples/PCF-demo). For your convenience, symbolic links are provided. However, you can use the [samples directory](../samples/PCF-demo) to do the same.

Push the application with the following command. The `-n` options picks the defaults. You can try without the `-n` option and pick the defaults otherwise.

It is recommended to provide your own unique name for the application in the file.

```
cf push -n
```

You should see at output something like below.

```
Using manifest file "manifest.yml"
Instances:         1
Application Url:   http://pcfdemo-53590.15.126.133.139.xip.io

snip -- output deleted

OK
http://pcfdemo-53590.15.126.133.139.xip.io/ deployed
```

Your application has now been deployed. You can verify that by running the following command.

```
cf apps
```

Which should produce an output that looks something like below.

```
+-------------+---+-----+---------+--------------------------------------------+----------+
| Application | # | Mem | Health  | URLs                                       | Services |
+-------------+---+-----+---------+--------------------------------------------+----------+
| pcfdemo     | 1 | 300 | RUNNING | http://pcfdemo-53590.15.126.133.139.xip.io |          |
+-------------+---+-----+---------+--------------------------------------------+----------+
```

If you browse the URL provided you should be able to see a heat map that is not yet active since we've not connected to a service. You will also notice a message "No RabbitMQ service bound - streaming is not active" indicating that the RabbitMQ service is not hooked up the application, yet.

You can get the application logs with the following command. We will sibsequently connect to a log provider such as papertrail or splunk.

```
cf logs pcfdemo
```

You can delete the application with the following command 

```
cf delete -n pcfdemo
```

and verify that it is deleted as below.

```
cf apps
```

```
No Applications
```

We will look at the manifest file and connect to a service, subsequently.







#Cloud Foundry on OpenStack Hands-On Labs

##Exercise 1: Target the Cloud Foundry instance

Make sure you've installed the stackato CLI and created an alias for accessing the Cloud Foundry instance.

Target the Cloud Foundry instance by substituting the URL with the one provided and use the following command if using the Stackato CLI.

```
cf target https://api.15.126.133.139.xip.io
```

The output for the `stackato` CLI should look something like below.

```
Successfully targeted to [https://api.15.126.133.139.xip.io]
Target:       https://api.15.126.133.139.xip.io
Organization: workshop
Space:        workshop
```

The equivalent command when using the `cf` CLI is as below.

```
cf api --skip-ssl-validation https://api.198.11.220.125.xip.io
```

The output for the CF CLI should look something like below.

```
Setting api endpoint to https://api.198.11.220.125.xip.io...
OK

                   
API endpoint:   https://api.198.11.220.125.xip.io (API version: 2.23.0)   
User:           admin   
Org:            default_organization   
Space:          development
```

You can log in with the following command. Login credentials will be provided.

```
cf login username
```

The output will look something line below.

```
Attempting login to [https://api.15.126.133.139.xip.io]
Password:
Successfully logged into [https://api.15.126.133.139.xip.io]
Target:       https://api.15.126.133.139.xip.io
Organization: org
Space:        space
```


You can verify that you are targeted and logged in by running with the following command.

```
cf apps
```

and the services that are installed with the following command.

```
cf marketplace
```

You should see an output that lists the different services and the plans that have been pre-installed for your convenience.

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

We will be pushing an app. and connecting to a service in the upcoming exercises.

You can also try additional options for the CLI.

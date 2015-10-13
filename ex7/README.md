#Cloud Foundry on OpenStack Hands-On Labs

##Exercise 7: Drain Logs

Applications log to stdout in syslog format that can be redirected to a log provider. We will look at [papertrail] (https://papertrailapp.com/) for this.

We will follow the instructions at [http://docs.cloudfoundry.org/devguide/services/log-management-thirdparty-svc.html#papertrail] (http://docs.cloudfoundry.org/devguide/services/log-management-thirdparty-svc.html#papertrail) as below substituting the appropriate value of the URL and the port.

```
cf cups my-logs -l syslog://logs2.papertrailapp.com:33866
```

Bind the service to the app with the following command.

```
cf bind-service pcfdemo my-logs
```

You can verify this on papertrail by restaging the app.

```
cf restage pcfdemo
```

That's it to consolidate your logging.

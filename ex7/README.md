#Cloud Foundry on OpenStack Hands-On Labs

##Exercise 7: Drain Logs

Applications log to stdout in syslog format that can be redirected to a log provider. We will look at [papertrail] (https://papertrailapp.com/) for this.

We will follow the instructions at [http://docs.cloudfoundry.org/devguide/services/log-management-thirdparty-svc.html#papertrail] (http://docs.cloudfoundry.org/devguide/services/log-management-thirdparty-svc.html#papertrail) and use a slightly different command as below substituting the appropriate value of the URL and the port.

```
cf drain add pcfdemo drain tcp://logs2.papertrailapp.com:33866
```

You can verify this on papertrail by restaging the app.

```
cf restage pcfdemo
```

That's it to consolidate your logging.

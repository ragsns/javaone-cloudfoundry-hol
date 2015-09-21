#Cloud Foundry on OpenStack Hands-On Labs

##Exercise 3: manifest.yml and more CLI commands

Notice the manifest.yml in this directory. Its contents looks something like below.

```
---
applications:
- name: pcfdemo
  memory: 300M 
  instances: 1
  host: pcfdemo-${random-word}
  path: ./target/pcfdemo.war
  env:
   JAVA_OPTS: -Djava.security.egd=file:///dev/urandom
```

The manifest.yml file is used for deployment if it's available and it's used to set the properties of the deployed application such as

- name of the application
- memory
- number of instances and so on.
- the path of the artifacts to be deployed, in this case it is target/pcfdemo.war

Change the instances to 2, and push the application again with the following command.

```
cf push -n --reset
```
You should see an output (partially reproduced) which shows the instances changed from 1 to 2.

```
Setting Instances    : 2 (was 1)
```

Also, notice the differences by running the following command which should show 2 running instances.

```
cf apps
```

If necessary, try restaging the application with the following command

```
cf restage pcfdemo
```

Try stopping and restarting the applicaton. If you need help with the different commands, you can get help with the following command.

```
cf --help
```

Change the instances back to 1. Try other commands and changes to `manifest.yml` that catch your fancy and push the app again.

We will connect to a service, next.

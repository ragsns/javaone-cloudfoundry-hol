#Cloud Foundry on OpenStack Hands-On Labs

##Exercise 8: Blue Green Deployments

[Blue/Green deployments](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/blue-green.html) are intended to minimize downtime during application upgrades and enable rollback if necessary. The steps are illustrated in the following diagrams.

![step 1] (http://docs.pivotal.io/pivotalcf/devguide/images/blue-green/blue.png)

![step 2] (http://docs.pivotal.io/pivotalcf/devguide/images/blue-green/blue-green.png)

![step 3] (http://docs.pivotal.io/pivotalcf/devguide/images/blue-green/map.png)

![step 4] (http://docs.pivotal.io/pivotalcf/devguide/images/blue-green/unmap.png)

We will make a simple change to an application, push it. Call this, the Green deployment.

Once we're happy with the Green deployment, we switchover the original route to this new route so that the network traffic is routed to the new application. If we need to rollback to the original Blue Deployment we can do that as well.

Let's push the application.

Ensure that you're in the subdirectory ex8.

The `manifest.yml` file looks like below.

```
---
applications:
- name: pcfdemo-green
  memory: 300M 
  instances: 1
  services: [rabbitmq]
  host: pcfdemo-a64c4
  path: ./target/pcfdemo-green.war
  env:
   JAVA_OPTS: -Djava.security.egd=file:///dev/urandom
```
Pushing this will deploy the Green deployment with the following command

```
cf push -n
```

The Green Deployment has a changed Copyright from 2014 to 2014-2015 and clicking a state on the map will change it's color to Green (got it :-)?)

Once you're happy with the Green deployment, we will switchover from the original (Blue) deployment to the Green deployment.

Run the following command

```
cf routes
```

Which should yield an output like below.

```
Routes: https://api.15.126.133.139.xip.io -> workshop -> workshop
+----------------------------------------------+--------------------+-----------------+
| Url                                          | Space              | Applications    |
+----------------------------------------------+--------------------+-----------------+
| http://pcfdemo-613d1.15.126.133.139.xip.io   | workshop::workshop | pcfdemo-green   |
| http://pcfdemo-a64c4.15.126.133.139.xip.io   | workshop::workshop | pcfdemo         |
+----------------------------------------------+--------------------+-----------------+
```

We map the Blue application to another route (just in case) and we map the new application to the old (or current route as below.

```
cf map pcfdemo pcfdemo-rags-old.15.126.133.139.xip.io'
cf unmap pcfdemo pcfdemo-a64c4.15.126.133.139.xip.io
cf map pcfdemo-green pcfdemo-a64c4.15.126.133.139.xip.io
cf unmap pcfdemo-green pcfdemo-613d1.15.126.133.139.xip.io

```

Now, the Green deployment has been switched over  which can be verified as below

```
cf apps
```

Which should yield an output like below.

```
+-----------------+---+-----+---------+-----------------------------------------------+--------------------+
| Application     | # | Mem | Health  | URLs                                          | Services           |
+-----------------+---+-----+---------+-----------------------------------------------+--------------------+
| pcfdemo         | 1 | 300 | RUNNING | http://pcfdemo-rags-old.15.126.133.139.xip.io | rabbitmq           |
| pcfdemo-green   | 1 | 300 | RUNNING | http://pcfdemo-a64c4.15.126.133.139.xip.io    | rabbitmq           |
+-----------------+---+-----+---------+-----------------------------------------------+--------------------+
```

We just accomplished the Blue/Green deployment just like that.


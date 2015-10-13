#Cloud Foundry Hands-On Labs

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
  host: pcfdemo-${random-word}
  path: ./target/pcfdemo-green.war
  env:
   JAVA_OPTS: -Djava.security.egd=file:///dev/urandom
```
Pushing this will deploy the Green deployment with the following command

```
cf push
```

Browse the app deployed at the URL as provided in the output of the command above.

The Green Deployment has a changed Copyright from 2014 to 2014-2015 and clicking a state on the map will change it's color to Green (got it :-)?)

Once you're happy with the Green deployment, we will switchover from the original (Blue) deployment to the Green deployment.

Run the following command

```
cf routes
```

Which should yield an output like below.

```
Getting routes as raghsrin@us.ibm.com ...

space   host                                             domain          apps         
dev     pcfdemo-clustery-cicatrix                        mybluemix.net   pcfdemo         
dev     pcfdemo-nonderisible-effort                      mybluemix.net   pcfdemo-green  
```

We map the Blue application to another route (just in case) and we map the new application to the old (or current route as below.

```
cf map-route pcfdemo mybluemix.net -n pcfdemo-clustery-ciatrix-old
cf unmap-route pcfdemo mybluemix.net -n pcfdemo-clustery-cicatrix
cf map-route pcfdemo-green mybluemix.net -n pcfdemo-clustery-cicatrix
cf unmap-route pcfdemo-green mybluemix.net -n pcfdemo-nonderisible-effort

```

Now, the Green deployment has been switched over  which can be verified as below

```
cf apps
```

Which should yield an output like below.

```
Getting apps in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

name                          requested state   instances   memory   disk   urls   
pcfdemo                       started           1/1         300M     1G     pcfdemo-clustery-ciatrix-old.mybluemix.net   
pcfdemo-green                 started           1/1         300M     1G     pcfdemo-clustery-cicatrix.mybluemix.net      
```

We accomplished the Blue/Green deployment just like that.


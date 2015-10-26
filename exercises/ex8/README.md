#Cloud Foundry Hands-On Labs

##Exercise 8: Blue Green Deployments

Ensure that you are in sub-directory ex8.

```
cd into <path-to-folder>/javaone-cloudfoundry-hol/ex8 
```

[Blue/Green deployments](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/blue-green.html) are intended to minimize downtime during application upgrades and enable rollback if necessary. The steps are illustrated in the following diagrams.

![step 1] (http://docs.pivotal.io/pivotalcf/devguide/images/blue-green/blue.png)

![step 2] (http://docs.pivotal.io/pivotalcf/devguide/images/blue-green/blue-green.png)

![step 3] (http://docs.pivotal.io/pivotalcf/devguide/images/blue-green/map.png)

![step 4] (http://docs.pivotal.io/pivotalcf/devguide/images/blue-green/unmap.png)

We will make a simple change to an application, push it. Call this, the Green deployment.

Once we're happy with the Green deployment, we want to switchover the original route to this new route so that the network traffic is routed to the new version of the application. If we need to rollback to the original Blue Deployment we can do that as well.

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

The Green Deployment has a changed Copyright from 2014 to 2014-2015 and clicking a state on the map will change it's color (from the original Blue) to Green (got it :-)?)

Once you're happy with the Green deployment, we will switchover from the original Blue deployment to the Green deployment.

Run the following command

```
cf routes --orglevel
```

Which should yield an output like below.

```
Getting routes as raghsrin@us.ibm.com ...

space   host                                             domain          apps   
dev     pcfdemo-chariotlike-infinitive                   mybluemix.net   pcfdemo   
dev     pcfdemo-noncontemptible-horripilation            mybluemix.net   pcfdemo-green  
```

The first route (`pcfdemo` under the column apps) is for the Blue version of application. The Second route is for the Green version of the application.

We map the Blue version of the application to another route (just in case) and we map the route of the new version of the application to the old (or current route of the Blue) version of the application as below.

Map the route of the Blue version to a backup route, just in case.

```
cf map-route pcfdemo mybluemix.net -n pcfdemo-chariotlike-infinitive-backup
```

Unmap the route of the Blue version. App. is no longer available for a short period.

```
cf unmap-route pcfdemo mybluemix.net -n pcfdemo-chariotlike-infinitive
```

Map the Green version to the original Blue route. Ignore the "already exists" message. New version of App is now available at the original route.

```
cf map-route pcfdemo-green mybluemix.net -n pcfdemo-chariotlike-infinitive
```
Unmap the Green version of the route since it's no longer needed.

```
cf unmap-route pcfdemo-green mybluemix.net -n pcfdemo-noncontemptible-horripilation

```
Now, the Green deployment has been switched over  which can be verified as below

```
cf apps
```

Which should yield an output like below.

```
Getting routes as raghsrin@us.ibm.com ...

space   host                                             domain          apps   
dev     pcfdemo-chariotlike-infinitive                   mybluemix.net   pcfdemo-green   
dev     pcfdemo-noncontemptible-horripilation            mybluemix.net      
dev     pcfdemo-chariotlike-infinitive-backup            mybluemix.net   pcfdemo         
```
Notice that the original route for the Blue version of the application is now pointing to the Green version of the application.

We accomplished the Blue/Green deployment just like that.

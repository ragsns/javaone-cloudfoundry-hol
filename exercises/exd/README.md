#Cloud Foundry Hands-On Labs

##Exercise D: Microservices Adventures

Ensure that you are in sub-directory `exd`.

```
cd <path-to-folder>/javaone-cloudfoundry-hol/exd
```

We will look into how the application has been broken upto into finer granular services. One service (`pcfdemo-producer`) will produce the data and the other service (`pcfdemo-map`) will render the data on the map.

First, let's go ahead and delete the running app.

```
cf delete --f --r pcfdemo
```

Let's examine the `manifest.yml` which comprises of the two applications. The decoupling of these applications allows the applications to be developed in different langauages using different data services depending on the composition of the teams.

```
---
applications:
- name: pcfdemo-producer
  buildpack: java_buildpack
  env:
    JBP_CONFIG_SPRING_BOOT_CLI: '{ version: 1.2.7_RELEASE }'
  memory: 512M 
  instances: 1
  host: pcfdemo-producer-${random-word}
  path: PCFDemo-producer/src
  timeout: 90
  services:
   - myrabbit
- name: pcfdemo-map
  memory: 512M 
  instances: 1
  host: pcfdemo-map-${random-word}
  path: PCFDemo-map/target/pcfdemo-map.war
  timeout: 90
  services:
   - myrabbit
```

You can deploy these apps independently. For example

```
cf push pcfdemo-map
cf push pcfdemo-producer
```

Or you can deploy both these apps. with a single push command, just like before

```
cf push
```
For 
You should see at output something like below.

```
Using manifest file /Users/raghavansrinivas/work/HOLs/javaone-cloudfoundry-hol/exercises/exd/manifest.yml

Updating app pcfdemo-producer in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

Creating route pcfdemo-producer-plagiocephalic-nonscience.mybluemix.net...
OK

Binding pcfdemo-producer-plagiocephalic-nonscience.mybluemix.net to pcfdemo-producer...
OK

Uploading pcfdemo-producer...
Uploading app files from: /Users/raghavansrinivas/work/HOLs/javaone-cloudfoundry-hol/exercises/exd/PCFDemo-producer/src
Uploading 5.5K, 8 files
Done uploading               
OK
Binding service myrabbit to app pcfdemo-producer in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

Stopping app pcfdemo-producer in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

Starting app pcfdemo-producer in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
-----> Downloaded app package (4.0K)
-----> Downloaded app buildpack cache (52M)
-----> Java Buildpack Version: v3.3 | https://github.com/cloudfoundry/java-buildpack.git#378f420
-----> Downloading Open Jdk JRE 1.8.0_65 from https://download.run.pivotal.io/openjdk/trusty/x86_64/openjdk-1.8.0_65.tar.gz (found in cache)
       Expanding Open Jdk JRE to .java-buildpack/open_jdk_jre (1.1s)
-----> Downloading Open JDK Like Memory Calculator 2.0.1_RELEASE from https://download.run.pivotal.io/memory-calculator/trusty/x86_64/memory-calculator-2.0.1_RELEASE.tar.gz (found in cache)
       Memory Settings: -XX:MaxMetaspaceSize=64M -Xss995K -Xms382293K -XX:MetaspaceSize=64M -Xmx382293K
-----> Downloading Spring Boot CLI 1.2.7_RELEASE from https://download.run.pivotal.io/spring-boot-cli/spring-boot-cli-1.2.7_RELEASE.tar.gz (found in cache)
       Expanding Spring Boot CLI to .java-buildpack/spring_boot_cli (0.0s)

-----> Uploading droplet (52M)

0 of 1 instances running, 1 starting
0 of 1 instances running, 1 starting
0 of 1 instances running, 1 starting
1 of 1 instances running

App started


OK

App pcfdemo-producer was started using this command `CALCULATED_MEMORY=$($PWD/.java-buildpack/open_jdk_jre/bin/java-buildpack-memory-calculator-2.0.1_RELEASE -memorySizes=metaspace:64m.. -memoryWeights=heap:75,metaspace:10,native:10,stack:5 -memoryInitials=heap:100%,metaspace:100% -totMemory=$MEMORY_LIMIT) && SERVER_PORT=$PORT JAVA_HOME=$PWD/.java-buildpack/open_jdk_jre JAVA_OPTS="-Djava.io.tmpdir=$TMPDIR -XX:OnOutOfMemoryError=$PWD/.java-buildpack/open_jdk_jre/bin/killjava.sh $CALCULATED_MEMORY" $PWD/.java-buildpack/spring_boot_cli/bin/spring run main/groovy/io/pivotal/cf/sample/App.groovy main/groovy/io/pivotal/cf/sample/RabbitClient.groovy`

Showing health and status for app pcfdemo-producer in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

requested state: started
instances: 1/1
usage: 512M x 1 instances
urls: pcfdemo-producer-plagiocephalic-nonscience.mybluemix.net
last uploaded: Thu Jan 14 18:20:21 UTC 2016
stack: cflinuxfs2
buildpack: java_buildpack

     state     since                    cpu    memory           disk           details   
#0   running   2016-01-14 01:21:33 PM   0.0%   407.7M of 512M   151.1M of 1G      
Creating app pcfdemo-map in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

Creating route pcfdemo-map-unoriginal-civilizedness.mybluemix.net...
OK

Binding pcfdemo-map-unoriginal-civilizedness.mybluemix.net to pcfdemo-map...
OK

Uploading pcfdemo-map...
Uploading app files from: /Users/raghavansrinivas/work/HOLs/javaone-cloudfoundry-hol/exercises/exd/PCFDemo-map/target/pcfdemo-map.war
Uploading 620.8K, 62 files
Done uploading               
OK
Binding service myrabbit to app pcfdemo-map in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

Starting app pcfdemo-map in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
-----> Downloaded app package (8.4M)
-----> Java Buildpack Version: v3.3 | https://github.com/cloudfoundry/java-buildpack.git#378f420
-----> Downloading Open Jdk JRE 1.8.0_65 from https://download.run.pivotal.io/openjdk/trusty/x86_64/openjdk-1.8.0_65.tar.gz (8.3s)
       Expanding Open Jdk JRE to .java-buildpack/open_jdk_jre (1.2s)
-----> Downloading Open JDK Like Memory Calculator 2.0.1_RELEASE from https://download.run.pivotal.io/memory-calculator/trusty/x86_64/memory-calculator-2.0.1_RELEASE.tar.gz (0.7s)
       Memory Settings: -Xms382293K -XX:MetaspaceSize=64M -Xss995K -Xmx382293K -XX:MaxMetaspaceSize=64M
-----> Downloading Spring Auto Reconfiguration 1.10.0_RELEASE from https://download.run.pivotal.io/auto-reconfiguration/auto-reconfiguration-1.10.0_RELEASE.jar (0.8s)
       Modifying /WEB-INF/web.xml for Auto Reconfiguration
-----> Downloading Tomcat Instance 8.0.30 from https://download.run.pivotal.io/tomcat/tomcat-8.0.30.tar.gz (0.8s)
       Expanding Tomcat to .java-buildpack/tomcat (0.1s)
-----> Downloading Tomcat Lifecycle Support 2.4.0_RELEASE from https://download.run.pivotal.io/tomcat-lifecycle-support/tomcat-lifecycle-support-2.4.0_RELEASE.jar (0.1s)
-----> Downloading Tomcat Logging Support 2.4.0_RELEASE from https://download.run.pivotal.io/tomcat-logging-support/tomcat-logging-support-2.4.0_RELEASE.jar (0.1s)
-----> Downloading Tomcat Access Logging Support 2.4.0_RELEASE from https://download.run.pivotal.io/tomcat-access-logging-support/tomcat-access-logging-support-2.4.0_RELEASE.jar (0.1s)

-----> Uploading droplet (61M)

0 of 1 instances running, 1 starting
1 of 1 instances running

App started


OK

App pcfdemo-map was started using this command `CALCULATED_MEMORY=$($PWD/.java-buildpack/open_jdk_jre/bin/java-buildpack-memory-calculator-2.0.1_RELEASE -memorySizes=metaspace:64m.. -memoryWeights=heap:75,metaspace:10,native:10,stack:5 -memoryInitials=heap:100%,metaspace:100% -totMemory=$MEMORY_LIMIT) &&  JAVA_HOME=$PWD/.java-buildpack/open_jdk_jre JAVA_OPTS="-Djava.io.tmpdir=$TMPDIR -XX:OnOutOfMemoryError=$PWD/.java-buildpack/open_jdk_jre/bin/killjava.sh $CALCULATED_MEMORY -Daccess.logging.enabled=false -Dhttp.port=$PORT" $PWD/.java-buildpack/tomcat/bin/catalina.sh run`

Showing health and status for app pcfdemo-map in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

requested state: started
instances: 1/1
usage: 512M x 1 instances
urls: pcfdemo-map-unoriginal-civilizedness.mybluemix.net
last uploaded: Thu Jan 14 18:22:02 UTC 2016
stack: cflinuxfs2
buildpack: java_buildpack

     state     since                    cpu    memory           disk           details   
#0   running   2016-01-14 01:23:13 PM   0.0%   344.2M of 512M   141.3M of 1G      
```

Your application has now been deployed. You can verify that by running the following command.

```
cf apps
```

Which should show both the applications running.

If you browse the URL from the deployed `pcfdemo-map` above you should be able to see the heat map. 

RabbitMQ is a bit troublesome. If you notice a message "*No RabbitMQ service bound - streaming is not active*" indicating that the RabbitMQ service is not hooked up the application or if the heat map does not show up, try unbinding, deleting and binding the service again, or try different versions of the `java_buildpack` for `pcfdemo-producer`.
#Cloud Foundry Hands-On Labs

##Exercise 1: Target the IBM Bluemix Cloud Foundry instance

Make sure you've met the following prerequisites.

**Prerequisite 1**: Registered for a Bluemix account that is **still current** (trial Bluemix accounts are available at <http://console.ng.bluemix.net>). Contact the instructor for a promotion code for a bump in the quota. Please note down the `username` (or `email`) and `password` which will be used to login via the `cf` CLI.

**Prerequisite 2**: Installed the Cloud Foundry CLI from <https://github.com/cloudfoundry/cli>.

Target the Bluemix Cloud Foundry instance by substituting the URL with the one provided and use the following command. 

```
cf api https://api.ng.bluemix.net # to Americas
```
**OR**

```
cf api https://api.eu-gb.bluemix.net # to Europe
```


The output for the `cf` CLI should look something like below.

```
Setting api endpoint to https://api.ng.bluemix.net...
OK

                   
API endpoint:   https://api.ng.bluemix.net (API version: 2.27.0)   
Not logged in. Use 'cf login' to log in.  
```

Login to the instance as directed.

```
cf login
```

Substitute the **non-expired** Bluemix account that was created earlier as below.

```
API endpoint: https://api.ng.bluemix.net

Email> <your IBM ID>

Password> 
Authenticating...
OK

Targeted org raghsrin@us.ibm.com

Targeted space dev


                   
API endpoint:   https://api.ng.bluemix.net (API version: 2.27.0)   
User:           raghsrin@us.ibm.com   
Org:            raghsrin@us.ibm.com   
Space:          dev
```


List the spaces with the following command

```
cf spaces
```

The output will look something line below.

```
Getting spaces in org raghsrin@us.ibm.com as raghsrin@us.ibm.com...

name   
dev
```

If there are no space(s) listed, then create a space `dev` with the following command.

```
cf create-space dev
```

The output will look something like below.

```
Creating space dev in org raghsrin@us.ibm.com as raghsrin@us.ibm.com...
OK
Assigning role SpaceManager to user raghsrin@us.ibm.com in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK
Assigning role SpaceDeveloper to user raghsrin@us.ibm.com in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

TIP: Use 'cf target -o raghsrin@us.ibm.com -s dev' to target new space
```

Issue the command as provided in `TIP` above as below to target the newly created space (if required).

```
cf target -o <your IBM ID> -s dev
```

The output will look something like below.

```
API endpoint:   https://api.ng.bluemix.net (API version: 2.27.0)   
User:           raghsrin@us.ibm.com   
Org:            raghsrin@us.ibm.com   
Space:          dev  
```

List the services that are available with the following command (**NOTE: This might take upto a minute or more**)

```
cf marketplace
```

You should see an output that lists the different services and the plans that are available via IBM Bluemix. Partial output is below.

```
Getting services from marketplace in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

service                        plans                                                                                                                                                                          description   
API Management                 Standard v2*                                                                                                                                                                   Publish, manage, and consume APIs.   
AdvancedMobileAccess           Gold*, Bronze*
```

List the apps by issuing the following command.

```
cf apps
```

The output will look something like below.

```
Getting apps in org raghsrin@us.ibm.com / space dev as raghsrin@us.ibm.com...
OK

No apps found
```

We will be pushing an app. and connecting to a service in the upcoming exercises.

You can also try additional options for the CLI.

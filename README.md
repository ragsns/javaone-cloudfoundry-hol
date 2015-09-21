#Cloud Foundry Hands-On Lab

##Prerequisites

Needless to say you'll need a laptop! Any OS is fine, but make sure to install the following prior to the session:

- [Git](http://git-scm.com/downloads) or "brew install git"	
- [The `cf` CLI] (https://github.com/cloudfoundry/cli#downloads) - download the latest version that is appropriate for your laptop and follow the instructions in README.txt. OR the [the `cf` CLI] (http://docs.cloudfoundry.org/devguide/installcf/install-go-cli.html)
- A Cloud Foundry instance, either [running on your laptop](https://github.com/cloudfoundry/bosh-lite) (16GB of laptop RAM recommended) or a hosted instance

During a live HOL, we will help you with a sandbox environment (the details are below). If you are trying these exercises by yourself, we want to provide you choices for deployment. We've tested the exercises on multiple instances.

- [https://run.pivotal.io/](https://run.pivotal.io/)
- [https://console.ng.bluemix.net/] (https://console.ng.bluemix.net/)
- [http://www.activestate.com/stackato/sandbox] (http://www.activestate.com/stackato/sandbox)

##Cloud Foundry Instance

You can target a Cloud Foundry instance either hosted on the cloud or on your laptop.

### If using ActiveState's Stackato instances

Create an alias for stackato as cf. You can create an alias for ```stackato``` as ```cf``` in which case the stackato CLI commands will be approximately similar to the `cf` CLI commands.

The following command worked in my case.

```
alias cf='/Users/srinir16/Downloads/stackato-3.2.1-macosx10.5-i386-x86_64/stackato --skip-ssl-validation'
```

### If using non-Stackato instances

Install the [cf CLI](http://docs.cloudfoundry.org/devguide/installcf/install-go-cli.html).

### Verify the CLI command

Verify that you're using the right command with any of the following commands.

```
cf --version
```

OR

```
cf --help
```

You should see an output like below if using the Stackato CLI.

`stackato 3.2.1 (3.2.1 @ 2015-04-10 14:28:15 -0700)`

Otherwise, you should see an output like below with the CF CLI.

`cf version 6.7.0-c38c991-2014-11-12T01:45:23+00:00`


##Samples and General Directions

Each directory is in a separate sub-directory. ***Ensure that you're in the sub-directory when you're working on a particular exercise and you're issuing the CLI commands from the subdirectory pertaining to the exercise.***

We've also provided a choice of samples. The **instructions will refer to the PCF-demo sample application** but they can be applied to the Node app. or other apps as well.


##Recommended Exercises - User Related

It is recommended that you run through these exercises sequentially since they are progressive with some dependencies. Each exercise should take about 5-10 mins. to complete.

- Exercise 1: [Target the Cloud Foundry Instance](ex1)
- Exercise 2: [Push your application] (ex2)
- Exercise 3: [manifest.yml and more CLI commands] (ex3)
- Exercise 4: [Connect to a service] (ex4)
- Exercise 5: [Scale your application] (ex5)
- Exercise 6: [Health Monitoring] (ex6)
- Exercise 7: [Draining logs] (ex7) 
- Exercise 8: [Blue/Green Deploy] (ex8) [Advanced]

##Recommended Exercises - Admin Related

- Exercise 1: [Quotas] (exa1)
- Exercise 2: [Security Groups] (exa2)
- Exercise 3: [Adminstering Quotas] (exa3)

##More Resources

Plenty of samples in multiple languages at [https://github.com/cloudfoundry-samples] (https://github.com/cloudfoundry-samples)

More samples at [https://github.com/Stackato-Apps] (https://github.com/Stackato-Apps)

##Contact

Please contact us on Twitter @ragss or @bcferrycoder.

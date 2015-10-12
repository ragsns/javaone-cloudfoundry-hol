#Cloud Foundry on OpenStack Hands-On Labs

##Exercise 9: Polyglot Adventures

One of the many benefits of Cloud Foundry is its ability to provision applications built with multiple language and frameworks. Cloud Foundry is equally at home with apps built in Java, Python, PHP, Ruby, Node.js, and custom buildpacks can be used to deploy applications built with almost any languages known to mankind including FORTAN, COBOL, and [Haskall](http://catdevrandom.me/blog/2013/05/16/buildpacks-in-cloud-foundry-v2/), and [Erlang](https://github.com/spiegela/cf-buildpack-erlang).

Since this is JavaOne, we'll stick with JVM-based languages, but this still gives us a nice palette of powerful languages to choose from including Java, Scala, Clojure, Jython, JRuby, Grails.

Here we'll provision a handful of apps using a selection of these languages. Feel free to use the samples provided, or you're more than welcome to bring your own. The steps are the same.

### Clojure

Clojure is basically a modern JVM-based LISP, extremely powerful and expressive. Here we'll deploy the simple Clojure web application ClojureSphere.

Note that Clojure isn't recognized by the default Java buildpack, so you need to specify an external buildpack to get it to run. Conveniently the heroku folks provide [Clojure buildpack](git://github.com/heroku/heroku-buildpack-clojure.git) that works perfectly.


Steps:

1. You'll need the "lein" clojure build tool to package the app. This is trivial to install, following [these steps](http://leiningen.org/#install)

2. Clone the ClojureSphere application

```
   git clone https://github.com/Stackato-Apps/clojuresphere'
```


3. Build the application using lein

  ``` 
     $ cd clojuresphere
     $ lein deps
  ```
  
4. Deploy this application to Cloud Foundry

This requires explicit specification of the external Heroku buildpack for Clojure.

```
   $ cf push clojuresphere  -b git://github.com/heroku/heroku-buildpack-clojure.git
```


5. Now the app is deployed. The output of the push command will show the specific URL to access it, or you can determine this with the "cf apps" command. 

```
$ cf apps

name                      requested state   instances   memory   disk   urls
clojuresphere             started           1/1         1G       1G     clojuresphere.mybluemix.net
```

   http://clojuresphere.mybluemix.net/
   

### Scala

Scala, or SCAlable LAnguage, is a powerful extensible JVM based language that's getting lots of traction. Twitter famously adopted Scala as a core language, and other important companies have followed suit.


We'll install a simple Hello World app built with Scale.

Steps:

1. Install sbt (I think it stands for Simple Build Tool) by following [these steps](http://www.scala-sbt.org/0.13/tutorial/Manual-Installation.html).

2. Clone the hello-scala application

```
   git clone https://github.com/Stackato-Apps/hello-scala.git
```

3. Build the application

```
   cd hello-scala
   sbt clean compile stage
```

4. Deploy this application to Cloud Foundry

```
   cf push
```

5. Now the app is deployed. The output of the push command will show the specific URL to access it, or you can determine this with the "cf apps" command. 

```
$ cf apps

name                      requested state   instances   memory   disk   urls
hello-scala               started           1/1         1G       1G     hello-scala.mybluemix.net
```

6. Visit the hello-scala app in your browser

   http://hello-scala.mybluemix.net/



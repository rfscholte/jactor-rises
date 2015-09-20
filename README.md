# README #

git origin for hjemme (including hjemme-web and hjemme-pom)

### What is this repository for? ###

* Src kode and issues regarding hjemme
* hjemme has none releases yet as this is used mostly for my own development
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* you need a web server to deploy hjemme-web (hjemme.war), this is developed using apache tomcat
* check out code and run mvn clean install on hjemme and hjemme-web
* the code uses an embedded hsql database and no other dependencies are needed
* after deployment, the application is visible on localhost:8080/hjemme/home.do
* to deploy on tomcat, copy the buildt hjemme.war to CATALINE_(HOME/BASE)/webapps

### Some versions used in hjemme ###

* spring framwork 4.2
* hibernate 4.2
* hsqldb 2.3
* hamcrest 1.3
* mockito 1.10
* aspect j 1.8
* opensymphony sitemesh 2.4
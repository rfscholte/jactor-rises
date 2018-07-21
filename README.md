# README #

Source code for jactor-rises (client, model, back-end, and front-end)

### What is this repository for? ###

* Version 1 of jactor-rises.

### How do I get set up? ###

* some spring-boot applications are created when building (`mvn install`)
    * jactor-persistence-orm which is a standalone rest application which handles the persistence in an object relational model
    * jactor-web which is a web application on apache tomcat running via spring-boot
       * it is necessary to run this application to have a user interface in which to interact with
* these applications can run side by side to get a full working application (using `mvn spring-boot:run` on each application)
* persistence-orm is using h2 (in-memory database), and is not finite

### Why ###

This is version one of my "sandbox" which I use to learn various aspects of java programming.
This repository contains code that is evolved from about the year 2004, going through version control systems as CSV, Subversion, and now Git...

### Some technologies used on jactor-rises ###

* [spring framwork 5.x](https://spring.io/projects/spring-framework)
* [spring-boot 2.x](https://spring.io/projects/spring-boot)
* [hibernate 5.x](http://hibernate.org/orm/)
* [h2](http://h2database.com)
* [junit 5.x](https://junit.org/junit5/)
* [assertj](https://joel-costigliola.github.io/assertj/)
* [mockito](http://site.mockito.org)
* [thymeleaf](https://www.thymeleaf.org)

# hibernate_quickstart

The project is a simple prototype that you can use to 
 
* embed a DB in the project
* configure hibernate

 
It sets up an embedded DB with some basic schema that hibernate uses to access.


## Requirements

* Maven
* Derby - download from http://db.apache.org/derby/derby_downloads.html and install and run in network mode



## Instructions

Clone this git repository

	git clone git@github.com:username/hibernate_quickstart.git

Start Derby in network mode `startNetworkServer`

Execute the maven pom

	cd hibernate_quickstart
	mvn clean install exec:java

This will call the HibernateMain class which calls the Hibernate DAO layer. 

##What it does

* fires up an embedded DB
* populates the DB with some initial data 
* executes some Hibernate code against that DB
* program ends and DB gets garbaged cleaned up

##How it works

To fire up the DB we use instruct Spring 4 to start up and configure a Derby DB

	<jdbc:embedded-database id="dataSource" type="DERBY">
        <jdbc:script location="classpath:db/sql/create-db.sql" />
        <jdbc:script location="classpath:db/sql/insert-data.sql" />
	</jdbc:embedded-database>

This gets executed when the ApplicationContext is initialised in the HibernateMain class.

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

Traditionally we set up hibernate from a properties file that gets pulled into a JNDI ServiceRegistry that hibernate configuration draws upon.

In our case, we have a datasource already created (by Spring) so we need to programmatically add it to the hibernate configuration. The simplest way to do this is just to generate a session out of this datasource. This is quick and dirty as it neglects session management but suits requirements.     

## Enhancing

This project will probably serve as a basis for other code. The best way to do this is to move it away from its original source and work with it. 


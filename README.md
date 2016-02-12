# hibernate_quickstart

The project is a starting point to configure hibernate. It sets up an embedded DB with some basic schema that hibernate uses to access.


## Requirements

* Maven
* Derby - download from http://db.apache.org/derby/derby_downloads.html and install and run in network mode



## Instructions

Clone this git repository

	git clone git@github.com:username/hibernate_quickstart.git

Start Derby in network mode `startNetworkServer`

Execute the maven pom

	cd hibernate_quickstart
	mvn jetty:run

Call the engine from the browser

	http://localhost:8080/

## Enhancing

This project will probably serve as a basis for other code. The best way to do this is to move it away from its original source and work with it.


playing-kundera-cassandra: [Play 2.3.0](http://www.playframework.com/), [Kundera](https://github.com/impetus-opensource/Kundera) & [Cassandra](http://cassandra.apache.org/)
==================================================================================================================================================

A simple CRUD application in Play! Framework using Kundera and Cassandra as a Database.

- Accessing a Cassandra database, using [Kundera](https://github.com/impetus-opensource/Kundera).
- Achieving, Futures to use more idiomatic error handling.
- Accessing JS & CSS libraries by [WebJars](http://www.webjars.org/).
- Bootswatch-United with Twitter Bootstrap 3.1.1 to improve the look and feel of the application

-----------------------------------------------------------------------
###Instructions :-
-----------------------------------------------------------------------
* The Github code for the project is at : [playing-kundera-cassandra](https://github.com/knoldus/playing-kundera-cassandra)
* Clone the project into local system
* To run the Play framework 2.3.0, you need JDK 6 or later
* Install Typesafe Activator if you do not have it already. You can get it from here: [download](http://www.playframework.com/download)
* Install [Cassandra](http://cassandra.apache.org/) if you do not have it already. [Instructions](https://www.digitalocean.com/community/tutorials/how-to-install-cassandra-and-run-a-single-node-cluster-on-a-ubuntu-vps)
* Start Cassandra Server and create schema/tables

`[default@unknown] create keyspace EmployeeExample;
395c9500-bf8d-3985-95a2-ddc055090131
[default@unknown] use EmployeeExample;
Authenticated to keyspace: EmployeeExample
[default@EmployeeExample] create column family employees with comparator=UTF8Type and default_validation_class=UTF8Type and key_validation_class=UTF8Type;
840d66b1-a54a-329e-9a43-dc9f373e9386
[default@EmployeeExample] `

* Execute `activator clean compile` to build the product
* Execute `activator start` to execute the product
* playing-kundera-cassandra should now be accessible at localhost:9000

-----------------------------------------------------------------------
###References :-
-----------------------------------------------------------------------
* [Play Framework 2.3.0](http://www.playframework.com/)
* [Bootstrap 3.1.1](http://getbootstrap.com/css/)
* [Bootswatch-United](http://bootswatch.com/united/)
* [WebJars](http://www.webjars.org/)
* [Kundera](https://github.com/impetus-opensource/Kundera)
* [Cassandra](http://cassandra.apache.org/)

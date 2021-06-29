# JMS
Job Management Service Java

**CASE STUDY - JOB MANAGEMENT SERVICE** 

Time spent on the project - **25th June - 29th June - 4 hours each day.**

TOOLS AND TECHNOOGIES USED:
1. IntelliJ Idea IDE
2. Spring boot, Ms sql server
3. Apache Tomcat Server

HOW TO RUN THE PROJECT:
1. Take the clone of the project 
2. Import the maven project in whichever IDE you want to use. Makesure web server is also running.
3. Start the JMSApplication
4. Cross check if the port:  8074 is alreday in use or not by using this command: -NETSTAT -O -A -N on cmd. 
   If alreday in use, you can change the port in the application.properties file, present in Resources folder.
6. To access the DB - Go to application.properties, which has the connection string and the credentials. Using those, you can connect the DB through SSMS.
7. After the application has started, hit the APis respectively to trigger the Jobs created. e.g http://localhost:8074/dbIsHackedAlert

**Details**:

The Job Management Service uses **Quartz** - Open source framework to schedule events.

For demonstration purposes, I have created an **EmailJob** which uses JavaMail to send emails.

The JobServiceImpl has two services that fetches the email ids from the employee table and sends them all the email. One service - **DailyProgressReminder** Email is sent every day at a specific time. Whereas, another service - **DBIsHackedAlert** Email is sent only once when the API is called.

**The API must be hit once, to trigger the job. Once triggered it will complete the job according to the JobConfig.**

JobConfig is specific to each job. It has fields like **priority, isRunForever, repeatCount.** 

For the two email services that I have created, jobConfig is already defined.

**FLEXIBILITY-**

The scheduler utilities and initiation code is independent of the job created.

New job classes can be added easily, with their respective services which call the scheduler.

**RELIABILITY-**

In case the job fails, the trigger state is ‘failed’. However, it does not stop the application.

**INTERNAL CONSISTENCY-**

The triggers are set to its appropriate state. This can be seen in the table **QRTZ\_TRIGGERS** table after hitting the API. States are waiting, paused, complete, failed, blocked erc… They are set accordingly.

**PRIORITY-**

Priority can be set as well. In this application, I have set high priority for DBIsHacked since this is an essential email that every employee must receive. By **default priority is 5.** 

**Higher the number greater the priority.**

**SCHEDULING**-

The jobs can be scheduled, in this example I have only set schedules minute wise but it can be modified further by creating cronTriggers etc.. There is much more that can be done using this framework. However, I have kept it simple in this application. 

Jobs can be run once and immediately as well, **by setting repeatCount = 1 and isRunForever = false (by default it is set to false).** 





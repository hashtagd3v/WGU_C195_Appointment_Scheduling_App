# Appointment Scheduling App

Appointment Scheduling App is an application written in Java that is connected to a mySql Database and performs CRUD operations.
This app enables a user to log in, add/delete/update a customer to the database, add/delete/update an appointment to the database,
and run several reports. This app also tracks successful and failed user log ins to a text file (login_activity.txt).

# Further App Info:

Author: Jay Michalek
Contact email: jalfant@wgu.edu
Application version 1.0.0
Date: March 7, 2021

# Tools:

IDE: IntelliJ IDEA 2020.3.2 (Community Edition)
     Build #IC-203.7148.57, built on January 25, 2021
     Runtime version: 11.0.9.1+11-b1145.77 amd64
     VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
     Windows 10 10.0
     GC: ParNew, ConcurrentMarkSweep
     Memory: 1965M
     Cores: 8
     Registry: debugger.watches.in.variables=false
     Non-Bundled Plugins: com.jetbrains.ChooseRuntime

JDK: Java SE 11.0.9

JavaFX: Version 11.0.2

# How to run the program:

Upon starting the app, log in screen will pop up. Input username and password to log in. Next screen will give you two
choices: Customers/Appointments. Select Customer to go to the list of customers (shown in table view), and add/update/
delete customers. Select Appointments to go to the list of appointments (also shown in table view), add add/update/
delete appointments. To run reports, select Appointments on the Welcome screen. Select "Reports" then choose between
three available reports: Sort Customer Appointments (generates a report for number of appointments by type per month),
Contact Appointment Schedule (generates a report for listing appointments by contact name), and lastly, Appointments Today
Report is the third chosen report (generates a convenient report for the user to quickly check scheduled appointments
for current day).


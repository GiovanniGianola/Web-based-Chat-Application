# **Web Chat Application** #


##  General information ##

The basic idea of the project is to link different and new technologies to create a simple Chat. All program is designed following **Model–view–controller (MVC)** architectural design pattern.

##  Design: ##
The design of the App is very simple it is fundamentally based on two Web pages: 

* The first one is the **Login** page, it permits to sign in the Web Chat and, if you are not register yet, to sign up.
* The second one is the **Chat**, once you logged in, a new web page will show you all the registered contacts and allow you to chat with every one of them just clicking on his tab.

## Main Features: ##
The following features are the main functionalities of the Application:

* Safe **Registration** and **Login** (no confidential data needed).
* Two different kind of chat: **Private Chat** with one of the other registered users, **Chat Room** that allows to write to everyone connected to the system.
* All the chats are **persistent** and the system automatically save all the conversation histories.
* The system keep inform every client who is **online** and who is not during all the client session.
* Possibility to **search** contacts in the system.
* Possibility to **clean** and **wipe** the history of a specified conversation.


## Front End Technologies: ##
**Client-side** development is the practice of producing HTML, CSS and JavaScript for a website or Web Application so that a user can see and interact with them directly.

* The structure of all the web pages is made in **HTML** such as buttons, text boxes and panels
* It also defined two different **CSS** (Cascading Style Sheets) files used for describing the presentation of the documents written in HTML. I.e. it determines the background color of the page and the objects positions.
* **JavaScript** acts as an intermediary between front-end and back-end side, in fact it catches all the guest inputs and send them to the server with a properly **AJAX requests** (Asynchronous JavaScript and XML) functions.
* The communication protocol between the presentation layer and the data access layer are made of **JSON Objects** messages.


## Back End Technologies: ##
**Server-side** represents the data access layer is made by a Java server and a MySQL Database.

* A **Java servlet** is a Java program that extends the capabilities of a server. Although servlets can respond to any types of requests made by front-end, they implement applications hosted on Web servers.
* **Hibernate ORM** is an object-relational mapping framework for the Java language. It provides a framework for mapping an object-oriented domain model to a relational database. Hibernate solves object-relational impedance mismatch problems by replacing direct, persistent database accesses with high-level object handling functions. Hibernate also provides data query and retrieval facilities. It generates SQL calls and relieves the developer from manual handling and object conversion of the result set.

## Credits ##

### Designed by: ###

***Giovanni Gianola***  

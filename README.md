# tasks-manager
Application which allows users to manage tasks.

There are users with functionality:

login: user1
/ password: user1
/ available: add new task

login: user2
/ password: user2
/ available: add new task

login: admin
/ password: admin
/ available: add new task; edit task; delete task

How to start application:

1. Git clone https://github.com/MarGrzy/tasks-manager.git

2. cd ./task-manager
	a) frontend
		- npm install
		- ng serve —open
		
	b) backend
		- Be sure your have installed java on your computer, to check it write java -version
		- Be sure you have installed maven, to check it write mvn --version
		- mvn package
		- java -jar target/tasks-manager-0.0.1.jar
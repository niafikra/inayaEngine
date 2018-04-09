naya is a very very opinionated way of building applications that won’t exceed the 10,000 concurrent users limit. It is aimed at enterprise applications or internal applications.

inaya provides the framework where most of the difficult Architectural and design decisions for such applications have already been made. Based on these decisions it provides tools which can be used to add functionality to it. 

This additional functionalities are called modules. To the end user inaya by itself is pretty useless. Add modules and it comes to life. Your typical inaya developer will spend most of his time writing modules which solve specific user problems. Modules are not meant to work in isolation only, they can also corroborate with other modules to solve the end user’s problem.

This repository contains the core engine
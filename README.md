# IoCContainer

A simple IoC container implementation in Kotlin.
Project has been created using Intellij IDEA.
It provides a flexible way to register and retrieve dependencies, and resolve interfaces with their implementations.

Features
* Dependency registration: The Container class allows you to register dependencies using either transient or singleton scopes. Transient dependencies are created each time they are resolved, while singleton dependencies are created only once and reused.
* Interface mapping: The Container supports interface mapping, allowing you to register an interface with its corresponding implementation. This enables resolving interfaces and retrieving their concrete implementations.
* Unit tests: The project includes a set of unit tests to validate the functionality of the Container class and ensure that dependency resolution works as expected.
* Modular structure: The project is structured with a separate module for the IoC container implementation (IoC module) and a main project module (Main module) to demonstrate its usage.


The Main module demonstrates a basic setup to showcase how to use the Container class. It consists of the following components:

Logger interface: Represents a logging mechanism.
ConsoleLogger implementation: Implements the Logger interface and logs messages to the console.
UserService class: A simple user management service that depends on a Logger implementation.
UserController class: Another user management component that depends on the UserService.
The Main module shows how to register the dependencies and retrieve instances from the Container to demonstrate the usage of the IoC container.

import com.example.ioc.Container
import logger.ConsoleLogger
import logger.Logger
import userManagement.UserController
import userManagement.UserService

fun main(args: Array<String>) {
    val container = Container()

    // Register dependencies
    container.registerSingletonInterface(Logger::class, ConsoleLogger::class) { ConsoleLogger() }
    container.registerInterface(UserService::class, UserService::class) { UserService(container.resolve()) }
    container.registerInterface(UserController::class, UserController::class) { UserController(container.resolve()) }

    // Resolve and use components
    val logger = container.resolve<Logger>()
    val userController = container.resolve<UserController>()

    // Demonstrate usage
    logger.log("Initializing application...")
    userController.createUser("JohnDoe")
}
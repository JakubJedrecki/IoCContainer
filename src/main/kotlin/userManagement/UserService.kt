package userManagement

import logger.Logger

class UserService(private val logger: Logger) {
    fun addUser(username: String) {
        logger.log("User '$username' added.")
    }
}
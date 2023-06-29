package userManagement

class UserController(private val userService: UserService) {
    fun createUser(username: String) {
        userService.addUser(username)
    }
}
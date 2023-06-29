package logger

class ConsoleLogger : Logger {
    override fun log(message: String) {
        println("[ConsoleLogger] $message")
    }
}
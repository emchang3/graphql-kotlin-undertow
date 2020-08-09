package services

import dto.Greeting

class GreetingService {
    fun greeting() = Greeting(message = "你好，世界！")
}

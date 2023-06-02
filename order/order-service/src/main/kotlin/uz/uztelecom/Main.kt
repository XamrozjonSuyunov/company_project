package uz.uztelecom

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Main

fun main(args: Array<String>) {
    SpringApplication.run(Main::class.java, *args)
}
package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class HexagonalApplication

fun main(args: Array<String>) {
    runApplication<HexagonalApplication>(*args)
}
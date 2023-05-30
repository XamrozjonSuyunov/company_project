package uz.uztelecom.`order-service`.config

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class OrderDBSourceConfig {

    fun connectToDatabase(): Connection? {
        val url = "jdbc:postgresql://172.23.0.1:5434/orderdb"
        val username = "postgres"
        val password = "root123"

        return try {
            Class.forName("org.postgresql.Driver")
            DriverManager.getConnection(url, username, password)
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }
}
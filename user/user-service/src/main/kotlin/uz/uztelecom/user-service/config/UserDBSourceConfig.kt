package uz.uztelecom.`user-service`.config

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class UserDBSourceConfig {

    fun connectToDatabase(): Connection? {
        val url = "jdbc:postgresql://localhost:5433/userdb"
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
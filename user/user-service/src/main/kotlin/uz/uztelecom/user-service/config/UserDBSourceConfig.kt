package uz.uztelecom.`user-service`.config

import org.apache.logging.log4j.LogManager
import java.io.FileInputStream
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

class UserDBSourceConfig {
    private val logger = LogManager.getLogger()

    fun connectToDatabase(): Connection? {
        val properties = Properties()

        val filePath = "D:\\new\\company_project\\user\\user-service\\src\\main\\resources\\default-config.properties"
        // Load the properties file
        val configFile = FileInputStream(filePath)
        properties.load(configFile)
        configFile.close()

        // Access the properties
        val url = properties.getProperty("url")
        val username = properties.getProperty("username")
        val password = properties.getProperty("password")

//        val url = "jdbc:postgresql://localhost:5433/userdb"
//        val username = "postgres"
//        val password = "root123"

        return try {
            Class.forName("org.postgresql.Driver")
            DriverManager.getConnection(url, username, password)
        } catch (e: SQLException) {
            logger.error("Failed to establish a database connection.")
            e.printStackTrace()
            null
        }
    }
}
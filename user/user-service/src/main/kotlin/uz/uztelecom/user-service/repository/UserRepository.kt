package uz.uztelecom.`user-service`.repository

import uz.uztelecom.`user-service`.config.UserDBSourceConfig
import uz.uztelecom.common.model.UserDTO

class UserRepository(private val userDbSourceConfig: UserDBSourceConfig) {

    fun findById(userId: String): UserDTO? {
        val connection = userDbSourceConfig.connectToDatabase()
        var userDTO: UserDTO? = null;
        println(connection)
        if (connection != null) {
            val statement = connection.createStatement()
            val query = "select * from users where id='$userId'"
            val resultSet = statement.executeQuery(query)

            while (resultSet.next()) {
                val id = resultSet.getString("id")
                val name = resultSet.getString("name")
                val phone = resultSet.getString("phone_number")
                // Process the data as needed
                println("ID: $id, Name: $name, Phone: $phone")

                userDTO = UserDTO(id, name, phone);
            }

            resultSet.close()
            statement.close()
            connection.close()

            return userDTO;
        } else {
            return userDTO;
        }
    }
}
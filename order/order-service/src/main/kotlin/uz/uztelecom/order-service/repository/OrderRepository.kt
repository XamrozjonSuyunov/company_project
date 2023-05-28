package uz.uztelecom.`order-service`.repository

import uz.uztelecom.`order-service`.config.OrderDBSourceConfig
import uz.uztelecom.common.model.OrderDTO
import uz.uztelecom.common.model.OrderResponseDTO

class OrderRepository(private val orderDbSourceConfig: OrderDBSourceConfig) {

    fun create(userId: String, orderDTO: OrderDTO): OrderResponseDTO {
        val connection = orderDbSourceConfig.connectToDatabase()
        if (connection != null) {

            val insertQuery = "insert into orders (id, product_id, quantity, user_id) VALUES (?, ?, ?, ?)"
            val preparedStatement = connection.prepareStatement(insertQuery)
            val random: Int = (Math.random() * 100000).toInt()

            preparedStatement.setString(1, "$random")
            preparedStatement.setString(2, "${orderDTO.productId}")
            preparedStatement.setInt(3, "${orderDTO.quantity}".toInt())
            preparedStatement.setString(4, "$userId")

            preparedStatement.executeUpdate()

            preparedStatement.close()
            connection.close()

            return OrderResponseDTO(true, "successfully")
        } else {
            println("Failed to establish a database connection.")
            return OrderResponseDTO(false, "Error")
        }
    }
}
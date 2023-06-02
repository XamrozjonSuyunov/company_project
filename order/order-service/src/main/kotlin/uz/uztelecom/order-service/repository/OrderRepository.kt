package uz.uztelecom.`order-service`.repository

import org.apache.logging.log4j.LogManager
import uz.uztelecom.`order-service`.config.OrderDBSourceConfig
import uz.uztelecom.common.model.OrderDTO
import uz.uztelecom.common.model.OrderResponseDTO


class OrderRepository(
    private val orderDbSourceConfig: OrderDBSourceConfig
) {
    private val logger = LogManager.getLogger()

    fun createOrder(userId: String, orderDTO: OrderDTO): OrderResponseDTO {
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
            logger.error("Failed to establish a database connection.")
            return OrderResponseDTO(false, "Error")
        }
    }

    fun editOrder(orderId: String, orderDTO: OrderDTO): OrderResponseDTO {
        val connection = orderDbSourceConfig.connectToDatabase()
        if (connection != null) {
            val productId: String = orderDTO.productId
            val quantity: Int = orderDTO.quantity

            val insertQuery = "update orders set product_id=?, quantity=? where id=?"
            val preparedStatement = connection.prepareStatement(insertQuery)
            val random: Int = (Math.random() * 100000).toInt()

            preparedStatement.setString(1, "${orderDTO.productId}")
            preparedStatement.setInt(2, "${orderDTO.quantity}".toInt())
            preparedStatement.setString(3, "$orderId")

            preparedStatement.executeUpdate()

            preparedStatement.close()
            connection.close()

            return OrderResponseDTO(true, "successfully")
        } else {
            logger.error("Failed to establish a database connection.")
            return OrderResponseDTO(false, "Error")
        }
    }

    fun deleteOrder(orderId: String): OrderResponseDTO {
        val connection = orderDbSourceConfig.connectToDatabase()
        var orderDTO: OrderDTO? = null;
        println(connection)
        if (connection != null) {
            val statement = connection.createStatement()
            val query = "DELETE FROM orders WHERE id = ?"
//            statement.executeUpdate(query)
            val preparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, "$orderId")

            preparedStatement.executeUpdate()
            preparedStatement.close()
            connection.close()

            return OrderResponseDTO(true, "Successfully")
        }

        logger.error("Failed to establish a database connection.")
        return OrderResponseDTO(false, "Error")
    }

    fun getOrder(orderId: String): OrderDTO? {
        val connection = orderDbSourceConfig.connectToDatabase()
        var orderDTO: OrderDTO? = null;
        println(connection)
        if (connection != null) {
            val statement = connection.createStatement()
            val query = "select * from orders where id='$orderId'"
            val resultSet = statement.executeQuery(query)

            while (resultSet.next()) {
                val id = resultSet.getString("id")
                val productId = resultSet.getString("product_id")
                val quantity = resultSet.getInt("quantity")
                val userId = resultSet.getString("user_id")
                // Process the data as needed
//                println("ID: $id, Name: $name, Phone: $phone")

                orderDTO = OrderDTO(productId, quantity);
            }

            resultSet.close()
            statement.close()
            connection.close()

            return orderDTO;
        } else {
            logger.error("Failed to establish a database connection.")
            return orderDTO;
        }
    }

    fun orderList(): List<OrderDTO>? {
        val connection = orderDbSourceConfig.connectToDatabase()
        val list: ArrayList<OrderDTO> = ArrayList();
        println(connection)
        if (connection != null) {
            val statement = connection.createStatement()
            val query = "select * from orders"
            val resultSet = statement.executeQuery(query)
            while (resultSet.next()) {
                val id = resultSet.getString("id")
                val productId = resultSet.getString("product_id")
                val quantity = resultSet.getInt("quantity")
                val userId = resultSet.getString("user_id")
                // Process the data as needed
//                println("ID: $id, Name: $name, Phone: $phone")

                list.add(OrderDTO(productId, quantity))
            }

            return list
        } else {
            logger.error("Failed to establish a database connection.")
            return list;
        }
    }
}
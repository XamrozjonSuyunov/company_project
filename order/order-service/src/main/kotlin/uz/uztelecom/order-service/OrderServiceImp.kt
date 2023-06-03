package uz.uztelecom.`order-service`

import org.apache.logging.log4j.LogManager
import uz.uztelecom.`order-common`.model.OrderService
import uz.uztelecom.`order-service`.config.OrderJsonRpcClient
import uz.uztelecom.`order-service`.config.OrderDBSourceConfig
import uz.uztelecom.`order-service`.repository.OrderRepository
import uz.uztelecom.common.model.OrderDTO
import uz.uztelecom.common.model.OrderResponseDTO

class OrderServiceImp : OrderService {

    private val orderRepository: OrderRepository = OrderRepository(OrderDBSourceConfig())
    private val logger = LogManager.getLogger()
    private val jsonRpcClient = OrderJsonRpcClient()

    override fun createOrder(userId: String, productId: String, quantity: Int): OrderResponseDTO? {
        val userDTO = jsonRpcClient.getUser(userId)

//        val userDTO: UserDTO? = userService.getCustomerInfo(userId)

        if (userDTO != null) {
            val orderDTO: OrderDTO = OrderDTO(productId, quantity)
            val orderResponseDTO: OrderResponseDTO? = orderRepository.createOrder(userId, orderDTO)

            return orderResponseDTO
        } else {
            logger.error("Service connection problem")
        }

        return OrderResponseDTO(false, "error")
    }

    override fun editOrder(orderId: String, productId: String, quantity: Int): OrderResponseDTO? {
        val orderDTO: OrderDTO? = orderRepository.getOrder(orderId)

        if (orderDTO != null) {
            val order = OrderDTO(productId, quantity)
            val orderResponseDTO: OrderResponseDTO? = orderRepository.editOrder(orderId, order)

            return orderResponseDTO
        }

        return OrderResponseDTO(false, "error")
    }

    override fun deleteOrder(orderId: String): OrderResponseDTO? {
        val orderDTO: OrderDTO? = orderRepository.getOrder(orderId)

        if (orderDTO != null) {
            val orderResponseDTO: OrderResponseDTO? = orderRepository.deleteOrder(orderId)

            return orderResponseDTO
        }

        return OrderResponseDTO(false, "error")
    }

    override fun getOrder(orderId: String): OrderDTO? {
        val orderDTO: OrderDTO? = orderRepository.getOrder(orderId)

        return orderDTO
    }

    override fun orderList(): List<OrderDTO>? {
        val orderDTOList: List<OrderDTO>? = orderRepository.orderList()

        return orderDTOList
    }
}
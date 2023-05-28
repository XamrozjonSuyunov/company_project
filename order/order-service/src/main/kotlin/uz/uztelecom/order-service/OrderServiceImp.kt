package uz.uztelecom.`order-service`

import uz.uztelecom.`order-common`.model.OrderService
import uz.uztelecom.`order-service`.repository.OrderRepository
import uz.uztelecom.`user-common`.model.UserService
import uz.uztelecom.common.model.OrderDTO
import uz.uztelecom.common.model.OrderResponseDTO
import uz.uztelecom.common.model.UserDTO

class OrderServiceImp(
    private val orderRepository: OrderRepository,
    private val userService: UserService
) : OrderService {

    override fun createOrder(userId: String, order: OrderDTO): OrderResponseDTO? {
        val userDTO: UserDTO? = userService?.getCustomerInfo(userId)

        if (userDTO != null) {
            val orderDTO: OrderDTO = OrderDTO(order.productId, order.quantity)
            val orderResponseDTO: OrderResponseDTO? = orderRepository.create(userId, orderDTO)

            return orderResponseDTO
        }

        return OrderResponseDTO(false, "error")
    }
}
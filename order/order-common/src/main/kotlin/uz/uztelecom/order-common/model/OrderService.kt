package uz.uztelecom.`order-common`.model

import uz.uztelecom.common.model.OrderDTO
import uz.uztelecom.common.model.OrderResponseDTO

interface OrderService {
    fun createOrder(userId: String, order: OrderDTO): OrderResponseDTO?
}
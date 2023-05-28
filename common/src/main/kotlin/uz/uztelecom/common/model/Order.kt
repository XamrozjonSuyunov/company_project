package uz.uztelecom.common.model

data class OrderDTO(
    val productId: String,
    val quantity: Int
)

data class OrderResponseDTO(
    val Success: Boolean,
    val Error: String?
)

data class OrderRequestDTO(
    val userId: String,
    val order: OrderDTO
)
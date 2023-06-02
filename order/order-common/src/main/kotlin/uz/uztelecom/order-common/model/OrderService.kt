package uz.uztelecom.`order-common`.model

import com.googlecode.jsonrpc4j.JsonRpcMethod
import com.googlecode.jsonrpc4j.JsonRpcParam
import com.googlecode.jsonrpc4j.JsonRpcService
import uz.uztelecom.common.model.OrderDTO
import uz.uztelecom.common.model.OrderResponseDTO

@JsonRpcService("order")
interface OrderService {
    @JsonRpcMethod("createOrder")
    fun createOrder(@JsonRpcParam(value = "userId") userId: String, @JsonRpcParam(value = "productId") productId: String, @JsonRpcParam(value = "quantity") quantity: Int): OrderResponseDTO?

    @JsonRpcMethod("editOrder")
    fun editOrder(@JsonRpcParam(value = "orderId") orderId: String, @JsonRpcParam(value = "productId") productId: String, @JsonRpcParam(value = "quantity") quantity: Int): OrderResponseDTO?

    @JsonRpcMethod("deleteOrder")
    fun deleteOrder(@JsonRpcParam(value = "orderId") orderId: String): OrderResponseDTO?

    @JsonRpcMethod("getOrder")
    fun getOrder(@JsonRpcParam(value = "orderId") orderId: String): OrderDTO?

    @JsonRpcMethod("orderList")
    fun orderList(): List<OrderDTO>?
}
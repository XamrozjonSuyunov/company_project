package uz.uztelecom.`order-service`

import com.google.gson.Gson
import io.activej.http.AsyncServlet
import io.activej.http.HttpMethod
import io.activej.http.HttpResponse
import io.activej.http.RoutingServlet
import io.activej.inject.annotation.Inject
import io.activej.inject.annotation.Provides
import io.activej.launchers.http.HttpServerLauncher
import uz.uztelecom.`order-service`.config.OrderDBSourceConfig
import uz.uztelecom.`order-service`.repository.OrderRepository
import uz.uztelecom.`user-common`.model.UserService
import uz.uztelecom.`user-service`.UserServiceImp
import uz.uztelecom.`user-service`.config.UserDBSourceConfig
import uz.uztelecom.`user-service`.repository.UserRepository
import uz.uztelecom.common.model.OrderDTO
import uz.uztelecom.common.model.OrderRequestDTO
import uz.uztelecom.common.model.OrderResponseDTO
import java.nio.charset.StandardCharsets

class OrderLauncher : HttpServerLauncher() {
    private val gson = Gson()

    @Inject
    private val orderService: OrderServiceImp? = null

    @Provides
    fun userService(): UserService = UserServiceImp(UserRepository(UserDBSourceConfig()))

    @Provides
    fun orderService(): OrderServiceImp = OrderServiceImp(OrderRepository(OrderDBSourceConfig()), userService())

    @Provides
    fun servlet(): AsyncServlet {
        return RoutingServlet.create()
            .map(HttpMethod.POST, "/addOrder") {
                it.loadBody().map { _ ->
                    val body = it.body.asString(StandardCharsets.UTF_8)
                    val request = gson.fromJson(body, OrderRequestDTO::class.java)

                    val orderDTO: OrderDTO = OrderDTO(request.order.productId, request.order.quantity);
                    val userId: String = request.userId

                    val orderResponseDTO: OrderResponseDTO? = orderService?.createOrder(userId, orderDTO)

                    HttpResponse.ok200()
                        .withJson(gson.toJson(orderResponseDTO))
                }
            }
    }
}
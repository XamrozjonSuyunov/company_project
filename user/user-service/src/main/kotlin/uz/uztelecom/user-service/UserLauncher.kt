package uz.uztelecom.`user-service`

import com.google.gson.Gson
import io.activej.http.AsyncServlet
import io.activej.http.HttpMethod
import io.activej.http.HttpResponse
import io.activej.http.RoutingServlet
import io.activej.inject.annotation.Inject
import io.activej.inject.annotation.Provides
import io.activej.launchers.http.HttpServerLauncher
import uz.uztelecom.`user-service`.config.UserDBSourceConfig
import uz.uztelecom.`user-service`.repository.UserRepository
import uz.uztelecom.common.model.UserDTO

class UserLauncher : HttpServerLauncher() {
    private val gson = Gson()

    @Inject
    private val userService: UserServiceImp? = null

    @Provides
    fun userService(): UserServiceImp = UserServiceImp()

    @Provides
    fun servlet(): AsyncServlet {
        return RoutingServlet.create()
            .map(HttpMethod.GET, "/users/:userId") {
                it.loadBody().map { _ ->
                    val userId: String = it.getPathParameter("userId")

                    val userDTO: UserDTO? = userService?.getCustomerInfo(userId)

                    HttpResponse.ok200()
                        .withJson(gson.toJson(userDTO))
                }
            }
    }
}
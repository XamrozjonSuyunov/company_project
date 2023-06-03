package uz.uztelecom.`order-service`.config

import com.googlecode.jsonrpc4j.JsonRpcHttpClient
import uz.uztelecom.common.model.UserDTO
import java.net.URL

class OrderJsonRpcClient {
    fun getUser(userId: String): UserDTO? {
        val client = JsonRpcHttpClient(URL("http://localhost:8080/user"))
//        client.connectionTimeoutMillis = 120000
//        val connectionTimeoutMillis = client.connectionTimeoutMillis;
        val params = mapOf("userId" to userId)
        val response = client.invoke("getCustomerInfo", params, Any::class.java)
        val map: LinkedHashMap<*, *>? = response as? LinkedHashMap<*, *>
        if (!map.isNullOrEmpty()) {
//            val userId: String = map["userId"] as String
            val name: String = map["name"] as String
            val phoneNumber: String = map["phoneNumber"] as String

            return UserDTO(userId, name, phoneNumber)
        }

        return null
    }
}
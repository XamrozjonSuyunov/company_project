package uz.uztelecom.`user-common`.model

import com.googlecode.jsonrpc4j.JsonRpcMethod
import com.googlecode.jsonrpc4j.JsonRpcParam
import com.googlecode.jsonrpc4j.JsonRpcService
import uz.uztelecom.common.model.UserDTO

@JsonRpcService("user")
interface UserService {
    @JsonRpcMethod("getCustomerInfo")
    fun getCustomerInfo(@JsonRpcParam(value = "userId") userId: String): UserDTO?
}
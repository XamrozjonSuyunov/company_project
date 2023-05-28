package uz.uztelecom.`user-common`.model

import uz.uztelecom.common.model.UserDTO

interface UserService {
    fun getCustomerInfo(userId: String): UserDTO?
}
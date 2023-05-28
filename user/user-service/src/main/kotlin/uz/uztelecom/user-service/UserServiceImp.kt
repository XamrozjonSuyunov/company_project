package uz.uztelecom.`user-service`

import uz.uztelecom.`user-common`.model.UserService
import uz.uztelecom.`user-service`.repository.UserRepository
import uz.uztelecom.common.model.UserDTO

class UserServiceImp(
    private val userRepository: UserRepository
) : UserService {

    override fun getCustomerInfo(userId: String): UserDTO? {
        val userDTO: UserDTO? = userRepository?.findById(userId);

        return userDTO
    }
}
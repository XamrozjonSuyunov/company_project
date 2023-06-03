package uz.uztelecom.`user-service`

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import uz.uztelecom.`user-common`.model.UserService
import uz.uztelecom.`user-service`.repository.UserRepository
import uz.uztelecom.common.model.UserDTO

class UserServiceImp : UserService {
    private val userRepository: UserRepository = UserRepository()
    private val logger: Logger = LogManager.getLogger()

    override fun getCustomerInfo(userId: String): UserDTO? {
        logger.info("Request: getCustomerInfo userId=$userId")
        println("Request: getCustomerInfo userId=$userId")

        val userDTO: UserDTO? = userRepository.findById(userId);

        return userDTO
    }
}
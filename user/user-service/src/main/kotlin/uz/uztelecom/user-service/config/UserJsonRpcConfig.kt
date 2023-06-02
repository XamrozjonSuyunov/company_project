package uz.uztelecom.`user-service`.config

import com.googlecode.jsonrpc4j.JsonRpcServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.HandlerAdapter
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import uz.uztelecom.`user-common`.model.UserService
import uz.uztelecom.`user-service`.UserServiceImp
import uz.uztelecom.`user-service`.repository.UserRepository
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
open class UserJsonRpcConfig : WebMvcConfigurerAdapter() {
    @Bean
    open fun userService(): UserService? {
        return UserServiceImp(UserRepository(UserDBSourceConfig()))
    }

    @Bean
    open fun dispatcherServlet(): DispatcherServlet? {
        return DispatcherServlet()
    }

    @Bean
    open fun jsonRpcHandlerAdapter(): HandlerAdapter? {
        return object : HandlerAdapter {
            override fun supports(handler: Any): Boolean {
                return true
            }

            @Throws(Exception::class)
            override fun handle(
                request: HttpServletRequest,
                response: HttpServletResponse,
                handler: Any
            ): ModelAndView? {
                // Process the JSON-RPC request using jsonrpc4j library
                val jsonRpcServer =
                    JsonRpcServer(UserServiceImp(UserRepository(UserDBSourceConfig())), UserService::class.java)
                jsonRpcServer.handle(request, response)
                return null
            }

            override fun getLastModified(request: HttpServletRequest, handler: Any): Long {
                return -1
            }
        }
    }

    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }
}
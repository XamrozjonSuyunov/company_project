package uz.uztelecom.`order-service`.config

import com.googlecode.jsonrpc4j.JsonRpcServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.HandlerAdapter
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import uz.uztelecom.`order-common`.model.OrderService
import uz.uztelecom.`order-service`.OrderServiceImp
import uz.uztelecom.`order-service`.repository.OrderRepository
import uz.uztelecom.`user-service`.UserServiceImp
import uz.uztelecom.`user-service`.config.UserDBSourceConfig
import uz.uztelecom.`user-service`.repository.UserRepository
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
open class OrderJsonRpcConfig: WebMvcConfigurerAdapter() {
    @Bean
    open fun orderService(): OrderService? {
        return OrderServiceImp(OrderRepository(OrderDBSourceConfig()), UserServiceImp(UserRepository(UserDBSourceConfig())))
    }

    @Bean
    open fun orderDispatcherServlet(): DispatcherServlet? {
        return DispatcherServlet()
    }

    @Bean
    open fun orderJsonRpcHandlerAdapter(): HandlerAdapter? {
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
                val jsonRpcServer = JsonRpcServer(orderService(), OrderService::class.java)
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

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/order").setViewName("forward:/rpc")
    }
}
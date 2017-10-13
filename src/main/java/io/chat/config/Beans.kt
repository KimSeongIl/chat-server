package io.chat.config

import io.chat.domain.users.User
import io.chat.domain.users.handler.PersonHandler
import io.chat.domain.users.repository.UserRepository
import io.chat.web.routes.Routes
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.RouterFunctions

import org.springframework.context.support.beans
import javax.persistence.EntityManager

/**
 * Created by coupang on 2017. 10. 1..
 */
fun beans() = beans {
    bean<PersonHandler>()
    bean<Routes>()
    bean("webHandler") {
        RouterFunctions.toWebHandler(ref<Routes>().router(), HandlerStrategies.builder().build())
    }
    bean("messageSource") {
        ReloadableResourceBundleMessageSource().apply {
            setBasename("messages")
            setDefaultEncoding("UTF-8")
        }
    }

}
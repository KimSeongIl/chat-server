package io.chat.config

import io.chat.handler.PersonHandler
import io.chat.routes.Routes
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.server.WebHandler

import org.springframework.context.support.beans
/**
 * Created by coupang on 2017. 10. 1..
 */
fun beans() = beans {
    bean<PersonHandler>()
    bean<Routes>()
    bean("messageSource") {
        ReloadableResourceBundleMessageSource().apply {
            setBasename("messages")
            setDefaultEncoding("UTF-8")
        }
    }

}
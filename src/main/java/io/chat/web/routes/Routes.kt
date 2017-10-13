package io.chat.web.routes

import io.chat.domain.users.handler.PersonHandler
import org.springframework.context.MessageSource
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.router

/**
 * Created by coupang on 2017. 10. 1..
 */

class Routes(private val personHandler: PersonHandler,
             private val messageSource: MessageSource) {

    fun router() = router {
        "/api".nest {
            accept(APPLICATION_JSON).nest {
                GET("/users", personHandler::findAll)
            }


        }
        resources("/**", ClassPathResource("static/"))
    }
}
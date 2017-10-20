package io.chat.web.routes

import io.chat.domain.users.handler.PersonHandler
import org.springframework.context.MessageSource
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

class Routes(val personHandler: PersonHandler, val messageSource: MessageSource) {

    fun router() = router {
        "/api".nest {
            accept(APPLICATION_JSON).nest {
                GET("/users", personHandler::findById)
            }

        }
        resources("/**", ClassPathResource("static/"))
    }
}
package io.chat.routes

import io.chat.handler.PersonHandler
import org.springframework.context.MessageSource
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.RenderingResponse
import org.springframework.web.reactive.function.server.router
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.toMono
import java.util.*

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
package io.chat.web.routes

import io.chat.domain.users.handler.PersonHandler
import org.springframework.context.MessageSource
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient
import org.springframework.web.reactive.socket.client.WebSocketClient
import java.net.URI
import reactor.core.publisher.Mono
import java.time.Duration


class Routes(val personHandler: PersonHandler, val messageSource: MessageSource) {

    fun router() = router {
        "/api".nest {
            accept(APPLICATION_JSON).nest {
                GET("/users",  {
                    print("123")
                    val client = ReactorNettyWebSocketClient()
                    client.execute(URI.create("ws://local.coupangdev.com:10001/foo")
                    ) { session ->
                        session.send(Mono.just(
                                session.textMessage("{\"event\":\"ping\"}")))
                                .then()
                    }.block(Duration.ofSeconds(10))
                    personHandler.findById(it)
            })
                GET("/users/password", {
                    personHandler.findByPassword(it)
                })
            }

        }
        resources("/**", ClassPathResource("static/"))
    }
}
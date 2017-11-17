package io.chat.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.chat.domain.users.handler.PersonHandler
import io.chat.web.routes.Routes
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.publisher.SynchronousSink
import java.io.File
import java.nio.file.Files
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer

fun beans() = beans {
    bean<PersonHandler>()
    bean {
        Routes(ref(), ref()).router()
    }
    bean {
        WebSocketHandlerAdapter()
    }
    bean {
        val suhm = SimpleUrlHandlerMapping()
        suhm.order = 10
        suhm.urlMap = mapOf("/ws/files" to wsh())
    }
    bean {
        incomingFilesChannel()
    }
    bean {
        wsh()
    }
//    profile("foo") {
//        bean<Foo>()
//    }
}

fun incomingFilesChannel(): PublishSubscribeChannel {
    return PublishSubscribeChannel()
}

fun wsh() : WebSocketHandler {
    val om = ObjectMapper()
    val connections = ConcurrentHashMap<String, MessageHandler>()

    class ForwardingMessageHandler(val session: WebSocketSession, val sink: FluxSink<WebSocketMessage>): MessageHandler {
        private val sessionId = session.id

        override fun handleMessage(msg: Message<*>) {
            print("ddddddd")
            val payload = msg.payload as File
            val fe = FileEvent(sessionId = sessionId, path = payload.absolutePath)
            val str = om.writeValueAsString(fe)
            val tm = session.textMessage(str)
            sink.next(tm)
        }
    }

    return WebSocketHandler { session ->
        val publisher = Flux.create(Consumer<FluxSink<WebSocketMessage>> { sink ->
            connections[session.id] = ForwardingMessageHandler(session, sink)
            connections[session.id]?.let { incomingFilesChannel().subscribe(it) }
        }).doFinally {
            connections[session.id]?.let { it1 -> incomingFilesChannel().unsubscribe(it1) }
            connections.remove(session.id)
        }

        session.send(publisher)
    }
}

class Foo

data class FileEvent(val sessionId: String, val path: String)

class BeansInitializer : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(context: GenericApplicationContext) =
            beans().initialize(context)
}

package io.chat

import com.fasterxml.jackson.databind.ObjectMapper
import io.chat.config.FileEvent
import io.chat.config.beans
import io.chat.config.incomingFilesChannel
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.http.server.reactive.HttpHandler
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import org.springframework.web.server.adapter.WebHttpHandlerBuilder
import reactor.ipc.netty.http.server.HttpServer
import reactor.ipc.netty.tcp.BlockingNettyContext
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import java.util.HashMap
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer


//
//class Application {
//
//    private val httpHandler: HttpHandler
//    private val server: HttpServer
//    private var nettyContext: BlockingNettyContext? = null
//
//    constructor(port: Int = 8080) {
//        val context = GenericApplicationContext().apply {
//            beans().initialize(this)
//            refresh()
//        }
//        server = HttpServer.create(port)
//        httpHandler = WebHttpHandlerBuilder.applicationContext(context).build()
//    }
//
//    fun start() {
//        nettyContext = server.start(ReactorHttpHandlerAdapter(httpHandler))
//    }
//
//    fun startAndAwait() {
//        server.startAndAwait(ReactorHttpHandlerAdapter(httpHandler),
//                { nettyContext = it })
//    }
//
//    fun stop() {
//        nettyContext?.shutdown()
//    }
//}
//
//fun main(args: Array<String>) {
//    Application().startAndAwait()
//}


@SpringBootApplication
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

@Bean
fun webSocketMapping(): HandlerMapping {
    val map = HashMap<String, WebSocketHandler>()
    map.put("/foo", wsh())

    val mapping = SimpleUrlHandlerMapping()
    mapping.urlMap = map
    return mapping
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
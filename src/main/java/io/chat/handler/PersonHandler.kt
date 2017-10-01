package io.chat.handler

import org.springframework.web.reactive.function.server.ServerResponse
import io.chat.dto.Person
import io.chat.repository.PersonRepository
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.Flux




/**
 * Created by coupang on 2017. 10. 1..
 */
class PersonHandler(private val repository: PersonRepository) {

    private val persons = Flux.just(
            Person(1, "Foo"),
            Person(1, "Foo2"),
            Person(1, "Foo3"))

    fun findAll(req: ServerRequest) =
            ok().body(persons)




}
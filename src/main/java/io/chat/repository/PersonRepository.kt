package io.chat.repository

import io.chat.dto.Person
import reactor.core.publisher.Flux

/**
 * Created by coupang on 2017. 10. 1..
 */
class PersonRepository {
    fun allPeople(): Flux<Person> {
        return Flux.just(Person(id = 1, name = "name"))
    }
}
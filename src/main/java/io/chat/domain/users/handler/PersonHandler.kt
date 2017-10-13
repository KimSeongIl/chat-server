package io.chat.domain.users.handler

import io.chat.domain.users.User
import io.chat.domain.users.dto.UserDto
import io.chat.domain.users.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import java.util.*


/**
 * Created by coupang on 2017. 10. 1..
 */
class PersonHandler(
        userRepository : UserRepository
) {

    private val users = Flux.just(
            userRepository.findById(1L))

    fun findAll(req: ServerRequest) =
            ok().body(users)




}
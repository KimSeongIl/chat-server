package io.chat.domain.users.handler

import io.chat.domain.users.Member
import io.chat.domain.users.repository.MemberRepository
//import io.chat.domain.users.repository.MemberRepository
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class PersonHandler(
        memberRepository: MemberRepository
) {

//    private val aa = memberRepository.findAll()
    private val bb = Mono.just(memberRepository.findById(3))

    private val cc = Flux.just(memberRepository.findByPassword("207240be39cf88db8d86c5dbb6485eef0116a81aaaac523e553851fe54cc1ae6").get())
//    private val cc = memberRepository.findAll()

//    private val users =  Flux.just(Member())

//    fun findAll(req: ServerRequest) =
//        ok().body(aa)

    fun findById(req: ServerRequest) =
            ok().body(bb)

    fun findByPassword(req: ServerRequest) =
            ok().body(cc)

//    fun findAll(req: ServerRequest) =
//        ok().body(aa)


    fun stream() {

    }
}
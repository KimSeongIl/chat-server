package io.chat.domain.users.handler

import io.chat.domain.users.Member
import io.chat.domain.users.repository.MemberRepository
//import io.chat.domain.users.repository.MemberRepository
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux

class PersonHandler(
        memberRepository: MemberRepository
) {

//    private val aa = memberRepository.findAll()
    private val bb = Flux.just(memberRepository.findById(3))
//    private val cc = memberRepository.findAll()

//    private val users =  Flux.just(Member())

//    fun findAll(req: ServerRequest) =
//        ok().body(aa)

    fun findById(req: ServerRequest) =
            ok().body(bb)

//    fun findAll(req: ServerRequest) =
//        ok().body(aa)


    fun stream() {

    }
}
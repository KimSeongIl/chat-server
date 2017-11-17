package io.chat.domain.users.repository

import io.chat.domain.users.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import org.springframework.scheduling.annotation.Async
import java.util.concurrent.CompletableFuture

interface MemberRepository : JpaRepository<Member, Long> {
    @Async
    fun findByPassword(password: String) : CompletableFuture<Member>
}
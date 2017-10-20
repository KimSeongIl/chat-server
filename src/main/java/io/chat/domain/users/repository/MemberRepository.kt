package io.chat.domain.users.repository

import io.chat.domain.users.Member
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MemberRepository : ReactiveCrudRepository<Member, Long> {
}
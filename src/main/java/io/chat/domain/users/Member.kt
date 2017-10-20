package io.chat.domain.users

import javax.persistence.*


@Entity
@Table(name = "member")
class Member (id : Long?, password: String, email: String) {



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null

    @Column(name = "password")
    var password : String = ""

    @Column(name = "email")
    var email : String = ""
}
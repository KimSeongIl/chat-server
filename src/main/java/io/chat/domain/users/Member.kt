package io.chat.domain.users

import javax.persistence.*


@Entity
@Table(name = "user")
class Member {



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null

    @Column(name = "passwd")
    var password : String = ""

    @Column(name = "user_name")
    var email : String = ""
}
package io.chat.domain.users

import javax.persistence.*


@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null
    var auth : Long = 0
    var passwd : String = ""
    var salt : String = ""

    @Column(name = "user_id")
    var userId : String = ""
    @Column(name = "user_name")
    var userName : String = ""
}
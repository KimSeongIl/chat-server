package io.chat.config

import io.chat.domain.users.handler.PersonHandler
import io.chat.web.routes.Routes
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

fun beans() = beans {
    bean<PersonHandler>()
    bean {
        Routes(ref(), ref()).router()
    }
//    profile("foo") {
//        bean<Foo>()
//    }
}

class Foo

class BeansInitializer : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(context: GenericApplicationContext) =
            beans().initialize(context)
}

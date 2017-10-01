package io.chat.config

import javax.servlet.ServletContext
import javax.servlet.ServletRegistration

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

class WebAppInitializer : WebApplicationInitializer {

    override fun onStartup(container: ServletContext) {
        // Create the 'root' Spring application context
        val rootContext = AnnotationConfigWebApplicationContext()
        // rootContext.register(ServiceConfig.class, JPAConfig.class, SecurityConfig.class);

        // Manage the lifecycle of the root application context
        container.addListener(ContextLoaderListener(rootContext))

        // Create the dispatcher servlet's Spring application context
        val dispatcherServlet = AnnotationConfigWebApplicationContext()
        dispatcherServlet.register(WebConfig::class.java)

        // Register and map the dispatcher servlet
        val dispatcher = container.addServlet("dispatcher", DispatcherServlet(dispatcherServlet))
        dispatcher.setLoadOnStartup(1)
        dispatcher.addMapping("/")

    }

}
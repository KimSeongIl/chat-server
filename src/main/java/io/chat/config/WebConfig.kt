package io.chat.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.MediaType
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import javax.servlet.http.HttpServletRequest

@EnableWebFlux
@EnableJpaRepositories
@Configuration
@ComponentScan(basePackages = arrayOf("io.chat.*"))
open class WebConfig : WebFluxConfigurer {

    fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        configurer.ignoreAcceptHeader(true)
                .favorPathExtension(false)
                .favorParameter(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
    }

    fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(object : HandlerMethodArgumentResolver {
            override fun supportsParameter(parameter: MethodParameter): Boolean =
                    parameter.parameterType.isAssignableFrom(ServletRequestAttributes::class.java)

            override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer, webRequest: NativeWebRequest, dataBindFactory: WebDataBinderFactory): Any =
                    ServletRequestAttributes(webRequest.getNativeRequest(HttpServletRequest::class.java))

        })
    }

//    @Autowired
//    fun setUpObjectMapper(mapper: ObjectMapper) {
//        mapper.dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//    }

}
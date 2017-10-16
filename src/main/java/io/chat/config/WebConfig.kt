package io.chat.config

import org.springframework.web.reactive.config.WebFluxConfigurer

//import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
//import javax.servlet.http.HttpServletRequest
//
//@EnableWebFlux
//@Configuration
//@ComponentScan(basePackages = arrayOf("io.chat.*"))
open class WebConfig : WebFluxConfigurer {
//
//    fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
//        configurer.ignoreAcceptHeader(true)
//                .favorPathExtension(false)
//                .favorParameter(false)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//    }
//
//    fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
//        argumentResolvers.add(object : HandlerMethodArgumentResolver {
//            override fun supportsParameter(parameter: MethodParameter): Boolean =
//                    parameter.parameterType.isAssignableFrom(ServletRequestAttributes::class.java)
//
//            override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer, webRequest: NativeWebRequest, dataBindFactory: WebDataBinderFactory): Any =
//                    ServletRequestAttributes(webRequest.getNativeRequest(HttpServletRequest::class.java))
//
//        })
//    }
//
////    @Autowired
////    fun setUpObjectMapper(mapper: ObjectMapper) {
////        mapper.dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
////    }
//
}
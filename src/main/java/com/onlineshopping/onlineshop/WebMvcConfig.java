// package com.onlineshopping.onlineshop;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.view.InternalResourceViewResolver;

// @Configuration
// @EnableWebMvc
// public class WebMvcConfig implements WebMvcConfigurer {

//     @Override
//     public void configureViewResolvers(ViewResolverRegistry registry) {
//         InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//         resolver.setPrefix("/WEB-INF/jsp/");
//         resolver.setSuffix(".jsp");
//         registry.viewResolver(resolver);
//     }
// }
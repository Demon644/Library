package ua.logos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedHeaders("*")
						//"X-Requested-With", "Content-Type", "Authorization", "Origin", "Accept", "Access-Control-Request-Method", "Access-Control-Request-Headers")
				.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
				.allowCredentials(true)
				.maxAge(3600);
	}


//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.favorPathExtension(false).
//				favorParameter(true).
//				parameterName("mediaType").
//				ignoreAcceptHeader(true).
//				//useJaf(false).
//				defaultContentType(MediaType.APPLICATION_JSON).
//				//mediaType("xml", MediaType.APPLICATION_XML).
//				mediaType("json", MediaType.APPLICATION_JSON);
//	}
//
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(jackson2HttpMessageConverter());
//	}
//
//	@Bean
//	public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		converter.setObjectMapper(this.jacksonBuilder().build());
//		return converter;
//	}
//
//	public Jackson2ObjectMapperBuilder jacksonBuilder() {
//		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//
//		Hibernate5Module hibernateModule = new Hibernate5Module();
//		hibernateModule.configure(Feature.FORCE_LAZY_LOADING, false);
//		builder.modules(hibernateModule);
//		builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//		builder.featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION);
//		return builder;
//	}
}

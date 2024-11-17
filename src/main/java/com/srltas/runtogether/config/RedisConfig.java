package com.srltas.runtogether.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.srltas.runtogether.adapter.out.session.dao.UserSessionDAO;

@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, UserSessionDAO> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, UserSessionDAO> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new GenericToStringSerializer<>(String.class));
		template.setValueSerializer(new GenericToStringSerializer<>(UserSessionDAO.class));
		return template;
	}
}

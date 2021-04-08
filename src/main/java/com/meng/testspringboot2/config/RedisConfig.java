package com.meng.testspringboot2.config;

import com.meng.testspringboot2.pojo.RedisInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	@Bean
	ReactiveRedisOperations<String, RedisInfo> redisOperations(ReactiveRedisConnectionFactory factory) {
		Jackson2JsonRedisSerializer<RedisInfo> serializer = new Jackson2JsonRedisSerializer<>(RedisInfo.class);

		RedisSerializationContext.RedisSerializationContextBuilder<String, RedisInfo> builder =
			RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

		RedisSerializationContext<String, RedisInfo> context = builder.value(serializer).build();

		return new ReactiveRedisTemplate<>(factory, context);
	}
}

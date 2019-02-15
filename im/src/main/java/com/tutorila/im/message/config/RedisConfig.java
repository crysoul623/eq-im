/**
 * 
 */
package com.tutorila.im.message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * @author PANZERS
 * Redis配置
 */
@Component
public class RedisConfig {

	@Bean
	public JedisConnectionFactory buildJedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(hostName);
		jedisConnectionFactory.setPort(port);
		return jedisConnectionFactory;
	}
}

/**
 * 
 */
package com.tutorila.im.message.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author PANZERS
 * ZK配置
 */
@Component
public class ZookeeperConfig {
	
	@Value("${zookeeper.connection}")
	private String connection;

	@Bean(initMethod = "start")
	public CuratorFramework buildCuratorFramework() {
		return CuratorFrameworkFactory.newClient(connection, new ExponentialBackoffRetry(1000, 3));
	}
}

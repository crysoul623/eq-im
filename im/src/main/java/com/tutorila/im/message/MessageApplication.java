package com.tutorila.im.message;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.tutorila.im.message.server.MessageServer;

/**
 * 
 * @author PANZERS
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MessageApplication implements CommandLineRunner {
	
	@Value("${message.server.url}")
	private String url;
	
	@Value("${message.server.port}")
	private Integer port;
	
	@Autowired
    private MessageServer messageServer;

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("server start...");
		//start
        messageServer.run(new InetSocketAddress(url, port));
	}

}


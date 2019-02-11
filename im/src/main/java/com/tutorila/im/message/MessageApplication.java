package com.tutorila.im.message;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tutorila.im.message.server.MessageServer;

@SpringBootApplication
public class MessageApplication implements CommandLineRunner {
	
	@Autowired
    private MessageServer messageServer;

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("server start...");
		//start
        messageServer.run(new InetSocketAddress("127.0.0.1", 8080));
	}

}


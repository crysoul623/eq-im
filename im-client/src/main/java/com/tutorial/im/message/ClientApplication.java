package com.tutorial.im.message;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tutorial.im.message.client.ClientServer;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {
	
	@Autowired
    private ClientServer clientServer;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("client start...");
		//run client
        clientServer.run(new InetSocketAddress("127.0.0.1", 8080));
	}

}


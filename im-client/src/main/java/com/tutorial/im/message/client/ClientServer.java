/**
 * 
 */
package com.tutorial.im.message.client;

import java.net.InetSocketAddress;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author PANZERS
 *
 */
@Slf4j
@Component
public class ClientServer {
    
    public void run(InetSocketAddress address) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
            		 //NIO
            		 .channel(NioSocketChannel.class)
            		 .remoteAddress(address)
            		 .handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel socket) throws Exception {
					        //logic service
							socket.pipeline().addLast(new ClientHandler());
						}
                    });
            //bind
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("Client server start error:", e);
        } finally {
        	group.shutdownGracefully().sync();
        }
    }

}

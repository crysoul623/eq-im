/**
 * 
 */
package com.tutorila.im.message.server;

import java.net.InetSocketAddress;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author PANZERS
 *
 */
@Slf4j
@Component
public class MessageServer {
    
    public void run(InetSocketAddress address) throws InterruptedException {
    	EventLoopGroup bossGroup = new NioEventLoopGroup();
    	EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
            		 //NIO
            		 .channel(NioServerSocketChannel.class)
            		 .localAddress(address)
            		 .childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel childChannel) throws Exception {
					        //logic service
							childChannel.pipeline().addLast(new MessageHandler());
						}
                    });
            //bind
            ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("Message server start error:", e);
        } finally {
        	bossGroup.shutdownGracefully().sync();
        	workGroup.shutdownGracefully().sync();
        }
    }

}

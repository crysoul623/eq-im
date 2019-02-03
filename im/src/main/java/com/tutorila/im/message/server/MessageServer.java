/**
 * 
 */
package com.tutorila.im.message.server;

import java.net.InetSocketAddress;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author PANZERS
 *
 */
@Slf4j
@Component
public class MessageServer {
	
	private final EventLoopGroup bossGroup = new NioEventLoopGroup();
	
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    
    private Channel channel;
    
    public ChannelFuture run(InetSocketAddress address) {
        ChannelFuture future = null;
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
            		 //NIO
            		 .channel(NioServerSocketChannel.class)
            		 .childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socket) throws Exception {
							socket.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
							socket.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
					        //logic service
							socket.pipeline().addLast(new MessageHandler());
						}
                    })
                    .option(ChannelOption.SO_BACKLOG, 512)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //bind
            future = bootstrap.bind(address).syncUninterruptibly();
            channel = future.channel();
        } catch (Exception e) {
            log.error("Message server start error:", e);
        }
        return future;
    }

    public void destroy() {
        if(channel != null) { 
        	channel.close();
        }
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}

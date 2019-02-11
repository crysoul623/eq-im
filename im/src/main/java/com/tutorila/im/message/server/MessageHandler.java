/**
 * 
 */
package com.tutorila.im.message.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author PANZERS
 *
 */
public class MessageHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	public void channelRead0(ChannelHandlerContext ctx, ByteBuf buff) throws Exception {
		System.out.println("Welcome to Netty world.");
		Channel channel = ctx.channel();
		channel.writeAndFlush(Unpooled.copiedBuffer("GoodBye.", CharsetUtil.UTF_8));
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) throws Exception {
		throwable.printStackTrace();
		ctx.close();
	}

}

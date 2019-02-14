/**
 * 
 */
package com.tutorial.im.message.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author PANZERS
 *
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	
	@Override
	public void channelRead0(ChannelHandlerContext ctx, ByteBuf buff) throws Exception {
		System.out.println("Client receive : " + buff.toString(CharsetUtil.UTF_8));
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) throws Exception {
		throwable.printStackTrace();
		ctx.close();
	}

}

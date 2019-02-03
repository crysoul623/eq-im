/**
 * 
 */
package com.tutorila.im.message.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author PANZERS
 *
 */
public class MessageHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(msg);
		//回写
		ctx.channel().writeAndFlush("receipt..." + msg);
        ctx.close();
	}

}

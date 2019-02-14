/**
 * 
 */
package com.tutorila.im.message.server;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author PANZERS
 *
 */
public class MessageHandler extends SimpleChannelInboundHandler<ByteBuf> {
	
	private static DefaultChannelGroup defaultChannelGroup = new DefaultChannelGroup("channelGroup", GlobalEventExecutor.INSTANCE);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		defaultChannelGroup.add(ctx.channel());
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, ByteBuf buff) throws Exception {
		//ctx.channel().writeAndFlush(Unpooled.copiedBuffer(String.valueOf(buff.writerIndex()), CharsetUtil.UTF_8));
		defaultChannelGroup.writeAndFlush(Unpooled.copiedBuffer(String.valueOf(buff.writerIndex()), CharsetUtil.UTF_8), channel -> !channel.id().equals(ctx.channel().id()));
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

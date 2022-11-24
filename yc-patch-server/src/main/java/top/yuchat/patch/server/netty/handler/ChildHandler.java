package top.yuchat.patch.server.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yuchat.patch.common.netty.codedc.YcDecode;
import top.yuchat.patch.common.netty.codedc.YcEncode;

@Component
public class ChildHandler extends ChannelInitializer<SocketChannel> {

	@Autowired
	private MessageHandler messageHandler;

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		ChannelPipeline pipeline = socketChannel.pipeline();
		// pipeline.addLast(new IdleStateHandler(5, 5, 10));
		pipeline.addLast(new YcDecode());
		pipeline.addLast(new YcEncode());
		pipeline.addLast(messageHandler);
	}
}

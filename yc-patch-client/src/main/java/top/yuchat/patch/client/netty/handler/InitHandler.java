package top.yuchat.patch.client.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import top.yuchat.patch.common.netty.codedc.YcDecode;
import top.yuchat.patch.common.netty.codedc.YcEncode;

public class InitHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new YcDecode());
        pipeline.addLast(new YcEncode());
        pipeline.addLast(new ClientHandler());
    }
}

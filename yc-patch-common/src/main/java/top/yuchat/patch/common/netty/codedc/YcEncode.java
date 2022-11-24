package top.yuchat.patch.common.netty.codedc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import top.yuchat.patch.common.content.NettyContent;
import top.yuchat.patch.common.netty.result.MemcachedRequest;

@Slf4j
public class YcEncode extends MessageToByteEncoder<MemcachedRequest> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MemcachedRequest memcachedRequest, ByteBuf byteBuf) throws Exception {


        int cmd = memcachedRequest.getCmd();
        byte[] data = memcachedRequest.getData();

        // 写入包头
        byteBuf.writeLong(NettyContent.FLAG);

        // 写入命令号
        byteBuf.writeInt(cmd);

        // 写入数据长度
        int len = data.length;
        byteBuf.writeInt(len);

        // 写入数据
        byteBuf.writeBytes(data);

        // 写入校验位
        byteBuf.writeLong(len + cmd);

    }
}

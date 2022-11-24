package top.yuchat.patch.common.netty.codedc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import top.yuchat.patch.common.content.NettyContent;
import top.yuchat.patch.common.netty.result.MemcachedRequest;

import java.util.List;

@Slf4j
public class YcDecode extends ByteToMessageDecoder {

    // 包头
    public static final int BASE_LENGTH = 8 + 4 + 4;

    /**
     * 数据结构
     * <p>
     * 包头 + 命令 + 数据长度 + 数据 + 检验位
     * 8 + 4 + 4
     * 检验位： 4个字节
     *
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {


        if (byteBuf.readableBytes() <= BASE_LENGTH) {
            return;
        }
        //防止socket字节流攻击
        if (byteBuf.readableBytes() > 2048) {
            byteBuf.skipBytes(byteBuf.readableBytes());
        }

        // 记录包头开始的位置
        int beginReader;
        while (true) {
            beginReader = byteBuf.readerIndex();
            byteBuf.markReaderIndex();

            if (NettyContent.FLAG == byteBuf.readLong()) {
                break;
            }
            byteBuf.resetReaderIndex();
            byteBuf.readByte();
            if (byteBuf.readableBytes() < BASE_LENGTH) {
                return;
            }
        }

        // 命令号
        int cmd = byteBuf.readInt();

        // 数据长度
        int len = byteBuf.readInt();

        // 判断数据是否到齐
        if (byteBuf.readableBytes() < len + NettyContent.CHECK_DIGIT) {
            byteBuf.readerIndex(beginReader);
            return;
        }

        byte[] data = new byte[len];
        byteBuf.readBytes(data);

        // 错误的数据直接丢掉
        long check = byteBuf.readLong();
        if (check != cmd + len) {
            log.warn("命令号：{}，数据长度：{}，校验位：{}。数据校验发送错误，该数据将被丢失！", cmd, len, check);
            return;
        }
        list.add(new MemcachedRequest(cmd, data));
    }
}

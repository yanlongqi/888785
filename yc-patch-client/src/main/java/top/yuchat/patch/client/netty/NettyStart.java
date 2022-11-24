package top.yuchat.patch.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import top.yuchat.patch.client.netty.handler.InitHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class NettyStart {

    private static final String IP = "127.0.0.1";

    private static final int PORT = 3333;

    /**
     * 引用计数
     */
    private static final AtomicInteger index = new AtomicInteger();

    /**
     * 会话池
     */
    private static final List<Channel> channels = new ArrayList<>();


    //服务类
    private static final Bootstrap bootstrap = new Bootstrap();

    public static void init(int count) {
        log.info("开始连接到服务器，ip：{}，port：{}", IP, PORT);
        //线程池
        EventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(worker);

        //socket工厂
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new InitHandler());

        log.info("开始连接会话，会话数量：{}", count);
        for (int i = 0; i < count; i++) {
            ChannelFuture future = bootstrap.connect(IP, PORT);
            channels.add(future.channel());
        }
        log.info("会话初始化完成，服务端启动完成！");
    }

    public static Channel getActiveChannel() {
        Channel channel = channels.get(Math.abs(index.getAndIncrement() % channels.size()));
        if (channel.isActive()) {
            return channel;
        }
        reconnect(channel);
        return getActiveChannel();
    }


    public static void reconnect(Channel channel) {
        synchronized (channel) {
            if (channels.indexOf(channel) == -1) {
                return;
            }
            Channel newChannel = bootstrap.connect(IP, PORT).channel();
            channels.set(channels.indexOf(channel), newChannel);
        }
    }

}

package top.yuchat.patch.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import top.yuchat.patch.server.netty.handler.ChildHandler;

@Slf4j
@Component
public class NettyRunner implements ApplicationRunner, ApplicationListener<ContextClosedEvent> {


	// @Value("${netty.port}")
	private int port = 3333;


	@Autowired
	private ChildHandler childHandler;


	private Channel serverChannel;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		log.info("正在启动websocket服务，port={}", port);
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();

			bootstrap.group(bossGroup, workerGroup);
			//设置socket工厂
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(childHandler);
			//设置参数，TCP参数
			//serverSocketchannel的设置，链接缓冲池的大小
			bootstrap.option(ChannelOption.SO_BACKLOG, 2048);

			//socketchannel的设置,维持链接的活跃，清除死链接
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

			//socketchannel的设置,关闭延迟发送
			bootstrap.childOption(ChannelOption.TCP_NODELAY, true);

			this.serverChannel = bootstrap.bind(port).sync().channel();
			log.info("Netty服务启动成功！监听端口:{}", port);
			this.serverChannel.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		if (this.serverChannel != null) {
			this.serverChannel.close();
		}
		log.info("Netty 服务停止");
	}
}

package top.yuchat.patch.client.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import top.yuchat.patch.client.netty.actuator.AuthActuator;
import top.yuchat.patch.common.netty.actuator.ActuatorManage;
import top.yuchat.patch.common.netty.actuator.IActuator;
import top.yuchat.patch.common.netty.result.Auth;
import top.yuchat.patch.common.netty.result.MemcachedRequest;

@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<MemcachedRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MemcachedRequest message) throws Exception {
        ActuatorManage actuatorManage = ActuatorManage.getActuatorManage();
        IActuator actuator = actuatorManage.getActuator(message.getCmd());
        actuator.setParams(message.getData());
        actuator.run();
    }

    /**
     * 新客户端接入
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        AuthActuator authActuator = new AuthActuator();
        Auth auth = new Auth();
        auth.setClientId("sjdiasjdiasjdiasudhuu");
        authActuator.setAuth(auth);
        authActuator.exec(ctx.channel());
        log.info("会话连接成功,ip:{}", ctx.channel().localAddress());
    }

    /**
     * 客户端断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("会话断开连接,ip:{}", ctx.channel().localAddress());
    }
}

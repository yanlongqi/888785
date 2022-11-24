package top.yuchat.patch.server.netty.handler;

import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.yuchat.patch.common.netty.actuator.ActuatorManage;
import top.yuchat.patch.common.netty.actuator.IActuator;
import top.yuchat.patch.common.netty.result.MemcachedRequest;
import top.yuchat.patch.server.netty.ChannelManage;
import top.yuchat.patch.server.netty.UserChannel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ChannelHandler.Sharable
public class MessageHandler extends SimpleChannelInboundHandler<MemcachedRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MemcachedRequest message) throws Exception {
        ActuatorManage actuatorManage = ActuatorManage.getActuatorManage();
        IActuator actuator = actuatorManage.getActuator(message.getCmd());
        actuator.setParams(message.getData());
        actuator.run();
    }

    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.ALL_IDLE) {
                log.warn("会话连接超时，断开该链接");
                //清除超时会话
                ChannelFuture writeAndFlush = ctx.writeAndFlush("you will close");
                writeAndFlush.addListener(new ChannelFutureListener() {

                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        ctx.channel().close();
                    }
                });
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }


    /**
     * 新客户端接入
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("新的客户端进入连接,ip:{}", ctx.channel().localAddress());
        ChannelManage.getChannelManage().put("lqyan", ctx.channel());
    }

    /**
     * 客户端断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端断开连接,ip:{}", ctx.channel().localAddress());
        ChannelManage.getChannelManage().remove("lqyan", ctx.channel());
    }

    /**
     * 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.info("客户链接发生异常,ip:{}, 异常信息：{}", ctx.channel().localAddress(), cause);
    }

}

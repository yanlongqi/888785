package top.yuchat.patch.client.netty.actuator;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import top.yuchat.patch.common.netty.actuator.AuthBaseActuator;
import top.yuchat.patch.common.netty.result.Auth;
import top.yuchat.patch.common.netty.result.MemcachedRequest;


@Slf4j
public class AuthActuator extends AuthBaseActuator {

    private Auth auth;

    @Override
    public void setParams(byte[] params) {
        auth = JSON.parseObject(params, Auth.class);
    }

    @Override
    public void run() {
        log.info("客户端认证，认证信息：{}", auth);
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public Auth getAuth() {
        return auth;
    }

    @Override
    public void exec(Channel channel) {
        MemcachedRequest request = new MemcachedRequest();
        request.setCmd(cmd());
        request.setData(JSON.toJSONBytes(auth));
        channel.writeAndFlush(request);
        log.info("发送认证信息");
    }
}

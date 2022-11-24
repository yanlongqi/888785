package top.yuchat.patch.server.netty.actuator;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import top.yuchat.patch.common.netty.actuator.Actuator;
import top.yuchat.patch.common.netty.actuator.AuthBaseActuator;
import top.yuchat.patch.common.netty.result.Auth;

@Slf4j
@Actuator
public class AuthActuator extends AuthBaseActuator {

    private Auth auth;

    @Override
    public void setParams(byte[] params) {
        auth = JSON.parseObject(params, Auth.class);
    }

    @Override
    public void run() {
        log.info("处理认证信息：{}", auth);
    }
}

package top.yuchat.patch.client;

import top.yuchat.patch.client.netty.NettyStart;
import top.yuchat.patch.client.netty.actuator.InitActuator;
import top.yuchat.patch.common.netty.actuator.ActuatorManage;

public class YcPatchClientStart {

    public static void main(String[] args) {

        // 初始化服务端会话
        NettyStart.init(5);

        // 初始化执行器
        InitActuator.init();
    }


}

package top.yuchat.patch.common.netty.actuator;

import io.netty.channel.Channel;

public interface IActuator {

    /**
     * 设置执行参数
     *
     * @param params
     */
    default void setParams(byte[] params) {
    }

    /**
     * 运行
     */
    void run();

    /**
     * 命令号
     *
     * @return
     */
    int cmd();

    default void exec(Channel channel){}


}

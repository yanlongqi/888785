package top.yuchat.patch.client.netty.actuator;

import top.yuchat.patch.common.netty.actuator.ActuatorManage;

public class InitActuator {

    public static void init() {
        ActuatorManage actuatorManage = ActuatorManage.getActuatorManage();
        actuatorManage.setPackagePath(InitActuator.class.getPackage().getName());
        actuatorManage.init();
    }
}

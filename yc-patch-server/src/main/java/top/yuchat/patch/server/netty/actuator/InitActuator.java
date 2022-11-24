package top.yuchat.patch.server.netty.actuator;

import org.springframework.stereotype.Component;
import top.yuchat.patch.common.netty.actuator.ActuatorManage;

import javax.annotation.PostConstruct;

@Component
public class InitActuator {

    /**
     * 初始化执行器
     */
    @PostConstruct
    public void init() {
        ActuatorManage actuatorManage = ActuatorManage.getActuatorManage();
        actuatorManage.setPackagePath(this.getClass().getPackage().getName());
        actuatorManage.init();
    }
}

package top.yuchat.patch.common.netty.actuator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.yuchat.patch.common.utils.ScanPackageUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Slf4j
public class ActuatorManage {

    private static final  Map<Integer, IActuator> ACTUATOR_MAP = new ConcurrentHashMap<>();

    private String packagePath;

    private static final ActuatorManage actuatorManage = new ActuatorManage();

    private ActuatorManage() {
    }


    public static ActuatorManage getActuatorManage() {
        return actuatorManage;
    }

    public void put(int cmd, IActuator actuator) {
        if (checkActuator(cmd)) {
            log.warn("执行器已经存在，将覆盖新的执行器。命令号：{}", cmd);
        }
        ACTUATOR_MAP.put(cmd, actuator);
    }

    /**
     * 初始化执行器
     */
    public void init() {
        try {
            log.info("开始扫包，包名：{}", packagePath);
            long startTime = System.currentTimeMillis();
            Class<?>[] scan = ScanPackageUtils.scan(packagePath);
            for (Class<?> clazz : scan) {
                if (clazz.getAnnotation(Actuator.class) == null) {
                    continue;
                }
                Object o = clazz.newInstance();
                if (o instanceof IActuator) {
                    IActuator actuator = (IActuator) o;
                    if (checkActuator(actuator.cmd())) {
                        log.warn("执行器已经存在，将覆盖新的执行器。命令号：{}", actuator.cmd());
                    }
                    ACTUATOR_MAP.put(actuator.cmd(), actuator);
                }
            }
            long endTime = System.currentTimeMillis();
            log.info("所有的执行器已经加载，总共耗时：{}ms，执行器数量：{}", endTime - startTime, ACTUATOR_MAP.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得一个执行器
     *
     * @param cmd
     * @return
     */
    public IActuator getActuator(Integer cmd) {
        IActuator actuator = ACTUATOR_MAP.get(cmd);
        if (actuator == null) {
            log.error("没有查找到对应的执行器。命令号：{}", cmd);
        }
        return actuator;
    }

    /**
     * 检查执行器是否存在
     *
     * @param cmd
     * @return
     */
    public boolean checkActuator(Integer cmd) {
        return ACTUATOR_MAP.containsKey(cmd);
    }


}

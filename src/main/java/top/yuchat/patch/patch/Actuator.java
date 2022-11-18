package top.yuchat.patch.patch;

import top.yuchat.patch.constant.CmdEnum;
import top.yuchat.patch.constant.ErrorInfoEnum;
import top.yuchat.patch.exception.ErrorException;
import top.yuchat.patch.patch.exec.IPatchExec;

import java.util.HashMap;
import java.util.Map;

public class Actuator {

    private static final Actuator actuator = new Actuator();

    private static final Map<CmdEnum, IPatchExec> patchExecMap = new HashMap<>();

    private Actuator() {
    }

    /**
     * 获得执行器的实例
     *
     * @return 执行器实例
     */
    public static Actuator getInstent() {
        return actuator;
    }

    /**
     * 添加执行器
     *
     * @param cmd  命令
     * @param exec 执行器
     */
    public void putExec(CmdEnum cmd, IPatchExec exec) {
        patchExecMap.put(cmd, exec);
    }


    /**
     * 执行一个执行器
     *
     * @param cmd
     */
    public void run(CmdEnum cmd, String ...args) {
        IPatchExec iPatchExec = patchExecMap.get(cmd);
        if (iPatchExec == null) {
            throw new ErrorException(ErrorInfoEnum.EXEC_NOT_NULL);
        }
        iPatchExec.run(args);
    }


}

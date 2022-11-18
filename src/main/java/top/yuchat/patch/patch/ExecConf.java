package top.yuchat.patch.patch;

import top.yuchat.patch.constant.CmdEnum;
import top.yuchat.patch.patch.exec.BeginExec;
import top.yuchat.patch.patch.exec.HelpExec;
import top.yuchat.patch.patch.exec.PatchExec;

public class ExecConf {

    /**
     * 初始化执行器
     */
    public static void initExec() {

        Actuator instent = Actuator.getInstent();
        instent.putExec(CmdEnum.BEGIN, new BeginExec());
        instent.putExec(CmdEnum.PATCH, new PatchExec());
        instent.putExec(CmdEnum.HELP, new HelpExec());

    }
}

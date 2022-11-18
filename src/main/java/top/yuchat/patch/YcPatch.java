package top.yuchat.patch;

import top.yuchat.patch.patch.Actuator;
import top.yuchat.patch.patch.ExecConf;
import top.yuchat.patch.utils.ArgsUtils;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/6/20
 * @description:
 */
public class YcPatch {

    public static void main(String[] args) {

        try {
            ExecConf.initExec();
            ArgsUtils utils = ArgsUtils.getInstance(args);
            Actuator.getInstent().run(utils.getCmd(), args);
        } catch (Exception e) {
            System.out.println("\n命令执行错误，获取帮助请使用 --help");
            e.printStackTrace();
        }
    }


}

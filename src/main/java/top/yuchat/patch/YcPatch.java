package top.yuchat.patch;

import top.yuchat.patch.patch.HelpInfo;
import top.yuchat.patch.patch.PatchInfo;
import top.yuchat.patch.utils.ArgsUtils;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/6/20
 * @description:
 */
public class YcPatch {

    public static void main(String[] args) {

        try {
            ArgsUtils utils = new ArgsUtils(args);

            String cmd = utils.getCmd();
            switch (cmd){
                case "begin":
                    PatchInfo.patchBegin(utils.getDirPath(), utils.getTargetPath());
                    break;
                case "patch":
                    PatchInfo.patch(utils.getDirPath(), utils.getTargetPath());
                    break;
                case "--help":
                    HelpInfo.printHelpInfo();
                    break;
                default:
                    System.out.println("命令执行错误，获取帮助请使用 --help");
                    HelpInfo.printHelpInfo();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n命令执行错误，获取帮助请使用 --help");
            e.printStackTrace();
        }
    }


}

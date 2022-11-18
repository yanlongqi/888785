package top.yuchat.patch.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.yuchat.patch.exception.ErrorException;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/11/18
 * @description: 命令
 */
@Getter
@AllArgsConstructor
public enum CmdEnum {

    BEGIN("begin", "开始记录文件信息"),
    PATCH("patch", "开始制作补丁"),
    HELP("--help", "查看帮助信息");

    /**
     * 命令
     */
    private String cmd;

    /**
     * 命令信息
     */
    private String message;


    /**
     * 获取一个命令
     *
     * @param cmd 命令
     * @return
     */
    public static CmdEnum getCmd(String cmd) {
        for (CmdEnum value : values()) {
            if (value.cmd.equals(cmd)) {
                return value;
            }
        }
        throw new ErrorException(ErrorInfoEnum.CMD_ERROR);
    }
}

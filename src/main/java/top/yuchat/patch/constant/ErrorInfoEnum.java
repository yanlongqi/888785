package top.yuchat.patch.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.yuchat.patch.exception.CommonException;

@Getter
@AllArgsConstructor
public enum ErrorInfoEnum implements CommonException {
    UNKNOWN_ERROR("UNKNOWN_ERROR", "未知错误"),
    COMMON_ERROR("COMMON_ERROR", "公共错误"),

    CONF_FILE_LOAD_ERROR("CONF_FILE_LOAD_ERROR", "配置文件加载失败，请检查文件路径"),
    CMD_ERROR("CMD_ERROR", "命令输入错误"),
    EXEC_NOT_NULL("EXEC_NOT_NULL", "执行器不存在"),
    ;

    /**
     * 错误信息编号
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;
}

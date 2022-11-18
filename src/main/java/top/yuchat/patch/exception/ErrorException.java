package top.yuchat.patch.exception;


import lombok.Getter;
import top.yuchat.patch.constant.ErrorInfoEnum;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/11/18
 * @description: 配置文件加载失败
 */

@Getter
public class ErrorException extends RuntimeException {

    private final String code;

    public ErrorException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorException(CommonException e) {
        super(e.getMessage());
        this.code = e.getCode();
    }

    public ErrorException(String message) {
        super(message);
        this.code = ErrorInfoEnum.COMMON_ERROR.getCode();
    }

    public ErrorException() {
        super(ErrorInfoEnum.UNKNOWN_ERROR.getMessage());
        this.code = ErrorInfoEnum.UNKNOWN_ERROR.getCode();
    }
}

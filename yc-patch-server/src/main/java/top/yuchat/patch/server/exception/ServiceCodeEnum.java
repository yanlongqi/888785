package top.yuchat.patch.server.exception;


public enum ServiceCodeEnum implements CommonException {

    OK(200, "请求执行成功"),
    AUTH_FAILD(403, "您没有权限访问，请联系管理员"),
    ERROR(500, "请求执行失败"),


    ILLEGAL_CHAR(100000, "包含非法字符"),

    ;


    private int code;
    private String message;


    ServiceCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

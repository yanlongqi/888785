package top.yuchat.patch.server.constant;

public class RedisConstant {



    // 储存验证码
    public static final String CODE = "code:";

    // 用户token
    public static final String LOGIN = "login:";

    // 密码盐
    public static final String SALT = "salt:";

    public static final String ROOM_TABLE = "room:table:";
    public static final String ROOM_KEY = "room:key:";

    // 验证码发送次数，超过10条则不能再发送
    public static final int CODE_MAX_SEND_COUNT = 10;

    // 验证码有效时间，默认5分钟
    public static final long CODE_VAILD_TIME = 300000L;

    // 验证码在redis存在的时间,默认2个小时
    public static final long CODE_EXIST_TIME = 2L;

    // 密码盐有效时间，1分钟
    public static final long SALT_EXIST_TIME =  1L;

    // token的有效时间7天
    public static final long TOKEN_TIME = 7L;

}

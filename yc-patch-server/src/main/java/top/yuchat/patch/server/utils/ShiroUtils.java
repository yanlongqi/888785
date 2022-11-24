package top.yuchat.patch.server.utils;

import org.apache.shiro.SecurityUtils;
import top.yuchat.patch.server.api.user.vo.UserVo;

public class ShiroUtils {

    public static UserVo getUser() {
        return (UserVo) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUser().getId();
    }

}

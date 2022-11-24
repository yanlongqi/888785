package top.yuchat.patch.server.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.yuchat.patch.server.api.sys.service.MenuService;
import top.yuchat.patch.server.api.user.vo.UserVo;
import top.yuchat.patch.server.constant.RedisConstant;
import top.yuchat.patch.server.exception.ServiceException;
import top.yuchat.patch.server.utils.JWTUtils;
import top.yuchat.patch.server.utils.ShiroUtils;

import java.util.Set;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private RedisTemplate<String, UserVo> redisTemplate;


    @Autowired
    //@Lazy
    private MenuService menuService;

    @Override
    public boolean supports(AuthenticationToken token) {
        log.info("supports");
        return token instanceof UserToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("doGetAuthorizationInfo");
        Set<String> permissions = menuService.getPermissions(ShiroUtils.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("doGetAuthenticationInfo");
        String token = (String) authenticationToken.getPrincipal();
        Long userId = null;
        try {
            userId = JWTUtils.getUserId(token);
        } catch (Exception e) {
//            throw new IncorrectCredentialsException("token失效，请重新登录");
            throw new ServiceException();
        }
        UserVo user = redisTemplate.opsForValue().get(RedisConstant.LOGIN + userId);
        if (user == null) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
//        if (user.getStateDc() != 1){
//            throw new LockedAccountException("账号已被锁定,请联系管理员");
//        }
        return new SimpleAuthenticationInfo(user, token, getName());
    }
}

package top.yuchat.patch.server.api.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yuchat.patch.server.api.oauth.dto.LoginDTO;
import top.yuchat.patch.server.api.oauth.vo.TokenVO;
import top.yuchat.patch.server.api.user.entity.Users;
import top.yuchat.patch.server.api.user.mapper.UsersMapper;
import top.yuchat.patch.server.api.user.service.UsersService;

@Service
public class AuthService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 用户登录
     *
     * @param login
     * @return
     */
    public TokenVO login(LoginDTO login) {
        Users users = usersMapper.selectAllByUserNameUsers(login.getUserName());

        return null;
    }
}

package top.yuchat.patch.server.api.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yuchat.patch.server.api.user.entity.Users;

/**
 * @author yanlongqi
 * @description 针对表【users(用户表)】的数据库操作Mapper
 * @createDate 2022-11-24 10:41:15
 * @Entity top.yuchat.patch.server.api.auth.entity.Users
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    Users selectAllByUserNameUsers(@Param("userName") String userName);
}





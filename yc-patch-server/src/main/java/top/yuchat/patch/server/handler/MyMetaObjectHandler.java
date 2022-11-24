package top.yuchat.patch.server.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import top.yuchat.patch.server.utils.ShiroUtils;

import java.util.Date;

@Slf4j
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("updateTime", date, metaObject);
        Long userId;
        try {
            userId = ShiroUtils.getUserId();
        } catch (Exception e) {
            userId = -1L;
            log.warn("无法获取用户id");
        }
        this.setFieldValByName("createUser", userId, metaObject);
        this.setFieldValByName("updateUser", userId, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        Long userId;
        try {
            userId = ShiroUtils.getUserId();
        } catch (Exception e) {
            userId = -1L;
            log.warn("无法获取用户id");
        }
        this.setFieldValByName("updateUser", userId, metaObject);
    }

}

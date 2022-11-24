package top.yuchat.patch.server.api.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class Users {

    // 主键
    @TableId
    private String id;

    // 用户名称
    private String userName;

    // 密码
    private String password;

    // 头像
    private String avatar;

    // 状态编号
    private String statusDc;

    // 状态值
    private String statusDn;

    // 版本号
    @Version
    private String version;

    // 创建用户
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String createUser;

    // 创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String createTime;

    // 修改用户
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    // 修改时间
    @TableField(fill = FieldFill.UPDATE)
    private String updateTime;

}

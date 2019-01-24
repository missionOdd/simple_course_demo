package com.mission.course.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
@Entity
@Data
@Table(name="tb_user")
@TableName("tb_user")
@ApiModel(value="User对象", description="")
public class User extends Model<User> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @Column
    @ApiModelProperty(value = "用户名")
    private String username;

    @Column
    @ApiModelProperty(value = "密码")
    private String password;

    @Column
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Column
    @ApiModelProperty(value = "角色")
    private String role;

    @Column
    @ApiModelProperty(value = "权限")
    private String permission;

    @Column
    @ApiModelProperty(value = "最后登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime lastLoginTime;

    @Column
    @ApiModelProperty(value = "创建账号时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime createTime;

    @Column
    @ApiModelProperty(value = "头像id")
    private Integer thumbId;

    @ApiModelProperty(value = "头像")
    @TableField(exist = false)
    @Transient
    private Thumb thumb;

    @Column
    @ApiModelProperty(value = "逻辑删除状态")
    @TableLogic
    private Integer userStatus;

    @Column
    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}

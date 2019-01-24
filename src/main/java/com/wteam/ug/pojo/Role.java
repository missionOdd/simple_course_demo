package com.wteam.ug.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author mission
 * @since 2018-11-08
 */
@TableName("tb_role")
@ApiModel(value="Role对象", description="")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    private LocalDateTime createTime;

    private String roleName;

    private Integer roleType;

    private Integer userKeyId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getUserKeyId() {
        return userKeyId;
    }

    public void setUserKeyId(Integer userKeyId) {
        this.userKeyId = userKeyId;
    }

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

    @Override
    public String toString() {
        return "Role{" +
        "roleId=" + roleId +
        ", createTime=" + createTime +
        ", roleName=" + roleName +
        ", roleType=" + roleType +
        ", userKeyId=" + userKeyId +
        "}";
    }
}

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
@TableName("tb_permission")
@ApiModel(value="Permission对象", description="")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "permisson_id", type = IdType.AUTO)
    private Integer permissonId;

    private LocalDateTime createTime;

    private String permissionName;


    public Integer getPermissonId() {
        return permissonId;
    }

    public void setPermissonId(Integer permissonId) {
        this.permissonId = permissonId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    protected Serializable pkVal() {
        return this.permissonId;
    }

    @Override
    public String toString() {
        return "Permission{" +
        "permissonId=" + permissonId +
        ", createTime=" + createTime +
        ", permissionName=" + permissionName +
        "}";
    }
}

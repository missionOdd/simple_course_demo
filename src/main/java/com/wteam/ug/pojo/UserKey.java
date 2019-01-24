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
@TableName("tb_user_key")
@ApiModel(value="UserKey对象", description="")
public class UserKey extends Model<UserKey> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_key_id", type = IdType.AUTO)
    private Long userKeyId;

    private LocalDateTime createTime;

    private String password;

    private String userKeyName;


    public Long getUserKeyId() {
        return userKeyId;
    }

    public void setUserKeyId(Long userKeyId) {
        this.userKeyId = userKeyId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserKeyName() {
        return userKeyName;
    }

    public void setUserKeyName(String userKeyName) {
        this.userKeyName = userKeyName;
    }

    @Override
    protected Serializable pkVal() {
        return this.userKeyId;
    }

    @Override
    public String toString() {
        return "UserKey{" +
        "userKeyId=" + userKeyId +
        ", createTime=" + createTime +
        ", password=" + password +
        ", userKeyName=" + userKeyName +
        "}";
    }
}

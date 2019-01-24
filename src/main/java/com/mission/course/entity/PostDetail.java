package com.mission.course.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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
@Table(name="tb_post_detail")
@TableName("tb_post_detail")
@ApiModel(value="PostDetail对象", description="")
public class PostDetail extends Model<PostDetail> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "文章id")
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    @Column
    @ApiModelProperty(value = "作者id")
    private Integer userId;

    @ApiModelProperty(value = "作者")
    @TableField(exist = false)
    @Transient
    private User user;

    @Column
    @ApiModelProperty(value = "发布情况")
    @TableLogic
    private Integer postStatus;

    @Column
    @ApiModelProperty(value = "文章内容")
    private String postContent;

    @Column
    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.postId;
    }

}

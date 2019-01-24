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
@Table(name="tb_post")
@TableName("tb_post")
@ApiModel(value="Post对象", description="")
public class Post extends Model<Post> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "文章id")
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    @Column
    @ApiModelProperty(value = "文章标题")
    private String postTitle;

    @Column
    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @Column
    @ApiModelProperty(value = "文章种类id")
    private Integer postCategoryId;

    @ApiModelProperty(value = "文章种类")
    @TableField(exist = false)
    @Transient
    private PostCategory postCategory;

    @Column
    @ApiModelProperty(value = "发布情况")
    @TableLogic
    private Integer postStatus;

    @Column
    @ApiModelProperty(value = "文章修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime lastEditTime;

    @Column
    @ApiModelProperty(value = "文章创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime createTime;

    @Column
    @ApiModelProperty(value = "文章摘要")
    private String postSummary;

    @Column
    @ApiModelProperty(value = "缩略图id")
    private Integer thumbId;

    @ApiModelProperty(value = "缩略图")
    @TableField(exist = false)
    @Transient
    private Thumb thumb;

    @Column
    @ApiModelProperty(value = "作者id")
    private Integer userId;

    @ApiModelProperty(value = "作者")
    @TableField(exist = false)
    @Transient
    private User user;


    @Column
    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return this.postId;
    }

}

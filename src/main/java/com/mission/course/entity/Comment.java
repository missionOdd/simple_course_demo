package com.mission.course.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@Table(name="tb_comment")
@TableName("tb_comment")
@ApiModel(value="Comment对象", description="")
public class Comment extends Model<Comment> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "评论id")
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    @Transient
    private User user;

    @ApiModelProperty(value = "文章")
    private Integer postId;

    @ApiModelProperty(value = "公告")
    private Integer noticeId;


    @ApiModelProperty(value = "视频")
    private Integer videoId;



    @ApiModelProperty(value = "评论内容")
    private String messages;

    @ApiModelProperty(value = "楼层")
    private Integer level;

    @ApiModelProperty(value = "父子级")
    private Integer parentId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "逻辑删除状态")
    @TableLogic
    private Integer commentStatus;


    @ApiModelProperty(value = "子评论")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Transient
    private List<Comment> children;





    @Override
    protected Serializable pkVal() {
        return this.commentId;
    }


}

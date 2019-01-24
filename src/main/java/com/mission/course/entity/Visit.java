package com.mission.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@Table(name="tb_visit")
@TableName("tb_visit")
@ApiModel(value="Visit对象", description="")
public class Visit extends Model<Visit> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "访问id")
    @TableId(value = "visit_id", type = IdType.AUTO)
    private Integer visitId;

    @Column
    @ApiModelProperty(value = "文章id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer postId;

    @Column
    @ApiModelProperty(value = "公告id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer noticeId;

    @Column
    @ApiModelProperty(value = "视频id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer videoId;


    @Column
    @ApiModelProperty(value = "ip地址")
    private String visitIp;

    @Column
    @ApiModelProperty(value = "点赞")
    private Integer visitLike;

    @Column
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "文章")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    @Transient
    private PostDetail postDetail;

    @ApiModelProperty(value = "公告")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    @Transient
    private Notice notice;

    @Override
    protected Serializable pkVal() {
        return this.visitId;
    }

}

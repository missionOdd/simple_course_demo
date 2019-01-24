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
@Table(name="tb_notice")
@TableName("tb_notice")
@ApiModel(value="Notice对象", description="")
public class Notice extends Model<Notice> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "公告id")
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    @Column
    @ApiModelProperty(value = "公告标题")
    private String noticeTitle;

    @Column
    @ApiModelProperty(value = "作者id")
    private Integer userId;

    @ApiModelProperty(value = "作者")
    @TableField(exist = false)
    @Transient
    private User user;

    @Column
    @ApiModelProperty(value = "公告内容")
    private String noticeContent;

    @Column
    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @Column
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime lastEditTime;

    @Column
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime createTime;

    @Column
    @ApiModelProperty(value = "发布情况")
    @TableLogic
    private Integer noticeStatus;

    @Column
    @ApiModelProperty(value = "缩略图id")
    private Integer thumbId;

    @ApiModelProperty(value = "缩略图")
    @TableField(exist = false)
    @Transient
    private Thumb thumb;

    @Column
    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return this.noticeId;
    }


}

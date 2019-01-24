package com.mission.course.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2018-10-11
 */

@Entity
@Data
@Table(name="tb_video")
@TableName("tb_video")
@ApiModel(value="Video对象", description="")
public class Video extends Model<Video> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @TableId(value = "video_id", type = IdType.AUTO)
    private Integer videoId;

    @Column
    @ApiModelProperty(value = "视频名称")
    private String videoName;

    @Column
    @ApiModelProperty(value = "视频地址")
    private String videoSrc;

    @Column
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @Column
    @ApiModelProperty(value = "视频明细")
    private String videoDesc;

    @Column
    @ApiModelProperty(value = "视频缩略图")
    private String thumbSrc;

    @Column
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime createTime;


    @Column
    @ApiModelProperty(value = "发布情况")
    @TableLogic
    private Integer videoStatus;

    @Column
    @ApiModelProperty(value = "用户")
    @TableField(exist = false)
    @Transient
    private User user;




    @Override
    protected Serializable pkVal() {
        return this.videoId;
    }


}

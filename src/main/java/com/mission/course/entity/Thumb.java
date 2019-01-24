package com.mission.course.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@Table(name="tb_thumb")
@TableName("tb_thumb")
@ApiModel(value="Thumb对象", description="")
public class Thumb extends Model<Thumb> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "缩略图id")
    @TableId(value = "thumb_id", type = IdType.AUTO)
    private Integer thumbId;

    @Column
    @ApiModelProperty(value = "缩略图名称")
    private String thumbName;

    @Column
    @ApiModelProperty(value = "缩略图大小")
    private Double thumbSize;

    @Column
    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @Column
    @ApiModelProperty(value = "缩略图类别")
    private Integer thumbCategoryId;

    @ApiModelProperty(value = "缩略图类别")
    @TableField(exist = false)
    @Transient
    private ThumbCategory thumbCategory;

    @Column
    @ApiModelProperty(value = "缩略图描述")
    private String thumbDesc;

    @Column
    @ApiModelProperty(value = "父子级")
    private Integer parentId;

    @Column
    @ApiModelProperty(value = "图片路径")
    private String thumbSrc;

    @Column
    @ApiModelProperty(value = "图片是否删除")
    @TableLogic
    private Integer thumbStatus;

    @Column
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "缩略图类别")
    @TableField(exist = false)
    @Transient
    private Thumb parent;

    @Override
    protected Serializable pkVal() {
        return this.thumbId;
    }
}

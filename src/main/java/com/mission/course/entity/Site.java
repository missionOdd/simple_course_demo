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
@Table(name="tb_site")
@TableName("tb_site")
@ApiModel(value="Site对象", description="")
public class Site extends Model<Site> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "站点id")
    @TableId(value = "site_id", type = IdType.AUTO)
    private Integer siteId;

    @Column
    @ApiModelProperty(value = "网站标题")
    private String siteTitle;

    @Column
    @ApiModelProperty(value = "主要标题")
    private String headline;

    @Column
    @ApiModelProperty(value = "背景图片")
    private String headlineImg;

    @Column
    @ApiModelProperty(value = "逻辑删除状态")
    @TableLogic
    private Integer siteStatus;

    @Column
    @ApiModelProperty(value = "背景图片id")
    private Integer thumbId;

    @ApiModelProperty(value = "背景图片")
    @TableField(exist = false)
    @Transient
    private Thumb thumb;

    @Column
    @ApiModelProperty(value = "创建时间")
    @TableField("create_Time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private LocalDateTime createTime;

    @Column
    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return this.siteId;
    }

}

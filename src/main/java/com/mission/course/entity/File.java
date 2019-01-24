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
@Table(name="tb_file")
@TableName("tb_file")
@ApiModel(value="File对象", description="")
public class File extends Model<File> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "文件id")
    @TableId(value = "file_id", type = IdType.AUTO)
    private Integer fileId;

    @Column
    @ApiModelProperty(value = "文件名")
    private String fileName;

    @Column
    @ApiModelProperty(value = "文件标题")
    private String fileTitle;

    @Column
    @ApiModelProperty(value = "文件种类id")
    private Integer fileCategoryId;

    @ApiModelProperty(value = "文件种类")
    @TableField(exist = false)
    @Transient
    private FileCategory fileCategory;

    @Column
    @ApiModelProperty(value = "文件路径")
    private String fileSrc;

    @Column
    @ApiModelProperty(value = "文件类型id")
    private Integer fileTypeId;

    @ApiModelProperty(value = "文件类型")
    @TableField(exist = false)
    @Transient
    private FileType fileType;

    @Column
    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @Column
    @ApiModelProperty(value = "文件描述")
    private String fileDesc;

    @Column
    @ApiModelProperty(value = "逻辑删除状态")
    @TableLogic
    private Integer fileStatus;

    @Column
    @ApiModelProperty(value = "缩略图")
    private Integer thumbId;

    @ApiModelProperty(value = "缩略图")
    @TableField(exist = false)
    @Transient
    private Thumb thumb;

    @ApiModelProperty(value = "文件修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime lastEditTime;

    @ApiModelProperty(value = "文件创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.fileId;
    }


}

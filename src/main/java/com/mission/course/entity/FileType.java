package com.mission.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
@Table(name="tb_file_type")
@TableName("tb_file_type")
@ApiModel(value="FileType对象", description="")
public class FileType extends Model<FileType> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "文件格式id")
    @TableId(value = "file_type_id", type = IdType.AUTO)
    private Integer fileTypeId;

    @Column
    @ApiModelProperty(value = "文件格式名")
    private String fileTypeName;

    @Column
    @ApiModelProperty(value = "文件格式描述")
    private String fileTypeDesc;

    @Column
    @ApiModelProperty(value = "文件格式逻辑删除")
    @TableLogic
    private Integer fileTypeStatus;

    @Override
    protected Serializable pkVal() {
        return this.fileTypeId;
    }

}

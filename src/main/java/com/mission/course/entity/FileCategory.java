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
@Table(name="tb_file_category")
@TableName("tb_file_category")
@ApiModel(value="FileCategory对象", description="")
public class FileCategory extends Model<FileCategory> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "文件种类id")
    @TableId(value = "file_category_id", type = IdType.AUTO)
    private Integer fileCategoryId;

    @Column
    @ApiModelProperty(value = "文件种类名称")
    private String fileCategoryName;

    @Column
    @ApiModelProperty(value = "文件种类描述")
    private String fileCategoryDesc;

    @Column
    @ApiModelProperty(value = "逻辑删除状态")
    @TableLogic
    private Integer fileCategoryStatus;



    @Override
    protected Serializable pkVal() {
        return this.fileCategoryId;
    }


}

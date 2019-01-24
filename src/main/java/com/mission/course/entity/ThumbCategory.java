package com.mission.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2018-10-10
 */
@Entity
@Data
@TableName("tb_thumb_category")
@Table(name ="tb_thumb_category")
@ApiModel(value="ThumbCategory对象", description="")
public class ThumbCategory extends Model<ThumbCategory> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "相册id")
    @TableId(value = "thumb_category_id", type = IdType.AUTO)
    private Integer thumbCategoryId;

    @Column
    @ApiModelProperty(value = "相册名字")
    private String thumbCategoryName;

    @Column
    @ApiModelProperty(value = "相册描述")
    private String thumbCategoryDesc;

    @Column
    @ApiModelProperty(value = "相册状态")
    @TableLogic
    private Integer thumbCategoryStatus;

    @Column
    @ApiModelProperty(value = "相册缩略图")
    private String thumbCategorySrc;


    @Override
    protected Serializable pkVal() {
        return this.thumbCategoryId;
    }

}

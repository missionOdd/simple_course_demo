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
@Table(name="tb_post_category")
@TableName("tb_post_category")
@ApiModel(value="PostCategory对象", description="")
public class PostCategory extends Model<PostCategory> {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "文章种类id")
    @TableId(value = "post_category_id", type = IdType.AUTO)
    private Integer postCategoryId;

    @Column
    @ApiModelProperty(value = "文章种类名称")
    private String postCategoryName;

    @Column
    @ApiModelProperty(value = "文章种类注备")
    private String postCategoryDesc;

    @Column
    @ApiModelProperty(value = "逻辑删除状态")
    @TableLogic
    private Integer postCategoryStatus;

    @Override
    protected Serializable pkVal() {
        return this.postCategoryId;
    }

}

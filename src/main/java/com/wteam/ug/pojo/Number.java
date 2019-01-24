package com.wteam.ug.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author mission
 * @since 2018-11-08
 */
@TableName("tb_number")
@ApiModel(value="Number对象", description="")
public class Number extends Model<Number> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "number_id", type = IdType.AUTO)
    private Long numberId;

    private Long aliveNumber;

    private LocalDateTime date;

    private Long registerNumber;

    private Long workOrderNumber;


    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    public Long getAliveNumber() {
        return aliveNumber;
    }

    public void setAliveNumber(Long aliveNumber) {
        this.aliveNumber = aliveNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(Long registerNumber) {
        this.registerNumber = registerNumber;
    }

    public Long getWorkOrderNumber() {
        return workOrderNumber;
    }

    public void setWorkOrderNumber(Long workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }

    @Override
    protected Serializable pkVal() {
        return this.numberId;
    }

    @Override
    public String toString() {
        return "Number{" +
        "numberId=" + numberId +
        ", aliveNumber=" + aliveNumber +
        ", date=" + date +
        ", registerNumber=" + registerNumber +
        ", workOrderNumber=" + workOrderNumber +
        "}";
    }
}

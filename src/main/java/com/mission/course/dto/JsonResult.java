package com.mission.course.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mission.course.dto.enums.CodeStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 统一返回给前端的Json数据
 * @author mission
 * @date 2018/10/5 0005-9:24
 */

@Getter
@Setter
public class JsonResult<T> implements Serializable {
  /**
   * 状态码
   */
  private Integer state;
  /**
   * 状态信息
   */
  private String msg;
  /**
   * 数据统计
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Integer count;

  /**
   * 数据
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private  T data;

  public JsonResult(T data) {
    this.state=CodeStateEnum.SUCCESS.getState();
    this.msg=CodeStateEnum.SUCCESS.getStateInfo();
    this.data = data;
  }

  public JsonResult(T data,int count) {
    this.state=CodeStateEnum.SUCCESS.getState();
    this.msg=CodeStateEnum.SUCCESS.getStateInfo();
    this.data = data;
    this.count=count;
  }

  public JsonResult(CodeStateEnum codeStateEnum, T data){
    this.state=codeStateEnum.getState();
    this.msg=codeStateEnum.getStateInfo();
    this.data = data;
  }

  public JsonResult(CodeStateEnum codeStateEnum, T data,int count){
    this.state=codeStateEnum.getState();
    this.msg=codeStateEnum.getStateInfo();
    this.data = data;
    this.count=count;
  }

  public JsonResult(CodeStateEnum codeStateEnum){
    this.state=codeStateEnum.getState();
    this.msg=codeStateEnum.getStateInfo();
  }

  public JsonResult(){
  }

  public JsonResult(Integer state, String msg) {
    this.state=state;
    this.msg=msg;
  }

  public JsonResult erro(){
    this.state=CodeStateEnum.ERRO.getState();
    this.msg=CodeStateEnum.ERRO.getStateInfo();
    return this;
  }
  public JsonResult erro(String msg){
    this.state=CodeStateEnum.ERRO.getState();
    this.msg=msg;
    return this;
  }

  public JsonResult ok(){
    this.state=CodeStateEnum.SUCCESS.getState();
    this.msg=CodeStateEnum.SUCCESS.getStateInfo();
    return this;
  }

  public JsonResult ok(T data){
    this.state=CodeStateEnum.SUCCESS.getState();
    this.msg=CodeStateEnum.SUCCESS.getStateInfo();
    this.data = data;
    return this;
  }

  public JsonResult ok(T data,int count){
    this.state=CodeStateEnum.SUCCESS.getState();
    this.msg=CodeStateEnum.SUCCESS.getStateInfo();
    this.data = data;
    this.count=count;
    return this;
  }

}

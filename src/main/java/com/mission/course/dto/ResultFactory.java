package com.mission.course.dto;

import com.mission.course.dto.enums.CodeStateEnum;

/**
 * @author mission
 * @date 2018/10/6 0006-14:31
 */
public class ResultFactory<T> {

  public static JsonResult ok(){
    return new JsonResult().ok();
  }

  public static JsonResult erro(){
    return new JsonResult().erro();
  }

  public static JsonResult erro(String msg){
    return new JsonResult().erro(msg);
  }

  public static JsonResult ok(Object data){
    return new JsonResult().ok(data);
  }

  public static JsonResult ok(Object data,int count){
    return new JsonResult().ok(data,count);
  }

  public static JsonResult erro(CodeStateEnum codeStateEnum){
    return new JsonResult(codeStateEnum);
  }

  public static JsonResult custom(Integer state,String msg){
    return new JsonResult(state,msg);
  }


  public static JsonResult empty(){
    return new JsonResult(CodeStateEnum.NULL);
  }

  public static JsonResult bad(){
    return new JsonResult(CodeStateEnum.BAD);
  }

  public static JsonResult over(){
    return new JsonResult(CodeStateEnum.OVER);
  }

  public static JsonResult unavailable(){
    return new JsonResult(CodeStateEnum.Unavailable);
  }

  public static JsonResult notGo(){
    return new JsonResult(CodeStateEnum.NotGo);
  }
}

package com.mission.course.common.exception;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.mission.course.dto.ResultFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.mission.course.common.util.HttpServletRequestUtil.isAjax;

/**
 * @author mission
 * @date 2018/10/6 0006-15:18
 */
@ControllerAdvice
@Slf4j
public class WebExceptionHandler {

  public static final String ERRO_VIEW ="ftl/error";

  @ExceptionHandler(value =Exception.class)
  @ResponseBody
  public Object errorHandler(HttpServletRequest request, HttpServletResponse response ,Exception e) throws Exception {
    log.error(e.toString());
    return getObject(request, e);
  }

  @ExceptionHandler(MyException.class)
  @ResponseBody
    public Object MyErrorHandler(HttpServletRequest request, HttpServletResponse response ,Exception e) throws Exception{
      log.error(e.toString());
    return getObject(request, e);
  }


  private Object getObject(HttpServletRequest request, Exception e) {
    if (isAjax(request)) {
      JSON json;
      if (e instanceof MyException){
        json = JSONUtil.parse(ResultFactory.custom(505,e.toString()));
      }else {
        json = JSONUtil.parse(ResultFactory.unavailable());
      }

      log.error("错误:{}", json);
      return json;
    } else {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("erro", e);
      modelAndView.addObject("url", request.getRequestURL());
      modelAndView.setViewName(ERRO_VIEW);
      return modelAndView;
    }
  }

}

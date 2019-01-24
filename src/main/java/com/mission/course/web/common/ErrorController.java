package com.mission.course.web.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mission
 * @date 2018/10/6 0006-16:22
 */
@Controller
public class ErrorController {


  @GetMapping("errortest")
  public String errtest(){
    int i=1/0;
    return "";
  }


}

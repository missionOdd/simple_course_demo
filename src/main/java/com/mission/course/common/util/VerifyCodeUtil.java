package com.mission.course.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mission
 * @date 2018/8/21 0021-1:48
 */
public class VerifyCodeUtil {

  /**
   * 验证验证码
   * @param request
   * @return
   */
  public static boolean checkVerifyCode(HttpServletRequest request){
    String verifyCodeExpected= (String) request.getSession().getAttribute("vrifyCode");
    String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
    if (verifyCodeActual==null||!verifyCodeActual.equals(verifyCodeExpected)){
      return false;
    }
    return true;
  }
}

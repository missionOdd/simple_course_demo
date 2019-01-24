package com.mission.course.common.util;

import com.mission.course.common.exception.MyException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @author mission
 * @date 2018/8/20 0020-15:43
 */
@Slf4j
public class HttpServletRequestUtil {

  /**
   * request各种转换
   * @param request
   * @param key
   * @return
   */
  public static int getInt(HttpServletRequest request,String key){
    try {
      return Integer.decode(request.getParameter(key));
    } catch (Exception e) {
      log.error("转换失败 {}",key);
      return -1;
    }
  }

  public static Long getLong(HttpServletRequest request,String key){
    try {
      return Long.valueOf(request.getParameter(key));
    } catch (Exception e) {
      log.error("转换失败 {}",key);
      return -1L;
    }
  }

  public static Double getDouble(HttpServletRequest request,String key){
    try {
      return Double.valueOf(request.getParameter(key));
    } catch (Exception e) {
      log.error("转换失败 {}",key);
      return -1d;
    }
  }

  public static boolean getBoolean(HttpServletRequest request,String key){
    try {
      return Boolean.valueOf(request.getParameter(key));
    } catch (Exception e) {
      log.error("转换失败 {}",key);
      return false;
    }
  }

  public static String getString(HttpServletRequest request,String key){

    try {
      String result = request.getParameter(key);
      if (request!=null){
        result=result.trim();
      }

      if ("".equals(result)){
        result=null;
      }
      return result;
    } catch (Exception e) {
      log.error("转换失败 {}",key);
      return null;
    }
  }

  /**
   * 判断是否为ajax请求
   * @param request
   * @return
   */
  public static boolean isAjax(HttpServletRequest request) {
    boolean f =(request.getHeader("X-Requested-With")!=null)&&
        ("XMLHttpRequest").equals(request.getHeader("X-Requested-With"));
    log.info("isAjax? -> {}",f);
    return f;
  }

  /**
   * 获取用户ip
   * @param request
   * @return
   * @throws Exception
   */
  public static String getIpAddr(HttpServletRequest request) throws Exception{
    String ipAddress = "";
    try {
      ipAddress = request.getHeader("x-forwarded-for");
      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("Proxy-Client-IP");
      }
      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("WL-Proxy-Client-IP");
      }
      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getRemoteAddr();
        if (ipAddress.equals("127.0.0.1")) {
          // 根据网卡取本机配置的IP
          InetAddress inet = null;
          try {
            inet = InetAddress.getLocalHost();
          } catch (Exception e) {
            throw new MyException("ipd获取失败");
          }
          ipAddress = inet.getHostAddress();
        }
      }
      // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
      if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
        // = 15
        if (ipAddress.indexOf(",") > 0) {
          ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }
      }
    } catch (Exception e) {
      ipAddress="";
    }
    return ipAddress;
  }

}

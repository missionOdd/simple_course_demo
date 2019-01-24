package com.mission.course.common.util;

/**
 * @author mission
 * @date 2018/8/19 0019-15:13
 */

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * 注意路径的 / 拼接问题
 */
public class PathUtil {

  @Value("${web.upload-path}")
  static String basePath;

  private static String seperator=System.getProperty("file.separator");

  /**
   * 获得基本路径
   * @return
   */
  public static String getBasePath(){
    //获得系统名字
    String os=System.getProperty("os.name");

    if (os.toLowerCase().startsWith("win")){
      basePath="D:/projectdev/course";
    }else{
      basePath="/home/mission/course";
    }
    basePath=basePath.replace("/",seperator);
    return  basePath;
  }

  /*
   *获得图片相对路径
   */
  public static String getImagepath(String sign){
    String imagePath="/upload/images/"+sign+"/";
    return imagePath.replace("/",seperator);
  }

  /*
   *获得文件路径
   */
  public static String getFilepath(String sign){
    String imagePath="/upload/files/"+sign+"/";
    return imagePath.replace("/",seperator);
  }


  /**
   * 获取输入文件流的扩展名
   * @param fileName
   * @return
   */
  public static String getFileExtension(String fileName) {
    String originalFileName=fileName;
    return originalFileName.substring(originalFileName.lastIndexOf("."));
  }


  /**
   * 如果需要
   * 创建目标路径,调用此方法
   * @param targetAddr
   */
  public static void makeDirPath(String targetAddr) {
    String realFileParentPath= getBasePath()+targetAddr;
    File dirPath=new File(realFileParentPath);
    if (!dirPath.exists()) {
      dirPath.mkdirs();
    }
  }
}

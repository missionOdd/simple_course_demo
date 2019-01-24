package com.mission.course.common.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import com.mission.course.dto.FileHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static com.mission.course.common.util.ImgUtil.getRandomFileName;
import static com.mission.course.common.util.PathUtil.makeDirPath;

/**
 * @author mission
 * @date 2018/10/7 0007-18:16
 */
public class MyFileUtil {


  /**
   *  保存文件并返回相对路径
   * @param fileHolder 文件封装类
   * @param sign 图片路径的一个目录
   * @return
   */
  public  static String saveFile(FileHolder fileHolder,String sign){

    //获得文件的随机名
    String realFileName=getRandomFileName();
    //获得文件的扩展名
    String extension=PathUtil.getFileExtension(fileHolder.getFileName());
    if(extension==""){
      throw new RuntimeException("File后缀名为空");
    }
    //创建保存路径
    String targetAddr = PathUtil.getFilepath(sign);
    makeDirPath(targetAddr);
    //获得文件保存相对路径
    String relativeAddr=targetAddr+realFileName+extension;

    String fullpath =PathUtil.getBasePath()+relativeAddr;
    try {
      FileUtil.writeFromStream(fileHolder.getFileInputStream(),fullpath);
    } catch (IORuntimeException e) {
      throw new RuntimeException("FileIO失败"+e.toString());
    }
    return relativeAddr;
  }



  /**
   * 得到 FileHolder
   * @param file
   * @return
   * @throws IOException
   */
  public static FileHolder getFileHolder(MultipartFile file) throws IOException {

    FileHolder fileHolder = null;
    if (file != null) {
      //若取出的图文件流不为空,则将其放入ImageHolder
      fileHolder = new FileHolder(file.getOriginalFilename(),file.getInputStream());
    }
    return fileHolder;
  }


  /**
   * 测试
   * @param args
   */
  public static void main(String[] args) {
    File file=new File("E:\\Source\\图包\\temp\\shuiyin.png");
    try {
      InputStream inputStream =new FileInputStream(file);
      FileHolder fileHolder =new FileHolder(file.getName(),inputStream);
      System.out.println(file.getName());
      System.out.println(saveFile(fileHolder,"www"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除文件
   * storePath是文件的路径还是目录的路径
   * 如果是storePath是文件路径则删除该文件,
   * 如果是storePath是目录路径则删除该目录下的所有文件
   * @param storePath
   */
  public static void deleteFileOrPath(String storePath){
    File fileOrPath =new File(com.mission.course.common.util.PathUtil.getBasePath()+storePath);
    if (fileOrPath.exists()){
      if (fileOrPath.isDirectory()) {
        try {
          File files[] = fileOrPath.listFiles();
          for (int i = 0; i < files.length; i++) {
            files[i].delete();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      fileOrPath.delete();
    }
  }



}


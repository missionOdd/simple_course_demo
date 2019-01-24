package com.mission.course.common.util;


import com.mission.course.dto.ImageHolder;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.mission.course.common.util.PathUtil.makeDirPath;

/**
 * @author mission
 * @date 2018/8/19 0019-14:50
 */
@Slf4j
public class ImgUtil {


  //获得根路径
  private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();

  //获得时间格式
  private static final SimpleDateFormat sDateForamt=new SimpleDateFormat("yyyyMMddHHmmss");
  //获得随机数
  private static final Random r=new Random();

  /**
   * 处理并保存缩略图
   * @param imageHolder
   * @param sign 图片存储路径标志
   * @return
   */
  public static String generateThumbnail(ImageHolder imageHolder, String sign){
    //获得文件的随机名
    String realFileName=getRandomFileName();
    //获得文件的扩展名
    String extension=PathUtil.getFileExtension(imageHolder.getImageName());
    //创建保存路径
    String targetAddr = PathUtil.getImagepath(sign);
    makeDirPath(targetAddr);
    //获得文件保存相对路径
    String relativeAddr=targetAddr+realFileName+extension;
    //File(根路径+相对路径)
    File dest=new File(com.mission.course.common.util.PathUtil.getBasePath()+relativeAddr);


    log.debug("basePath is"+basePath);
    log.debug("current complete addr is:"+ com.mission.course.common.util.PathUtil.getBasePath()+relativeAddr);


    //of:传入需要处理的图片文件
    //size:处理后图片大小
    //watermark:水印 (水印位置,水印文件,水印透明度)
    //outputQuality:压缩图片比
    //toFile:输出目录
    System.out.println(basePath+"watermark.jpg");
    try {
      Thumbnails.of(imageHolder.getImage())
          .size(200,200).watermark(Positions.BOTTOM_RIGHT,
          ImageIO.read(new File(basePath+"watermark.jpg")),0.45f).outputQuality(0.8f)
          .toFile(dest);
    } catch (IOException e) {
      throw new RuntimeException("ImageIO失败"+e.getMessage());
    }
    return relativeAddr;
  }




  /**
   * 生成随机文件名,当前年月日小时分钟秒钟+五位随机数
   * @return
   */
  public static String getRandomFileName() {
    //获得随机的五位数
    int rannum=r.nextInt(89999)+10000;
    String nowTimeStr=sDateForamt.format(new Date());
    return nowTimeStr+rannum;

  }


  /**
   * 测试Thumbnails的用法
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {

    File dest=new File(com.mission.course.common.util.PathUtil.getBasePath());
    System.out.println(basePath);
    //of:传入需要处理的图片文件
    //size:处理后图片大小
    //watermark:水印 (水印位置,水印文件,水印透明度)
    //outputQuality:压缩图片比
    //toFile:输出目录
    Thumbnails.of(new File("E:\\Source\\图包\\temp\\shuiyin.png"))
        .size(200,200).watermark(Positions.BOTTOM_RIGHT,
            ImageIO.read(new File(basePath+"watermark.jpg")),0.45f).outputQuality(0.8f)
        .toFile("E:\\Source\\图包\\temp\\shuiyin_new.png");
  }


  /**
   *创建正常图片
   * @param imageHolder
   * @param sign
   * @return
   */
  public static String generateNormalThumbnail(ImageHolder imageHolder, String sign) {
    //获得文件的随机名
    String realFileName=getRandomFileName();
    //获得文件的扩展名
    String extension=PathUtil.getFileExtension(imageHolder.getImageName());
    //创建保存路径
    String targetAddr = PathUtil.getFilepath(sign);
    makeDirPath(targetAddr);
    //获得文件保存相对路径
    String relativeAddr=targetAddr+realFileName+extension;
    //File(根路径+相对路径)
    File dest=new File(com.mission.course.common.util.PathUtil.getBasePath()+relativeAddr);
    System.out.println(dest.getAbsolutePath());


    log.debug("basePath is"+basePath);
    log.debug("current complete addr is:"+ com.mission.course.common.util.PathUtil.getBasePath()+relativeAddr);
    //of:传入需要处理的图片文件
    //size:处理后图片大小
    //watermark:水印 (水印位置,水印文件,水印透明度)
    //outputQuality:压缩图片比
    //toFile:输出目录
    System.out.println(basePath+"watermark.jpg");
    try {
      Thumbnails.of(imageHolder.getImage())
          .size(1400,1400).watermark(Positions.BOTTOM_RIGHT,
          ImageIO.read(new File(basePath+"watermark.jpg")),0.45f).outputQuality(0.8f)
          .toFile(dest);
    } catch (IOException e) {
      throw new RuntimeException("ImageIO失败"+e.getMessage());
    }
    return relativeAddr;
  }


  /**
   *
   * @param files
   * @param imgList
   * @return
   * @throws IOException
   */
  public static ImageHolder getImageHolder(MultipartFile[] files, List<ImageHolder> imgList,Integer size) throws IOException {

    ImageHolder thumbnail = null;

    if (files != null) {
      //若取出的图文件流不为空,则将其放入ImageHolder
      thumbnail = new ImageHolder(files[0].getOriginalFilename(),files[0].getInputStream());
    }
    //取出详情图列表并构建List<ImageHolder>列表对象,最多支持六张图片上传
    for (int i = 0; i < size; i++) {
      if (files != null) {
        //若取出的第i个详情图片文件流不为空,则将其加入详情图片列表
        ImageHolder img = new ImageHolder(files[i].getOriginalFilename(),files[i].getInputStream());
        imgList.add(img);
      } else {
        //若取出的第i个详情图文件流为空,则终止循环
        break;
      }
    }
    return thumbnail;
  }


  /**
   * 获得单个ImageHolder
   * @param file
   * @return
   * @throws IOException
   */
  public static ImageHolder getImageHolder(MultipartFile file) throws IOException {
    ImageHolder thumbnail =null;

    if (file != null) {
      //若取出的图文件流不为空,则将其放入ImageHolder
      thumbnail = new ImageHolder(file.getOriginalFilename(),file.getInputStream());
    }
    return thumbnail;
  }
}

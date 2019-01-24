package com.mission.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mission.course.common.exception.MyException;
import com.mission.course.common.util.ImgUtil;
import com.mission.course.common.util.MyFileUtil;
import com.mission.course.dao.ThumbMapper;
import com.mission.course.dto.ImageHolder;
import com.mission.course.entity.Thumb;
import com.mission.course.service.ThumbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
@Service
@Transactional
public class ThumbServiceImpl extends ServiceImpl<ThumbMapper, Thumb> implements ThumbService {

  @Resource
  private ThumbMapper thumbMapper;

  @Override
  public List<Thumb> getThumbList(Page page, Thumb thumbCondition) {
    return thumbMapper.getThumbList(page,thumbCondition);
  }

  @Override
  public Thumb saveThumb(MultipartFile file,Thumb thumb,String sign) {
    ImageHolder imageHolder;
    if (thumb==null){
      thumb=new Thumb();
    }
    if (file!=null){
      try {
        imageHolder = ImgUtil.getImageHolder(file);
      } catch (IOException e) {
        throw new MyException("得不到文件流");
      }
      try{
        String imgSrc = ImgUtil.generateThumbnail(imageHolder,sign);
        thumb.setThumbSrc(imgSrc);
        thumb.setCreateTime(LocalDateTime.now());
        if (thumbMapper.insert(thumb)>0) {
          return thumb;
        }

      } catch (Exception e) {
        throw new MyException("保存文件错误");
      }
    }else {
      throw new MyException("Http文件错误");
    }
    return null;
  }
  @Override
  public Thumb savePhoto(MultipartFile file,Thumb thumb,String sign) {
    ImageHolder imageHolder;
    if (thumb==null){
      thumb=new Thumb();
    }
    if (file!=null){
      try {
        imageHolder = ImgUtil.getImageHolder(file);
      } catch (IOException e) {
        throw new MyException("得不到文件流");
      }
      try{
        String imgSrc = ImgUtil.generateNormalThumbnail(imageHolder,sign);
        thumb.setThumbSrc(imgSrc);
        thumb.setCreateTime(LocalDateTime.now());
        if (thumbMapper.insert(thumb)>0) {
          return thumb;
        }

      } catch (Exception e) {
        throw new MyException("保存文件错误");
      }
    }else {
      throw new MyException("Http文件错误");
    }
    return null;
  }


  @Override
  public boolean deleteThumb(Thumb thumb) {
    List<Thumb>  thumbList=thumbMapper.selectList(new UpdateWrapper<>(thumb));
    Integer count = thumbMapper.delete(new UpdateWrapper<>(thumb));
    for (int i = 0; i <thumbList.size(); i++) {
      MyFileUtil.deleteFileOrPath(thumbList.get(i).getThumbSrc());
    }
    if (count>0){
      return true;
    }
   return false;
  }

  /**
   *
   * @param file
   * @param thumb
   * @param sign
   * @return
   */
  @Override
  public Thumb updateThumb(MultipartFile file, Thumb thumb,String sign) {
    ImageHolder imageHolder;
    if (thumb==null){
      thumb=new Thumb();
    }
    if (file!=null){
      try {
        imageHolder = ImgUtil.getImageHolder(file);
      } catch (IOException e) {
        throw new MyException("得不到文件流");
      }
      try{
        if(thumb.getThumbId()!=null) {
          Thumb thumb1 = thumbMapper.selectById(thumb.getThumbId());
          if (thumb1 != null){
            MyFileUtil.deleteFileOrPath(thumb1.getThumbSrc());
        }
        }
        if (thumb.getThumbSrc()!=null){
          MyFileUtil.deleteFileOrPath(thumb.getThumbSrc());
        }
        String imgSrc = ImgUtil.generateThumbnail(imageHolder,sign);
        thumb.setThumbSrc(imgSrc);
        thumb.setCreateTime(LocalDateTime.now());
        if (this.saveOrUpdate(thumb)) {
          return thumb;
        }

      } catch (Exception e) {
        throw new MyException("保存文件错误");
      }
    }else {
      throw new MyException("Http文件错误");
    }

    return null;
  }

  @Override
  public Thumb updatePhoto(MultipartFile file,Thumb thumb ,String sign) {
    ImageHolder imageHolder;
    if (thumb==null){
      thumb=new Thumb();
    }
    if (file!=null){
      try {
        imageHolder = ImgUtil.getImageHolder(file);
      } catch (IOException e) {
        throw new MyException("得不到文件流");
      }
      try{
        if(thumb.getThumbId()!=null){
          Thumb thumb1 =thumbMapper.selectById(thumb.getThumbId());

          MyFileUtil.deleteFileOrPath(thumb1.getThumbSrc());
        }
        if (thumb.getThumbSrc()!=null){
          MyFileUtil.deleteFileOrPath(thumb.getThumbSrc());
        }
        MyFileUtil.deleteFileOrPath(thumb.getThumbSrc());
        String imgSrc = ImgUtil.generateNormalThumbnail(imageHolder,sign);
        thumb.setThumbSrc(imgSrc);
        thumb.setCreateTime(LocalDateTime.now());
        if (thumbMapper.updateById(thumb)>0) {
          return thumb;
        }

      } catch (Exception e) {
        throw new MyException("保存文件错误");
      }
    }else {
      throw new MyException("Http文件错误");
    }

    return null;
  }

}

package com.mission.course.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mission.course.common.exception.MyException;
import com.mission.course.common.util.ImgUtil;
import com.mission.course.common.util.MyFileUtil;
import com.mission.course.dao.VideoMapper;
import com.mission.course.dto.FileHolder;
import com.mission.course.dto.ImageHolder;
import com.mission.course.entity.Video;
import com.mission.course.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mission
 * @since 2018-10-11
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

  @Resource
  private VideoMapper videoMapper;

  @Override
  public List<Video> getVideoList(Page page, Video video) {
    return videoMapper.getVideoList(page,video);
  }

  @Override
  public Video saveVideo(MultipartFile files[], Video video) {
    if (files==null&&files.length!=2){
      return null;
    }
    handleSaveVideo(files, video);
    if (video.insert()) {
     return video;
    }

    return null;
  }


  @Override
  public Video updateVideo(MultipartFile[] files, Video video) {
    if (video==null&&video.getVideoId()==null){
      return null;
    }
    if (files==null&&files.length==2) {
      Video videoTemp = videoMapper.selectById(video.getVideoId());
      MyFileUtil.deleteFileOrPath(videoTemp.getVideoSrc());
      MyFileUtil.deleteFileOrPath(videoTemp.getThumbSrc());
      handleSaveVideo(files, video);
    }else {
      return null;
    }
    if (video.updateById()) {
      return video;
    }

    return null;
  }


  private void handleSaveVideo(MultipartFile[] files, Video video) {
    try {
      FileHolder fileHolder = new FileHolder(files[0].getOriginalFilename(), files[0].getInputStream());
      ImageHolder imageHolder = new ImageHolder(files[1].getOriginalFilename(), files[1].getInputStream());
      String src = MyFileUtil.saveFile(fileHolder, "video");
      String imgSrc = ImgUtil.generateThumbnail(imageHolder, "video");
      video.setVideoSrc(src);
      video.setThumbSrc(imgSrc);
    } catch (IOException e) {
      throw new MyException("保存视频失败");
    }
  }


  @Override
  public boolean deteleVideo(Integer id) {
    Video video =videoMapper.selectById(id);
    MyFileUtil.deleteFileOrPath(video.getVideoSrc());
    MyFileUtil.deleteFileOrPath(video.getThumbSrc());
    if(videoMapper.deleteById(id)>0){
      return true;
    }
    return false;
  }
}

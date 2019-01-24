package com.mission.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mission.course.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mission
 * @since 2018-10-11
 */
public interface VideoService extends IService<Video> {

  List<Video> getVideoList(Page page, Video video);

  Video saveVideo(MultipartFile file[],Video video);

  Video updateVideo(MultipartFile file[],Video video);

  boolean deteleVideo(Integer id);
}

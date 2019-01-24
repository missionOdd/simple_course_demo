package com.mission.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mission.course.entity.Thumb;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface ThumbService extends IService<Thumb> {

  List<Thumb> getThumbList(Page page,Thumb thumbCondition);

  Thumb saveThumb(MultipartFile file,Thumb thumb, String sign);

  boolean deleteThumb(Thumb thumb);

  Thumb updateThumb(MultipartFile file,Thumb thumb ,String sign);

  Thumb savePhoto(MultipartFile file,Thumb thumb ,String sign);

  Thumb updatePhoto(MultipartFile file,Thumb thumb , String sign);
}

package com.mission.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mission.course.entity.FileCategory;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface FileCategoryService extends IService<FileCategory> {

  boolean deleteFileCategory(Integer fileCategoryId);

}

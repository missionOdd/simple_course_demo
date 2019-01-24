package com.mission.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mission.course.dao.FileMapper;
import com.mission.course.entity.File;
import com.mission.course.entity.FileCategory;
import com.mission.course.dao.FileCategoryMapper;
import com.mission.course.service.FileCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
public class FileCategoryServiceImpl extends ServiceImpl<FileCategoryMapper, FileCategory> implements FileCategoryService {

  @Resource
  private FileCategoryMapper fileCategoryMapper;

  @Resource
  private FileMapper fileMapper;

  @Override
  public boolean deleteFileCategory(Integer fileCategoryId) {

    File file = new File();
    file.setFileCategoryId(fileCategoryId);
    if (fileMapper.delete(new QueryWrapper<>(file))>0){
      fileCategoryMapper.deleteById(fileCategoryId);
      return true;
    }

    return false;
  }
}

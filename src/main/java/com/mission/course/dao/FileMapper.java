package com.mission.course.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface FileMapper extends BaseMapper<File> {

  /**
   * 分页条件查询
   * @param page
   * @param fileCondition
   * @return
   */
  List<File> getFileList(Page<File> page,@Param("file") File fileCondition);

}

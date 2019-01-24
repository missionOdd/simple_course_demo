package com.mission.course.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface NoticeMapper extends BaseMapper<Notice> {

  List<Notice> getNoticeList(Page<Notice> page, @Param("notice")Notice noticeCondition);
}

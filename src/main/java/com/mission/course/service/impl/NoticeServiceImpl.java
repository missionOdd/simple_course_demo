package com.mission.course.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.Notice;
import com.mission.course.dao.NoticeMapper;
import com.mission.course.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

  @Resource
  private NoticeMapper noticeMapper;

  @Override
  public List<Notice> getNoticeList(Page page, Notice noticeCondition) {
    return noticeMapper.getNoticeList(page,noticeCondition);
  }

  @Override
  public Notice addNotice(MultipartFile file, Notice notice, String sign) {
    return null;
  }
}

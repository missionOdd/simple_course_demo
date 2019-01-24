package com.mission.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mission.course.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface NoticeService extends IService<Notice> {


  List<Notice> getNoticeList(Page page,Notice noticeCondition);

  Notice addNotice(MultipartFile file, Notice notice,String sign);
}

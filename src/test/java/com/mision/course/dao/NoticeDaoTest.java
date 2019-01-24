package com.mision.course.dao;
import com.mision.course.BaseTest;
import com.mission.course.dao.NoticeMapper;
import com.mission.course.entity.Notice;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author mission
 * @date 2018/10/7 0007-15:17
 */
public class NoticeDaoTest  extends BaseTest {

  @Resource
  private NoticeMapper noticeMapper;
  @Resource
  private DataFactory dataFactory;

  @Test
  @Ignore
  public void testAdd(){
    Notice notice =new Notice();
    for (int i = 0; i <8 ; i++) {
      notice.setNoticeTitle(dataFactory.getRandomWord(4));


      notice.setNoticeContent(dataFactory.getRandomWord(24));
      notice.setPriority(0);
      notice.setLastEditTime(LocalDateTime.now());
      notice.setCreateTime(LocalDateTime.now());
      notice.setNoticeStatus(1);
      notice.setThumbId(2);
      notice.setVersion(1);
      noticeMapper.insert(notice);
    }


  }
}
